package com.master.api;

/**
 * Created by Administrator on 2015/6/5.
 */
public class TtsUtil {

    public final static String API_START = "http://tts-api.com/tts.mp3?q=";

    //�滻���ӵĿո�
    public static String getMp3URL(String sentence) {
        String replace = sentence.replace(" ", "%20");
        return API_START + replace;
    }
}
