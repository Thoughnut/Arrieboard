package com.thoughnut.soundboard;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadTask {
    private static final String TAG = "Download Task";
    private Context context;
    private Button buttonText;
    private String downloadUrl = "", downloadFileName = "";
    private String[] filename= {"darrie.zip","djappie.zip","drest.zip"};

    //ziplocaties
    private String zip = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Arrieboard Downloads/";
    private String unzipa = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Arrieboard Downloads/arrie";
    private String unzipj = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Arrieboard Downloads/jappie";
    private String unzipr = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Arrieboard Downloads/rest";


    public DownloadTask(Context context, Button buttonText, String downloadUrl) {
        this.context = context;
        this.buttonText = buttonText;
        this.downloadUrl = downloadUrl;

        if(downloadUrl.equals(Utils.darrie)) {
            downloadFileName = downloadUrl.replace(Utils.darrie, filename[0]);//Create file name by picking download file name from URL
        }
        if(downloadUrl.equals(Utils.djappie)) {
            downloadFileName = downloadUrl.replace(Utils.djappie, filename[1]);//Create file name by picking download file name from URL
        }
        if(downloadUrl.equals(Utils.drest)) {
            downloadFileName = downloadUrl.replace(Utils.drest, filename[2]);//Create file name by picking download file name from URL
        }

        Log.e(TAG, downloadFileName);

        //Start Downloading Task
        new DownloadingTask().execute();
    }

    private class DownloadingTask extends AsyncTask<Void, Void, Void> {

        File apkStorage = null;
        File outputFile = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            buttonText.setEnabled(false);
            Toast.makeText(context, R.string.downloadStarted, Toast.LENGTH_SHORT).show();//Set Button Text when download started
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    buttonText.setEnabled(true);

                    //zorg dat hierna wordt geunzipped
                    if(downloadUrl.equals(Utils.darrie)) {
                    if(FileHelper.unzip(zip+"darrie.zip",unzipa)){ }
                        Toast.makeText(context, R.string.downloadCompleteda, Toast.LENGTH_SHORT).show();//If Download completed then change button text
                        DemoFrag1.hayoarrie();
                    }
                    if(downloadUrl.equals(Utils.djappie)) {
                        if(FileHelper.unzip(zip+"djappie.zip",unzipj)){ }
                        Toast.makeText(context, R.string.downloadCompletedj, Toast.LENGTH_SHORT).show();//If Download completed then change button text
                        DemoFrag2.hayojappie();
                    }
                    if(downloadUrl.equals(Utils.drest)) {
                        if(FileHelper.unzip(zip+"drest.zip",unzipr)){ }
                        Toast.makeText(context, R.string.downloadCompletedr, Toast.LENGTH_SHORT).show();//If Download completed then change button text
                        DemoFrag3.hayorest();
                    }

                } else {
                    Toast.makeText(context, R.string.downloadFailed, Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            buttonText.setEnabled(true);
                            Toast.makeText(context, R.string.downloadAgain, Toast.LENGTH_SHORT).show();//Change button text again after 3sec
                        }
                    }, 3000);

                    Log.e(TAG, "Download Failed");

                }
            } catch (Exception e) {
                e.printStackTrace();

                //Change button text if exception occurs
                Toast.makeText(context, R.string.downloadFailed, Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        buttonText.setEnabled(true);
                        Toast.makeText(context, R.string.downloadAgain, Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
                Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

            }


            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                URL url = new URL(downloadUrl);//Create Download URl
                HttpsURLConnection c = (HttpsURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection

                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpsURLConnection.HTTP_OK) {
                    Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());

                }


                //Get File if SD card is present
                if (new CheckSD().isSDCardPresent()) {

                    apkStorage = new File(
                            Environment.getExternalStorageDirectory() + "/"
                                    + Utils.downloadDirectory);
                } else
                    Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

                //If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e(TAG, "Directory Created.");
                }

                outputFile = new File(apkStorage, downloadFileName);//Create Output file in Main File

                //Create New File if not present
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e(TAG, "File Created");
//                    Toast.makeText(context, "Download klaar", Toast.LENGTH_SHORT).show();
                }

                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);//Write new file
                }

                //Close all connection after doing task
                fos.close();
                is.close();

            } catch (Exception e) {

                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e(TAG, "Download Error Exception " + e.getMessage());
            }

            return null;
        }
    }
}
