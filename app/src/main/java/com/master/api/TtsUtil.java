package com.master.api;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/5.
 */
public class TtsUtil {

    public final static String API_START = "http://tts-api.com/tts.mp3?q=";


    public static String getMp3URL(String sentence) {
        String replace = sentence.replace(" ", "%20");
        return API_START + replace;
    }



    /**
     * 打开Activity
     * @param context
     * @param data
     * @param clazz
     */
    public static  void openActivity(Context context,Map<String,Object> data, Class<?> clazz ){
        try{
            Intent intent = new Intent(context, clazz);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            if(data != null){
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) data);
                intent.putExtras(bundle);
            }
            context.startActivity(intent);
        }catch (Exception e) {

        }
    }
}



