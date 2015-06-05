package com.master.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.master.R;

import java.util.Map;

/**
 * Created by Administrator on 2015/6/4.
 */
public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.CardHolder> {


    private static final String TAG = "CardRecyclerAdapter";
//    private String[] engStrs;
    private String[] chStrs;
    private Map<String, String> maps;
    private MyItemClickListener mListener;

    public CardRecyclerAdapter(String[] chStrs, Map<String, String> maps) {
//        this.engStrs = engStrs;
        this.maps = maps;
        this.chStrs = chStrs;
    }

    public class CardHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvEng;
        private TextView tvCh;
        private MyItemClickListener listener;

        public CardHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            tvEng = (TextView) itemView.findViewById(R.id.tv_eng);
            tvCh = (TextView) itemView.findViewById(R.id.tv_ch);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onItemClick(v, getPosition());
            }
        }
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardHolder(rootView, mListener);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.tvCh.setText(chStrs[position]);
        holder.tvEng.setText(maps.get(chStrs[position]));
    }

    @Override
    public int getItemCount() {
        return chStrs.length;
    }


    /**
     * 设置item点击事件
     **/
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mListener = listener;
    }

}
