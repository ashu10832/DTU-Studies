package com.example.dell.dtustudies;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Movie;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.List;

import static android.R.id.list;
import static android.content.Context.DOWNLOAD_SERVICE;
import static android.os.Build.VERSION_CODES.M;
import static java.security.AccessController.getContext;

/**
 * Created by Dell on 16-Dec-16.
 */

class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.MyViewHolder> {

    private List<Document> documentList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        ImageView downloadBtn;
        private ImageView shareBtn;
        public Context context;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.heading);
            desc = (TextView) view.findViewById(R.id.desc);
            downloadBtn = (ImageView) view.findViewById(R.id.download_btn);
            context = view.getContext();
            shareBtn = (ImageView) view.findViewById(R.id.share_btn);
        }
    }

    CustomAdapter(List<Document> list)
    {
        this.documentList = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.document_cardview,parent,false);
        return new MyViewHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Document document = documentList.get(position);
        holder.title.setText(document.getTitle());
        holder.desc.setText(document.getDescription());
        holder.title.setTextColor(holder.context.getResources().getColor(R.color.black));
        holder.desc.setTextColor(holder.context.getResources().getColor(R.color.colorAccent));
        holder.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file = new File(Environment.getExternalStorageDirectory()+"/DtuStudies");
                if(!file.exists())
                {
                    file.mkdir();
                }
                File doc = new File(Environment.getExternalStorageDirectory()+"/DtuStudies",document.getSubject()+"_"+document.getTitle()+".pdf");
                if(doc.exists()) {
                    Toast.makeText(holder.context, "This file already exists!!", Toast.LENGTH_SHORT).show();
                    File doc1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/DtuStudies/" + document.getSubject()+"_"+document.getTitle()+".pdf");
                    Intent  i  = new Intent(Intent.ACTION_VIEW);
                    i.setDataAndType(Uri.fromFile(doc1),"application/pdf");
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    holder.context.startActivity(i);
                }
                else
                {
                    new AlertDialog.Builder(holder.context)
                            .setTitle("Download")
                            .setMessage("Are you sure you want to download " + document.getTitle())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    DownloadManager dm = (DownloadManager) holder.context.getSystemService(DOWNLOAD_SERVICE);
                                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(document.getUrl()));
                                    request.setTitle(document.getTitle());
                                    request.setDescription("Downloading");
                                    request.setDestinationInExternalPublicDir("/DtuStudies",document.getSubject()+"_"+document.getTitle()+".pdf");
                                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                    dm.enqueue(request);
                                    Toast.makeText(holder.context,"Downloading your file.. Please wait!", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(R.drawable.ic_cloud_download)
                            .show();
                }
            }
        });

        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String message = null;
                message = "Hi,this is from the *DTU Studies* App. \n*Title:* " + document.getTitle() + "\n*Description:* "  + document.getDescription() + "\nJust Click the Link below to download the File :) \n\n" + document.getUrl();
                sendIntent.putExtra(Intent.EXTRA_TEXT,message);
                sendIntent.setType("text/plain");
                holder.context.startActivity(sendIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return documentList.size();
    }


}
