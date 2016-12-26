package com.example.dell.dtustudies;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import static android.R.id.list;

public class SubjectActivity extends AppCompatActivity {
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);;
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.recycleView_subject);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        list = new ArrayList<>();
        createList();
        SubjectAdapter adapter = new SubjectAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(this, recyclerView, new ClickListener() {
           @Override
           public void onClick(View view, int position) {
               changeActivity(position);
           }

           @Override
           public void onLongClick(View view, int position) {
           }

       }));
    }

    private void changeActivity(int position) {
               String subject = list.get(position);
        if(subject.equals("MATHS")||subject.equals("PHYSICS"))
        {
            Intent group = new Intent(SubjectActivity.this,GroupActivity.class);
            group.putExtra("Subject",subject);
            startActivity(group);
        }
        else {
            Intent i = new Intent(SubjectActivity.this, MainActivity.class);
            i.putExtra("Subject", subject);
            startActivity(i);
        }
    }

    private void createList()
    {
            list.add("MATHS");
            list.add("PHYSICS");
            list.add("CHEMISTRY");
            list.add("BME");
            list.add("COMMUNICATION SKILLS");
            list.add("ELECTRICAL");
            list.add("PROGRAMMING");
            list.add("ENVIRONMENTAL");
        }

}
