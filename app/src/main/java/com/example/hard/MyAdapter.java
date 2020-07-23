package com.example.hard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {
    public static class VH extends RecyclerView.ViewHolder{
        public  TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
        public VH(View v) {
            super(v);
            textView1 = v.findViewById(R.id.textView1);
            textView2 = v.findViewById(R.id.textView2);
            textView3 = v.findViewById(R.id.textView3);
            textView4 = v.findViewById(R.id.textView4);
            textView5 = v.findViewById(R.id.textView5);
            textView6 = v.findViewById(R.id.textView6);
            textView7 = v.findViewById(R.id.textView7);
            textView8 = v.findViewById(R.id.textView8);

        }
    }
    private List<LoginModel> mDatas;
    public MyAdapter(List<LoginModel> data) {
        this.mDatas = data;
    }
    @NonNull
    @Override
    public MyAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.VH holder, int position) {
        holder.textView1.setText(mDatas.get(position).getitem_id());
//        holder.textView2.setText(mDatas.get(position));
//        holder.textView3.setText(mDatas.get(position));
//        holder.textView4.setText(mDatas.get(position));
//        holder.textView5.setText(mDatas.get(position));
//        holder.textView6.setText(mDatas.get(position));
//        holder.textView7.setText(mDatas.get(position));
//        holder.textView8.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
            }
    });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
