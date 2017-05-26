package com.example.sonanew.scanner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SonaNew on 26.05.2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>  {
    private List<Data> dataList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new MyViewHolder(view);
    }

    public Adapter(List<Data> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data data = dataList.get(position);
        holder.bar.setText(data.getBarcode());
        holder.hamar.setText(data.getCount());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView bar, hamar;
        public MyViewHolder(View itemView) {
            super(itemView);
            bar = (TextView) itemView.findViewById(R.id.bar);
            hamar = (TextView) itemView.findViewById(R.id.hamar);
        }
    }
}
