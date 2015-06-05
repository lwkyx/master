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
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> { /*    LayoutInflater inflater; public  RecyclerAdapter(Context context){ inflater = LayoutInflater.from(context); }*/
    public static final String TAG = "RecyclerAdapter";
    public String[] TITLES;
    private MyItemClickListener mListener;

    public RecyclerAdapter(String[] titles) {
        TITLES = titles;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView tvList;
        private MyItemClickListener listener;

        public MyViewHolder(View itemView, MyItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            tvList = (TextView) itemView.findViewById(R.id.tv_list);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) listener.onItemClick(v, getPosition());
        }
    }

    /**
     * 设置item点击事件
     **/
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyViewHolder holder, int position) { /*        holder.ivList.setBackgroundResource(mData.get(position).icon);*/
        holder.tvList.setText(TITLES[position]);
    }

    @Override
    public int getItemCount() {
        return TITLES.length;
    }


}
