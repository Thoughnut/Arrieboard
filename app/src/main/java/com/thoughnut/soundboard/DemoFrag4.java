package com.thoughnut.soundboard;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.seismic.ShakeDetector;



public class DemoFrag4 extends Fragment implements ShakeDetector.Listener {
    MediaPlayer[] localplayer = new MediaPlayer[]{};
    private VideoView videoView;
    private Uri video;
    private Handler handler;
    private Handler handler2;
    private Handler handler3;
    private Vibrator vibratie;
    private View v;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;
    private static TextView cijfer;
    private TextView schaaltext;
    private Button button;
    private static MediaPlayer media;
    private static SeekBar seekBar;
    private TabLayout tabLayout;
    public DemoFrag4() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_demo5, container, false);


        localplayer = new MediaPlayer[14];
        localplayer[0] = MediaPlayer.create(getActivity(), R.raw.sound0);
        localplayer[1] = MediaPlayer.create(getActivity(), R.raw.sound1);
        localplayer[2] = MediaPlayer.create(getActivity(), R.raw.sound2);
        localplayer[3] = MediaPlayer.create(getActivity(), R.raw.sound3);
        localplayer[4] = MediaPlayer.create(getActivity(), R.raw.sound4);
        localplayer[5] = MediaPlayer.create(getActivity(), R.raw.sound5);
        localplayer[6] = MediaPlayer.create(getActivity(), R.raw.sound6);
        localplayer[7] = MediaPlayer.create(getActivity(), R.raw.sound7);
        localplayer[8] = MediaPlayer.create(getActivity(), R.raw.sound8);
        localplayer[9] = MediaPlayer.create(getActivity(), R.raw.sound9);
        localplayer[10] = MediaPlayer.create(getActivity(), R.raw.sound10);
        localplayer[11] = MediaPlayer.create(getActivity(), R.raw.jestaat);
        localplayer[12] = MediaPlayer.create(getActivity(), R.raw.komma);
        localplayer[13] = MediaPlayer.create(getActivity(), R.raw.opdekutschaal);

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        seekBar = getActivity().findViewById(R.id.seekBar);
        seekBar = getActivity().findViewById(R.id.seekBar);
        cijfer = getActivity().findViewById(R.id.cijfer);
        schaaltext = getActivity().findViewById(R.id.schaaltext);
        button = getActivity().findViewById(R.id.button);
        //videoView = (VideoView) getActivity().findViewById(R.id.video);
        video = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.gucci);
        seek();
        jappieclick2(v);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        shakeDetector = new ShakeDetector(this);
        shakeDetector.start(sensorManager);
    }

    public void seek(){
        //seekBar


        cijfer.setText("" +  seekBar.getProgress()/10);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    float progress_val;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        progress_val = (float) progress;
                        cijfer.setText(String.valueOf(progress_val/10));


                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        cijfer.setText("" + progress_val/10);
                    }
                }

        );
    }

    public void jappieclick2(View v){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int voorkomma;
                final int nakomma;
                voorkomma = seekBar.getProgress()/10;

                nakomma = (int) Math.floor(seekBar.getProgress())-(seekBar.getProgress()/10)*10;
                System.out.println(voorkomma + "     " + nakomma);

                if (voorkomma == nakomma){
                    localplayer[11].start();


                }


                localplayer[11].start();




                localplayer[11].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        localplayer[voorkomma].start();

                    }
                });


                localplayer[voorkomma].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        localplayer[12].start();
                    }
                });


                localplayer[12].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        tweedehelft(nakomma);
                    }
                });




            }
        });
    }


    public void tweedehelft(int nakomma){
        nakomma = (int) Math.floor(seekBar.getProgress())-(seekBar.getProgress()/10)*10;
        localplayer[nakomma].start();
        localplayer[nakomma].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                localplayer[13].start();
            }
        });
    }

    @Override
    public void hearShake(){
        //wat moet er gebeuren als je schud
        final int voorkomma;
        final int nakomma;
        voorkomma = seekBar.getProgress()/10;
        nakomma = (int) Math.floor(seekBar.getProgress())-(seekBar.getProgress()/10)*10;
        videoView = getActivity().findViewById(R.id.video);
        tabLayout = getActivity().findViewById(R.id.tabs);


        if(voorkomma ==6){
            if (nakomma ==9){
                if(videoView.isPlaying()){
                    if(tabLayout.getSelectedTabPosition() == 1){
                        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        video = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.secret);
                        videoView.setVideoURI(video);
                        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                tabLayout.setVisibility(View.GONE);
                                videoView.start();
                                handler3 = new Handler();
                                handler3.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //vibrator
                                        //990 ms
                                        vibratie = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                                        if (Build.VERSION.SDK_INT >= 26){
                                            vibratie.vibrate(VibrationEffect.createOneShot(1970, 255));
                                        }else{
                                            vibratie.vibrate(1970);
                                        }



                                    }
                                },9240);

                                handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //vibrator
                                        //990 ms
                                        vibratie = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                                        if (Build.VERSION.SDK_INT >= 26){
                                            vibratie.vibrate(VibrationEffect.createOneShot(180, 255));
                                        }else{
                                            vibratie.vibrate(180);
                                        }



                                    }
                                },11850);

                                handler2 = new Handler();
                                handler2.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        vibratie =(Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                                        if (Build.VERSION.SDK_INT >= 26){
                                            vibratie.vibrate(VibrationEffect.createOneShot(170, 255));
                                        }else{
                                            vibratie.vibrate(170);
                                        }



                                    }
                                },12660);




                            }
                        });
                        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                tabLayout.setVisibility(View.VISIBLE);
                                videoView.setVisibility(View.GONE);
                                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                            }
                        });



                    }
                }else{
                    media = MediaPlayer.create(getContext(), R.raw.crimirates);
                    media.start();
                }



            }
        }


    }


}
