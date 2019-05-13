package com.example.bankapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    Context mContext;
    List<BankAccount> mData;


    public Adapter(Context mContext, List<BankAccount> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_item, viewGroup, false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
       /* myViewHolder.courseTitle.setText(mData.get(i).getSubject());
        myViewHolder.courseTitle.setOnClickListener((View) -> {
            Intent accessCourse = new Intent(mContext, ShowCourseActivity.class);
            accessCourse.putExtra("Course", mData.get(i));
            mContext.startActivity(accessCourse);
        });*/




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle;


        public myViewHolder(View itemView){
            super(itemView);
            courseTitle = itemView.findViewById(R.id.accountName);




        }
    }
}
