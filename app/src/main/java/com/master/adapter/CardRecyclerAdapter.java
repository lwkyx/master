package com.master.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.master.R;

/**
 * Created by Administrator on 2015/6/4.
 */
public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.CardHolder> {


    private static final String TAG = "CardRecyclerAdapter";
    private String[] engStrs;
    private String[] chStrs;

    private CardRecyclerAdapter(String[] engStrs, String[] chStrs) {
        this.engStrs = engStrs;
        this.chStrs = chStrs;
    }

    public class CardHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvEng;
        private TextView tvCh;
        private CardItemClickListener mListener;

        public CardHolder(View itemView) {
            super(itemView);
            tvEng = (TextView) itemView.findViewById(R.id.tv_eng);
            tvCh = (TextView) itemView.findViewById(R.id.tv_ch);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        holder.tvCh.setText(chStrs[position]);
        holder.tvEng.setText(engStrs[position]);
    }

    @Override
    public int getItemCount() {
        return chStrs.length;
    }

    public interface CardItemClickListener {
        public void onItemClick(View v, int position);
    }

}
