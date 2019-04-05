package com.thoughnut.soundboard;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class DemoFrag5 extends Fragment{




    private Button toevoegen;
    private Button verwijderen;

    //opnemen
    private Button record,stop;
    private MediaRecorder mediaRecorder;
    private File target;
    private String pathSave = "";
    private EditText editText;
    private Button save,cancel;
    private View viewknop;


    final int REQUEST_PERMISSION_CODE =1000;


    public static RecyclerView recyclerView;

    private static ArrayList<SoundObject2> soundList = new ArrayList<>();
    public static SoundObject2[] soundItems;

    private static Context context;
    private static SoundboardRecyclerAdapter2 adapter =new SoundboardRecyclerAdapter2(context, soundList);
    private static Activity activity;



    public DemoFrag5() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_demo6, container, false);
        activity = (Activity) view.getContext();
        recyclerView = view.findViewById(R.id.soundboardRecyclerView5);

        //request
        if(!checkPermissionFromDevice()) requestPermission();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(adapter);

        //opnemen
        record = view.findViewById(R.id.record);
        stop = view.findViewById(R.id.stoprecord);




        verwijderen = view.findViewById(R.id.verwijderen);
        verwijderen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verwijder /eigengeluiden/
                soundList.clear();
                adapter.notifyDataSetChanged();

                File dir = new File(Environment.getExternalStorageDirectory().toString()+"/eigengeluiden");
                if (dir.isDirectory())
                {
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++)
                    {
                        new File(dir, children[i]).delete();
                    }
                }
                hayotje();

            }
        });

        toevoegen = view.findViewById(R.id.toevoegen);
        toevoegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(getContext(),FileChooser.class);
                 getContext().startActivity(i1);
            }
        });


            record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkPermissionFromDevice()) {

                    record.setEnabled(false);
                    stop.setEnabled(true);
                    target = new File(Environment.getExternalStorageDirectory().toString() + "/arrieboardrecordings/");
                    target.mkdirs();

                    pathSave = Environment.getExternalStorageDirectory().getAbsolutePath() + "/arrieboardrecordings/" + "recorded_audio.mp3";
                    setupMediaRecorder();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    record.setBackground(getResources().getDrawable(R.drawable.recordoff));
                    stop.setBackground(getResources().getDrawable(R.drawable.stoprecord));

                    Toast.makeText(getContext(), "Aan het opnemen...", Toast.LENGTH_SHORT).show();
                }else{
                        requestPermission();
                    }
                }
            });

            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog filename = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar);
                    filename.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100,0,0,0)));
                    filename.setContentView(R.layout.naamgeving);
                    filename.setCancelable(true);
                    filename.show();
                    mediaRecorder.stop();
                    stop.setEnabled(false);
                    record.setEnabled(true);
                    record.setBackground(getResources().getDrawable(R.drawable.record));
                    stop.setBackground(getResources().getDrawable(R.drawable.stoprecordoff));
                    editText =filename.findViewById(R.id.naam);
                    save = filename.findViewById(R.id.save);
                    cancel = filename.findViewById(R.id.cancel);



                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            filename.cancel();
                        }
                    });
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String name = editText.getText().toString();
                            if (name.equals("") ){
                                name = "Geen Naam";
                            }
                            String recording = pathSave;
                            String targetPath = Environment.getExternalStorageDirectory().toString() + "/eigengeluiden/"+ name + ".mp3";
                            File source = new File(recording);
                            File target = new File(targetPath);
                            File createDir = new File(Environment.getExternalStorageDirectory().toString()+ "/eigengeluiden/");
                            createDir.mkdir();

                            try {

                                if (source.exists()) {

                                    InputStream in = new FileInputStream(source);
                                    OutputStream out = new FileOutputStream(target);

                                    byte[] buf = new byte[1024];
                                    int len;

                                    while ((len = in.read(buf)) > 0) {
                                        out.write(buf, 0, len);
                                    }

                                    in.close();
                                    out.close();


                                } else {
                                    Toast.makeText(getContext(),"Mislukt", Toast.LENGTH_SHORT).show();
                                }

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }



                            hayotje();
                            filename.cancel();
                        }

                    });


                    hayotje();
                }
            });









        return view;
    }

    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioEncodingBitRate(16*44100);
        mediaRecorder.setAudioSamplingRate(44100);
        mediaRecorder.setOutputFile(pathSave);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "Toestemming Verleend", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Toestemming Geweigerd", Toast.LENGTH_SHORT).show();

                }
                break;
            }
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.RECORD_AUDIO

        },REQUEST_PERMISSION_CODE);
    }

    private boolean checkPermissionFromDevice() {
        int record_audio_result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO);
        return record_audio_result == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        soundList.clear();
        String path = Environment.getExternalStorageDirectory().toString()+ "/eigengeluiden/";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();



        if (files!=null && files.length>0){
            Arrays.sort(files);
        for (int i = 0; i < files.length; i++)
        {

            Log.d("Files", "FileName:" + files[i].getName());
            //vind substring
            String extension = files[i].getName().substring(files[i].getName().lastIndexOf("."));
            int len = files[i].getName().length();
            int exlen = extension.length();
            int sublen = len - exlen;

            soundItems = new SoundObject2[]{
                    new SoundObject2(files[i].getName().substring(0,sublen) , Uri.parse(path + files[i].getName()), path + files[i].getName())

            };
            soundList.addAll(Arrays.asList(soundItems));
        }}


    }

    public static void hayotje(){
        soundList.clear();
        String path = Environment.getExternalStorageDirectory().toString()+ "/eigengeluiden/";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();




        if (files!=null && files.length>0){
            Arrays.sort(files);
        for (int i = 0; i < files.length; i++)
        {

            Log.d("Files", "FileName:" + files[i].getName());
            //vind substring
            String extension = files[i].getName().substring(files[i].getName().lastIndexOf("."));
            int len = files[i].getName().length();
            int exlen = extension.length();
            int sublen = len - exlen;


            soundItems = new SoundObject2[]{
                    new SoundObject2(files[i].getName().substring(0,sublen) , Uri.parse(path + files[i].getName()), path + files[i].getName())

            };
            soundList.addAll(Arrays.asList(soundItems));
            adapter = new SoundboardRecyclerAdapter2(context, soundList);
            recyclerView.setLayoutManager(new GridLayoutManager(activity,3));
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        }}


    }



}
