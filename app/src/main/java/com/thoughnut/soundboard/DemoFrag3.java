package com.thoughnut.soundboard;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFrag3 extends Fragment {

    private static Context context;
    private  static ArrayList<SoundObject2> soundList = new ArrayList<>();
    public static SoundObject2[] soundItems;
    private static SoundboardRecyclerAdapter2 adapter =new SoundboardRecyclerAdapter2(context, soundList);
    public static RecyclerView recyclerView;


    public DemoFrag3() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo4, container, false);

        recyclerView = view.findViewById(R.id.soundboardRecyclerView4);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(adapter);
        hayorest();

        return view;
    }

    public static void hayorest(){
        soundList.clear();


        String path = Environment.getExternalStorageDirectory().toString() + "/Arrieboard Downloads/rest/";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();


        if (files != null && files.length > 0) {
            Arrays.sort(files);
            for (int i = 0; i < files.length; i++) {
                Log.d("Files", "FileName:" + files[i].getName());
                //vind substring
                String extension = files[i].getName().substring(files[i].getName().lastIndexOf("."));
                int len = files[i].getName().length();
                int exlen = extension.length();
                int sublen = len - exlen;


                soundItems = new SoundObject2[]{
                        new SoundObject2(files[i].getName().substring(0, sublen), Uri.parse(path + files[i].getName()), path + files[i].getName())

                };
                soundList.addAll(Arrays.asList(soundItems));
                adapter = new SoundboardRecyclerAdapter2(context, soundList);
                recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);



    }
}
