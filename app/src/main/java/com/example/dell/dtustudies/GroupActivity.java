package com.example.dell.dtustudies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroupActivity extends AppCompatActivity {

    Button groupA,groupB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        groupA = (Button) findViewById(R.id.groupA);
        groupB = (Button) findViewById(R.id.groupB);
        groupA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainActivity = new Intent(GroupActivity.this,MainActivity.class);
                String subject = getIntent().getStringExtra("Subject");
                MainActivity.putExtra("Subject",subject);
                MainActivity.putExtra("Group","A");
                startActivity(MainActivity);
            }
        });
        groupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MainActivity = new Intent(GroupActivity.this,MainActivity.class);
                String subject = getIntent().getStringExtra("Subject");
                MainActivity.putExtra("Subject",subject);
                MainActivity.putExtra("Group","B");
                startActivity(MainActivity);
            }
        });
    }
}
