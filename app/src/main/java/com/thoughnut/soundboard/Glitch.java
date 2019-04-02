package com.thoughnut.soundboard;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Glitch extends Activity{
    private RecyclerView myrecyclerview;
    private List<String> nameList;
    private ArrayList<SoundObject> soundList = new ArrayList<>();
    private VideoView videoView;
    @Override
    public void onBackPressed() {

    }


    public Glitch(){

    }
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glitchlayout);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        |View.SYSTEM_UI_FLAG_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        );


        //video
        videoView = findViewById(R.id.glitchvideo);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.glitch));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                myrecyclerview.setVisibility(View.GONE);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.setVisibility(View.GONE);
                myrecyclerview.setVisibility(View.VISIBLE);
            }
        });






        //glitchlist
        nameList = Arrays.asList(getResources().getStringArray(R.array.soundNames));

        myrecyclerview = findViewById(R.id.glitchview);
        SoundboardRecyclerAdapter3 adapter = new SoundboardRecyclerAdapter3(this, soundList);
        myrecyclerview.setLayoutManager(new GridLayoutManager(this,3));
        myrecyclerview.setAdapter(adapter);

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));


        soundList.clear();

        SoundObject[] soundItems = {
                new SoundObject(random(), R.raw.g1),
                new SoundObject(random(), R.raw.g2),
                new SoundObject(random(), R.raw.g3),
                new SoundObject(random(), R.raw.g4),
                new SoundObject(random(), R.raw.g5),
                new SoundObject(random(), R.raw.g6),
                new SoundObject(random(), R.raw.g7),
                new SoundObject(random(), R.raw.g8),
                new SoundObject(random(), R.raw.g9),
                new SoundObject(random(), R.raw.g10),
                new SoundObject(random(), R.raw.g11),
                new SoundObject(random(), R.raw.g12),
                new SoundObject(random(), R.raw.g13),
                new SoundObject(random(), R.raw.g1),
                new SoundObject(random(), R.raw.g2),
                new SoundObject(random(), R.raw.g3),
                new SoundObject(random(), R.raw.g4),
                new SoundObject(random(), R.raw.g5),
                new SoundObject(random(), R.raw.g6),
                new SoundObject(random(), R.raw.g7),
                new SoundObject(random(), R.raw.g8),
                new SoundObject(random(), R.raw.g9),
                new SoundObject(random(), R.raw.g10),
                new SoundObject(random(), R.raw.g11),
                new SoundObject(random(), R.raw.g12),
                new SoundObject(random(), R.raw.g13),
                new SoundObject(random(), R.raw.g1),
                new SoundObject(random(), R.raw.g2),
                new SoundObject(random(), R.raw.g3),
                new SoundObject(random(), R.raw.g4),
                new SoundObject(random(), R.raw.g5),
                new SoundObject(random(), R.raw.g6),
                new SoundObject(random(), R.raw.g7),
                new SoundObject(random(), R.raw.g8),
                new SoundObject(random(), R.raw.g9),
                new SoundObject(random(), R.raw.g10),
                new SoundObject(random(), R.raw.g11),
                new SoundObject(random(), R.raw.g12),
                new SoundObject(random(), R.raw.g13),




        };
            soundList.addAll(Arrays.asList(soundItems));

    }


    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(18);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        String c = " ";
        if (randomStringBuilder.toString().isEmpty() || randomStringBuilder.toString().contains(c)){
            random();
        }


        return randomStringBuilder.toString();
    }


}
