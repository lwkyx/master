package com.master.api;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2015/6/5.�����
 */
public class PlayTask extends AsyncTask<String, Void, Void> {
    private Context context;
    private MediaPlayer mediaPlayer;

    public PlayTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... params) {
        File mp3 = new File("/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + context.getPackageName() + "/please.mp3");
        InputStream is = null;
        OutputStream os = null;
        HttpURLConnection connection = null;
        URL url;
        try {
            url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int fileLength = connection.getContentLength();
            is = connection.getInputStream();
            os = new FileOutputStream("/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + context.getPackageName() + "/please.mp3");
            byte data[] = new byte[4096];
            int count;
            while ((count = is.read(data)) != -1) { /* allow canceling with back button*/
                if (isCancelled()) {
                    is.close();
                    return null;
                }
                os.write(data, 0, count);
            }
        } catch (MalformedURLException e) { /* TODO Auto-generated catch block*/
            e.printStackTrace();
        } catch (IOException e) { /* TODO Auto-generated catch block*/
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) { /* TODO Auto-generated method stub*/
        super.onPostExecute(result);

        if(mediaPlayer == null) {
            mediaPlayer= MediaPlayer.create(context, Uri.parse("/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + context.getPackageName() + "/please.mp3"));
            if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
            }
        }



    }
}
