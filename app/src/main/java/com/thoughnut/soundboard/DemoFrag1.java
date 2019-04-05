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
public class DemoFrag1 extends Fragment {
    final int REQUEST_PERMISSION_CODE =1000;

    private static Context context;
    private  static ArrayList<SoundObject2> soundList = new ArrayList<>();
    public static SoundObject2[] soundItems;
    private static SoundboardRecyclerAdapter2 adapter =new SoundboardRecyclerAdapter2(context, soundList);
    public static RecyclerView recyclerView;

    public DemoFrag1() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        recyclerView = view.findViewById(R.id.soundboardRecyclerView1);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(adapter);
        hayoarrie();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }


    public static void hayoarrie(){


                soundList.clear();


                String path = Environment.getExternalStorageDirectory().toString() + "/Arrieboard Downloads/arrie/";
                Log.d("Files", "Path: " + path);
                File directory = new File(path);
                File[] files = directory.listFiles();
                Arrays.sort(files);

                if (files != null && files.length > 0) {
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

}