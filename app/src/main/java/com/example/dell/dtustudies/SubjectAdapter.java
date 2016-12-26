package com.example.dell.dtustudies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 19-Dec-16.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder> {
    List<String> subjectList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subject;

        public MyViewHolder(View view) {
            super(view);
            subject = (TextView) view.findViewById(R.id.subject);
        }
    }

    public SubjectAdapter(List<String> list) {
        this.subjectList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_cardview, parent, false);
        return new MyViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String subject = subjectList.get(position);
        holder.subject.setText(subject);
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }


}
