package com.master;

import android.content.Context;
import android.content.Intent;
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
import com.master.database.DatabaseUtils;

import java.io.IOException;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/2/2.
 */
public class MainFragment extends Fragment implements MyItemClickListener {
    public static final String TAG = "MainFragment";
    private static final int SPAN_COUNT = 2;
    private SQLiteDatabase db;
    private Map<String, Integer> travelengMap;
    private String[] travelengArray;
    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initdata();
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.inject(this, view);
        return view;
    }

    private void initdata() {
        try {
            DatabaseUtils.copyDB(getActivity(), DatabaseUtils.DBNAME);
            if (db == null)
                db = getActivity().openOrCreateDatabase(getActivity().getFilesDir().getAbsolutePath() + "/" + DatabaseUtils.DBNAME, Context.MODE_PRIVATE, null);
            travelengMap = DatabaseUtils.getClassify(db, DatabaseUtils.TABLE_NAME);
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
        Intent intent = new Intent(getActivity(), CardActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, travelengMap.get(travelengArray[position]));
        startActivity(intent);
    }
}
