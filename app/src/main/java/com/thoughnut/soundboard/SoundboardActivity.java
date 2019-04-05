package com.thoughnut.soundboard;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;


public class SoundboardActivity extends AppCompatActivity{

    private static final String LOG_TAG = "SoundboardActivity";

    //Viewpager
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    //Refresh
    private static Button refresh;
    private String zip = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Arrieboard Downloads/";
    private String unzipa = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Arrieboard Downloads/arrie";
    private String unzipj = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Arrieboard Downloads/jappie";
    private String unzipr = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Arrieboard Downloads/rest";

    Toolbar toolbar;
    View v;

    //Video
    public VideoView videoView;
    public static Handler handler;
    public static Vibrator vibratie;



    //Jappie op de schaal van kut
    public  static SeekBar seekBar;
    public  static TextView cijfer;
    public TextView schaaltext;
    public Button button;




    private View mLayout;

    //jappie

    MediaPlayer[] localplayer = new MediaPlayer[]{};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);


        if(appUpdate())
            Log.d(LOG_TAG, "WOOHOO er is een update!");



        //ViewPager
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        setupVPager(viewPager);

        final TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mLayout = findViewById(R.id.activity_soundboard);
        localplayer = new MediaPlayer[14];
        localplayer[0] = MediaPlayer.create(this, R.raw.sound0);
        localplayer[1] = MediaPlayer.create(this, R.raw.sound1);
        localplayer[2] = MediaPlayer.create(this, R.raw.sound2);
        localplayer[3] = MediaPlayer.create(this, R.raw.sound3);
        localplayer[4] = MediaPlayer.create(this, R.raw.sound4);
        localplayer[5] = MediaPlayer.create(this, R.raw.sound5);
        localplayer[6] = MediaPlayer.create(this, R.raw.sound6);
        localplayer[7] = MediaPlayer.create(this, R.raw.sound7);
        localplayer[8] = MediaPlayer.create(this, R.raw.sound8);
        localplayer[9] = MediaPlayer.create(this, R.raw.sound9);
        localplayer[10] = MediaPlayer.create(this, R.raw.sound10);
        localplayer[11] = MediaPlayer.create(this, R.raw.jestaat);
        localplayer[12] = MediaPlayer.create(this, R.raw.komma);
        localplayer[13] = MediaPlayer.create(this, R.raw.opdekutschaal);



//video
        videoView = findViewById(R.id.video);
        final Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gucci);
        videoView.setVisibility(View.GONE);
//videoview
        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin=0;
        videoView.setLayoutParams(params);



        //schaal van kut
        seekBar = findViewById(R.id.seekBar);

        cijfer = findViewById(R.id.cijfer);

        schaaltext = findViewById(R.id.schaaltext);

        button = findViewById(R.id.button);


//refresh
        refresh = findViewById(R.id.refresh);

        mLayout = findViewById(R.id.activity_soundboard);

        toolbar = findViewById(R.id.soundboard_toolbar);
        setSupportActionBar(toolbar);


        //refresh
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isConnectingToInternet()){
                    //verwijder oude bestanden
                    File dir = new File(Environment.getExternalStorageDirectory().toString()+"/Arrieboard Downloads/");
                    if (dir.isDirectory())
                    {
                        String[] children = dir.list();
                        for (int i = 0; i < children.length; i++)
                        {
                            new File(dir, children[i]).delete();
                        }
                    }
                    //download nieuwe bestanden
                    new DownloadTask(SoundboardActivity.this, refresh, Utils.darrie);
                    new DownloadTask(SoundboardActivity.this, refresh, Utils.djappie);
                    new DownloadTask(SoundboardActivity.this, refresh, Utils.drest);
                }else{
                    Toast.makeText(getBaseContext(), "Geen internet", Toast.LENGTH_SHORT).show();
                }



            }
        });




        //geheim


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("het werkt");
                videoView.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.GONE);
                refresh.setVisibility(View.GONE);
                videoView.setVideoURI(video);
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        videoView.start();
                        handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //vibrator
                                //990 ms
                                vibratie = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                if (Build.VERSION.SDK_INT >= 26){
                                    vibratie.vibrate(VibrationEffect.createOneShot(990, 255));
                                }else{
                                    vibratie.vibrate(990);
                                }



                            }
                        },3400);

                    }
                });
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        videoView.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.VISIBLE);
                        refresh.setVisibility(View.VISIBLE);
                    }
                });

            }
        });

        requestPermission();
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();

        EventHandlerClass.releaseMediaPlayer();
    }

    private void requestPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }

            if (!Settings.System.canWrite(this)){
                Snackbar.make(mLayout, "De app heeft toegang nodig tot instellingen", Snackbar.LENGTH_INDEFINITE).setAction("OK",
                        new View.OnClickListener(){

                            @Override
                            public void onClick(View v) {
                                Context context = v.getContext();
                                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                                intent.setData(Uri.parse("package:" + context.getPackageName()));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }).show();
            }

        }
    }

    private boolean appUpdate(){

        final String PREFS_NAME = "VersionPref";
        final String PREF_VER_CODEKEY = "version_code";
        final int NOT_EXIST = -1;

        int currentVerCode = 0;
        try{

            currentVerCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;

        }catch(PackageManager.NameNotFoundException e){

            Log.e(LOG_TAG, e.getMessage());
        }

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VER_CODEKEY, NOT_EXIST);
        SharedPreferences.Editor edit = prefs.edit();
        if(currentVerCode > savedVersionCode){
            edit.putInt(PREF_VER_CODEKEY, currentVerCode);
            edit.commit();

            return true;
        }

        return false;
    }

    private void setupVPager(ViewPager VP){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DemoFrag1());
        adapter.addFrag(new DemoFrag2());
        adapter.addFrag(new DemoFrag3());
        adapter.addFrag(new DemoFrag4());
        adapter.addFrag(new DemoFrag5());
        VP.setAdapter(adapter);
    }


    //Check if internet is present or not
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
