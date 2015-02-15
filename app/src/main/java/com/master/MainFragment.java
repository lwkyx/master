package com.master;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master.activity.CardActivity;
import com.master.adapter.RecyclerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2015/2/2.
 */
public class MainFragment extends Fragment implements RecyclerAdapter.MyItemClickListener {

    public static  final String TAG = "MainFragment";

    private static final int SPAN_COUNT = 2;

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    String[] mData;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initdata();
        View view = inflater.inflate(R.layout.fragment_main, null);
        ButterKnife.inject(this, view);
        return view;
    }



    private void initdata() {
        mData = getResources().getStringArray(R.array.scence_titles);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerAdapter adapter = new RecyclerAdapter(mData);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(View v, int position) {
        startActivity(new Intent(getActivity(), CardActivity.class));
    }

}
