package com.master.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/4.
 */
public class DatabaseUtils {


    public static final String DBNAME = "traveleng.db";
    public static final String TABLE_NAME = "traveleng_cate";

    /**
     * 先检测应用程序data目录下面是否已经存在该文件。不存在则 复制assert目录下文件到应用程序data目录下面
     *
     * @param context
     * @param fileName
     * @throws IOException
     */
    public static void copyDB(Context context, String fileName) throws IOException {
        String filePath = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        if (new File(filePath).exists()) {
            return;
        }
        FileOutputStream fos = new FileOutputStream(new File(filePath));
        InputStream is = context.getResources().getAssets().open(fileName);
        byte[] buffer = new byte[1024 * 500];
        int count = 0;
        while ((count = is.read(buffer)) > 0)
        {
            fos.write(buffer, 0, count);
        }
        fos.close();
        is.close();
    }



    public static Map<String, Integer> getClassify(SQLiteDatabase db, String tableName,String id, String name ) {
        Map<String, Integer> classifyMap = new LinkedHashMap<String, Integer>();
        Cursor cursor = db.query(tableName, new String[] { id, name },null,null,null,null,null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                classifyMap.put(cursor.getString(cursor.getColumnIndex(name)),
                        cursor.getInt(cursor.getColumnIndex(id)));
            }
        }
        if (cursor != null && !cursor.isClosed())
        {
            cursor.close();
        }
        return classifyMap;
    }


    public static Map<String, String> getItem(SQLiteDatabase db, String tableName, String id ,String zh , String en , int pcode) {
        Map<String, String> itemMap = new LinkedHashMap<String, String>();
        Cursor cursor = db.query(tableName, new String[] { id, zh, en }, id+"=?",
                new String[] { "" + pcode }, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                itemMap.put(cursor.getString(cursor.getColumnIndex(zh)),
                        cursor.getString(cursor.getColumnIndex(en)));
            }
        }
        if (cursor != null && !cursor.isClosed())
        {
            cursor.close();
        }
        return itemMap;
    }



    public static  String getMp3Path(SQLiteDatabase db ,String topic_x ){
        Cursor cursor = db.query("sentence_list", new String[] { "dialogue_mp3" }, "topic_x=?",
                new String[] { "" + topic_x }, null, null, null);
        String mp3Path = null;
        if (cursor != null) {
            while (cursor.moveToNext()) {
            mp3Path = cursor.getString(cursor.getColumnIndex("dialogue_mp3"));
            }
        }
        if (cursor != null && !cursor.isClosed())
        {
            cursor.close();
        }

        return mp3Path;
    }

}