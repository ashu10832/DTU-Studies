package com.example.dell.dtustudies;

import android.app.DownloadManager;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;

import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Dell on 16-Dec-16.
 */

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.MyViewHolder> {

    private List<Document> documentList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.heading);
            desc = (TextView) view.findViewById(R.id.desc);
        }
    }

    public CustomAdapter(List<Document> list)
    {
        this.documentList = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.document_cardview,parent,false);
        return new MyViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Document document = documentList.get(position);
        holder.title.setText(document.getTitle());
        holder.desc.setText(document.getDescription());
        holder.title.setTextColor(R.color.black);
        holder.desc.setTextColor(R.color.black);
    }
    @Override
    public int getItemCount() {
        return documentList.size();
    }


}
