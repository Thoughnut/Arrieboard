package com.thoughnut.soundboard;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileChooser extends ListActivity {
  //logtag
  private static final String TAG = "SoundboardActivity.java";

    //file
    public static File currentDir;
    private static FileArrayAdapter adapter;

    private String name;
    private String extension;

    //copy
    private File source;
    private File target;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDir = new File("/sdcard/");
        fill(currentDir);

    }

    String sdCard = Environment.getExternalStorageDirectory().toString();



    public void fill(File f) {

        File[] dirs = f.listFiles();

         this.setTitle("Current Dir: " + f.getName());

        List<Option> dir = new ArrayList<Option>();

        List<Option> fls = new ArrayList<Option>();

        try {

            for (File ff : dirs) {

                if (ff.isDirectory())

                    dir.add(new Option(ff.getName(), "Folder", ff.getAbsolutePath()));

                else {

                    fls.add(new Option(ff.getName(), "File Size: " + ff.length(), ff.getAbsolutePath()));

                }

            }

        } catch (Exception e) {


        }

        Collections.sort(dir);

        Collections.sort(fls);

        dir.addAll(fls);

        if (!f.getName().equalsIgnoreCase("sdcard"))

            dir.add(0, new Option("..", "Parent Directory", f.getParent()));
        adapter = new FileArrayAdapter(FileChooser.this, R.layout.file_view, dir);
        this.setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Option o = adapter.getItem(position);
        if(o.getData().equalsIgnoreCase("folder")||o.getData().equalsIgnoreCase("Parent Directory")){
            currentDir = new File(o.getPath());
            fill(currentDir);
        }else{
            onFileClick(o);
        }
    }

    public void onFileClick(Option o)
    {

        name = o.getName();
        extension = name.substring(name.lastIndexOf("."));
        System.out.println(extension);


        if (extension.equals(".mp3") || extension.equals(".wav") || extension.equals(".ogg")|| extension.equals(".flac")|| extension.equals(".3gp") || extension.equals(".m4a")) {
            System.out.println("Lekker man");
            source = new File(o.getPath());
            target = new File(sdCard + "/eigengeluiden/");
            target.mkdirs();
            target = new File(sdCard + "/eigengeluiden/" + o.getName());

            Log.v(TAG, "sourceLocation: " + source);
            Log.v(TAG, "targetLocation: " + target);

            try {

                if (source.exists()) {

                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);

                    // Copy the bits from instream to outstream
                    byte[] buf = new byte[1024];
                    int len;

                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }

                    in.close();
                    out.close();

                    Log.v(TAG, "Copy file successful.");
                    Toast.makeText(getBaseContext(),"gelukt", Toast.LENGTH_SHORT).show();

                } else {
                    Log.v(TAG, "Copy file failed. Source file missing.");
                    Toast.makeText(getBaseContext(),"geen bestand", Toast.LENGTH_SHORT).show();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getBaseContext(), "Geen audio bestand", Toast.LENGTH_SHORT).show();

        }



        DemoFrag5.hayotje();
        this.finish();


    }

}
