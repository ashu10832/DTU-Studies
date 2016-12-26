package com.example.dell.dtustudies;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static android.content.ContentValues.TAG;
import static android.content.Context.DOWNLOAD_SERVICE;


public class PapersFragment extends Fragment {
    private static final String TAG = "PapersFragment";

    public PapersFragment() {
        // Required empty public constructor
    }



    public static PapersFragment newInstance(String param1, String param2) {
        PapersFragment fragment = new PapersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    List<Document> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_papers, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView_papers);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        list = new ArrayList<>();
        String subject = getActivity().getIntent().getExtras().getString("Subject");
        Log.e(TAG, "Subject " + subject);
        prepareList(subject);
        CustomAdapter adapter = new CustomAdapter(list);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Document document = list.get(position);
                DownloadManager dm = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(document.getUrl()));
                long enqueue = dm.enqueue(request);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return rootView;
    }

    private void prepareList( String subject) {
        if(subject.equals("MATHS"))
        {
            if(getActivity().getIntent().getExtras().getString("Group").equals("A")) {

            }
            else if(getActivity().getIntent().getExtras().getString("Group").equals("B"))
            {

            }
        }

        else if(subject.equals("CHEMISTRY"))
        {
            list.add(new Document("CHEMISTRY", "Papers", "https://drive.google.com/uc?export=download&id=0B-qYGNdFzalddmhLenJXMGxUWXc", "Assignment", "Important Questions from Titration, Spectroscopy and Thermal Methods of Analysis"));

        }
    }

    }


