package com.master;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master.activity.CardActivity;
import com.master.adapter.MyItemClickListener;
import com.master.adapter.RecyclerAdapter;
import com.master.api.TtsUtil;
import com.master.database.DatabaseUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/2/2.
 */
@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements MyItemClickListener {
    public static final String TAG = "MainFragment";
    private static final int SPAN_COUNT = 2;
    private SQLiteDatabase db;
    private Map<String, Integer> travelengMap;
    private String[] travelengArray;
    private String dbName;
    private String id;
    private String name;
    private String tableName;
    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initdata();
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.inject(this, view);
        return view;
    }
    @SuppressLint("ValidFragment")
    public MainFragment(String dbName,String tableName,String id , String name ){
        this.dbName = dbName;
        this.id = id ;
        this.name = name;
        this.tableName = tableName;
    }


    private void initdata() {
        try {
            DatabaseUtils.copyDB(getActivity(),dbName);
            if (db == null)
                db = getActivity().openOrCreateDatabase(getActivity().getFilesDir().getAbsolutePath() + "/" + dbName, Context.MODE_PRIVATE, null);
            travelengMap = DatabaseUtils.getClassify(db, tableName,id,name);
            travelengArray = travelengMap.keySet().toArray(new String[travelengMap.size()]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT)); /*        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/
        RecyclerAdapter adapter = new RecyclerAdapter(travelengArray);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View v, int position) {
        Map<String, Object> data = new HashMap<String, Object>();
        if (dbName.equals("traveleng.db")){
            data.put("dbName","traveleng.db");
            data.put("tableName","traveleng_sent");
            data.put("id","cate_id");
            data.put("zh","sent_zh");
            data.put("en","sent_en");
            data.put("idNum",travelengMap.get(travelengArray[position]));
        }else if (dbName.equals("101_travel.db")){
            data.put("dbName","101_travel.db");
            data.put("tableName","sentence_list");
            data.put("id","sub_indexid");
            data.put("zh","topic_cn");
            data.put("en","topic_x");
            data.put("idNum",travelengMap.get(travelengArray[position]));
        }
        TtsUtil.openActivity(getActivity(),data, CardActivity.class);

    }


//    if (position == 1) {
//        data.put(KSKey.KEY_CONTENT, ATTENTIONTYPE1);
//    } else {
//        data.put(KSKey.KEY_CONTENT, ATTENTIONTYPE2);
//    }
//    data.put("MonitorInfo", mMonitorUserInfo);
//
//    if (dbName.equals("traveleng.db")){
//        intent.putExtra()
//
//    }else if (dbName.equals("101_travel.db")){
//
//    }
//    intent.putExtra(Intent.EXTRA_TEXT, travelengMap.get(travelengArray[position]));
//    startActivity(intent);
}
