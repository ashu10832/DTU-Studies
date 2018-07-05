package com.example.dell.dtustudies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.dell.dtustudies.R.raw.timetable_group_a;
import static com.example.dell.dtustudies.R.raw.timetable_group_b;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "NavActivity";

    List<String> list;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            Intent group = new Intent(NavActivity.this,GroupActivity.class);
            group.putExtra("Subject",subject);
            startActivity(group);
        }
        else {
            Intent i = new Intent(NavActivity.this, MainActivity.class);
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.group_a) {
            
            openPDF(id);
           
        } else if (id == R.id.group_b) {
            openPDF(id);

        } else if (id == R.id.ask_a_doubt) {

        } else if (id == R.id.developer) {

        } else if (id == R.id.feedback) {
            Intent feedback = new Intent(this,FeedbackActivity.class);
            startActivity(feedback);
        } else if (id == R.id.share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
    
    
    public void openPDF(int id)
    {      
        String name = null;
        int table_id;
        if(id==R.id.group_a)
        {
            name = "Group_A_TimeTable.pdf";
            table_id = timetable_group_a;
        }
        else
        {
            name = "Group_B_TimeTable.pdf";
            table_id = timetable_group_b;
        }
        try {
            copyFile(getResources().openRawResource(table_id), new FileOutputStream(new File(Environment.getExternalStorageDirectory()+"/testFiles", name)));
            Log.i(TAG, "openPDF: " + String.valueOf(table_id));
        } catch (IOException e) {
            Toast.makeText(NavActivity.this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        File pdfFile = new File(Environment.getExternalStorageDirectory()+"/testFiles/"+ name);
        Uri path = Uri.fromFile(pdfFile);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setDataAndType(path, "application/pdf");
        startActivity(intent);
    }

}
