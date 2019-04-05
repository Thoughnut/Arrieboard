package com.thoughnut.soundboard;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class EventHandlerClass {

    private static final String LOG_TAG = "EVENTHANDLER";

    private static File source;
    private static File target;
    private static MediaPlayer mp;

    public static void startMediaPlayer(View view, Integer soundID){

        try {

            if (soundID != null){

                if (mp != null)
                    mp.reset();

                mp = MediaPlayer.create(view.getContext(), soundID);
                mp.start();
            }
        } catch (Exception e){

            Log.e(LOG_TAG, "Failed to start the MediaPlayer: " + e.getMessage());
        }
    }

    public static void startMediaPlayer2(View view, Uri soundID){

        try {

            if (soundID != null){

                if (mp != null)
                    mp.reset();

                mp = MediaPlayer.create(view.getContext(), soundID);
                mp.start();
            }
        } catch (Exception e){

            Log.e(LOG_TAG, "Failed to start the MediaPlayer: " + e.getMessage());
        }
    }


    public static void releaseMediaPlayer(){

        if (mp != null){

            mp.release();
            mp = null;
        }
    }


    public static void popupM(final View v, final SoundObject soundObject){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.longclick, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.send  || item.getItemId() == R.id.ringtone) {

                    final String fileName = soundObject.getItemName() + ".mp3";

                    File storage = Environment.getExternalStorageDirectory();
                    File directory = new File(storage.getAbsolutePath() + "/Download/Geluiden/");
                    directory.mkdirs();
                    final File file = new File(directory, fileName);

                    InputStream in = v.getContext().getResources().openRawResource(soundObject.getItemID());

                    try {
                        OutputStream out = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer, 0, buffer.length)) != -1){
                            out.write(buffer, 0, len);
                        }
                        in.close();
                        out.close();

                    } catch (IOException e){
                        Log.e(LOG_TAG, "Failed to save file: " + e.getMessage());
                    }

                    if(item.getItemId() ==R.id.send){
                        final String AUTHORITY = v.getContext().getPackageName()+ ".fileprovider";
                        Uri contentUri;

                        contentUri = FileProvider.getUriForFile(v.getContext(), AUTHORITY, file);
                        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                        shareIntent.setType("audio/mp3");
                        v.getContext().startActivity(Intent.createChooser(shareIntent, "Deel geluid via..."));


                    }
                    if(item.getItemId() == R.id.ringtone){
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), AlertDialog.THEME_HOLO_LIGHT);
                        builder.setTitle("Stel in als...");
                        builder.setItems(new CharSequence[]{"Ringtone", "Notificatie", "Alarm (Werkt niet op elke mobiel)"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                switch(which){
                                    case 0:
                                        changeSysAudio(v, fileName, file, 1);
                                        break;
                                    case 1:
                                        changeSysAudio(v, fileName, file, 2);
                                        break;
                                    case 2:
                                        changeSysAudio(v, fileName, file, 3);
                                        break;
                                }

                            }
                        });

                        builder.create();
                        builder.show();

                    }
                }


                return true;
            }
        });
        popup.show();

    }

    public static void popupM2(final View v, final SoundObject2 soundObject){
        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.longclick, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.send  || item.getItemId() == R.id.ringtone) {


                    //alleen naam
                    final String ext = soundObject.getFilePath().substring(soundObject.getFilePath().lastIndexOf("."));

                    final String fileName = soundObject.getItemName() + ext;

                    System.out.println(ext);

                    //locatie
                    final int sub = fileName.length();
                    System.out.println(sub + "        " + fileName);
                    final String dir = soundObject.getFilePath();

                    final String directory = dir.substring(0,dir.length()-fileName.length());
                    System.out.println(directory);


                    final File file = new File(directory, fileName);


                    if(item.getItemId() ==R.id.send){
                        final String AUTHORITY = v.getContext().getPackageName()+ ".fileprovider";
                        Uri contentUri;

                        contentUri = FileProvider.getUriForFile(v.getContext(), AUTHORITY, file);
                        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                        shareIntent.setType("audio/*");
                        v.getContext().startActivity(Intent.createChooser(shareIntent, "Deel geluid via..."));


                    }
                    if(item.getItemId() == R.id.ringtone){
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), AlertDialog.THEME_HOLO_LIGHT);
                        builder.setTitle("Stel in als...");
                        builder.setItems(new CharSequence[]{"Ringtone", "Notificatie", "Alarm (Werkt niet op elke mobiel)"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                switch(which){
                                    case 0:
                                        changeSysAudio(v, fileName, file, 1);
                                        break;
                                    case 1:
                                        changeSysAudio(v, fileName, file, 2);
                                        break;
                                    case 2:
                                        changeSysAudio(v, fileName, file, 3);
                                        break;
                                }

                            }
                        });

                        builder.create();
                        builder.show();

                    }
                }


                return true;
            }
        });
        popup.show();

    }


    private static void changeSysAudio(View v, String fileName, File file, int action) {
        try {

            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());
            values.put(MediaStore.MediaColumns.TITLE, fileName);
            values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/*");
            switch (action) {
                case 1:

                    values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                    values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                    values.put(MediaStore.Audio.Media.IS_ALARM, false);
                    break;
                case 2:
                    values.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                    values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                    values.put(MediaStore.Audio.Media.IS_ALARM, false);
                    break;
                case 3:
                    values.put(MediaStore.Audio.Media.IS_RINGTONE, false);
                    values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
                    values.put(MediaStore.Audio.Media.IS_ALARM, true);
                    break;
            }
            values.put(MediaStore.Audio.Media.IS_MUSIC, false);
            Uri uri = MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath());
            v.getContext().getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + file.getAbsolutePath() + "\"", null);
            Uri finalUri = v.getContext().getContentResolver().insert(uri, values);
            switch (action) {
                case 1:
                    RingtoneManager.setActualDefaultRingtoneUri(v.getContext(), RingtoneManager.TYPE_RINGTONE, finalUri);

                    break;
                case 2:
                    RingtoneManager.setActualDefaultRingtoneUri(v.getContext(), RingtoneManager.TYPE_NOTIFICATION, finalUri);
                    break;
                case 3:
                    RingtoneManager.setActualDefaultRingtoneUri(v.getContext(), RingtoneManager.TYPE_ALARM, finalUri);
                    break;
            }
        }catch (Exception e){
            Log.e(LOG_TAG, "Failed to save as system audio: " + e.getMessage());
        }
    }




}
