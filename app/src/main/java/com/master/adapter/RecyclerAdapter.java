package com.master.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.master.R;

/**
 * Created by YeXiang on 15/2/2.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>  implements View.OnClickListener{

//    LayoutInflater inflater;
//
//    public  RecyclerAdapter(Context context){
//        inflater = LayoutInflater.from(context);
//    }
    public static final  String TAG = "RecyclerAdapter";

//    public List<ScenceInfo> mData = null;

    public String[] TITLES;

//    public  RecyclerAdapter(List<ScenceInfo> data){
//        mData = data;
//    }

    public RecyclerAdapter(String[] titles) {
        TITLES = titles;
    }

    @Override
    public void onClick(View v) {

    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvList;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvList = (TextView) itemView.findViewById(R.id.tv_list);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) {
//        holder.ivList.setBackgroundResource(mData.get(position).icon);
        holder.tvList.setText(TITLES[position]);
    }

    @Override
    public int getItemCount() {
        return TITLES.length;
    }


}

