package com.example.dell.dtustudies;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.support.design.widget.TabLayout;

import com.bumptech.glide.Glide;

import static android.view.KeyCharacterMap.load;
import static com.example.dell.dtustudies.R.id.pager;
import static com.example.dell.dtustudies.R.id.subject;

public class MainActivity extends AppCompatActivity {

    String subject;
    CollapsingToolbarLayout collapsingToolbarLayout = null;
    Toolbar toolbar = null;
    ImageView header;


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        subject = getIntent().getExtras().getString("Subject");
        toolbarTextAppearance();
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new CustomPageAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this,R.color.colorPrimary));
        collapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(0);
        }
        setToolbarHeader(subject);


    }

    private void setToolbarHeader(String subject) {
        header = (ImageView) findViewById(R.id.imageView);
        if (subject.equals("CHEMISTRY")) {
            Glide.with(this).load(R.drawable.chemistry_head).into(header);

        } else if (subject.equals("MATHS")) {
            Glide.with(this).load(R.drawable.maths_head).into(header);

        } else if (subject.equals("PROGRAMMING")) {
            Glide.with(this).load(R.drawable.computer_head).into(header);

        } else if (subject.equals("PHYSICS")) {
            Glide.with(this).load(R.drawable.physics_head).into(header);

        } else if (subject.equals("ELECTRICAL")) {
            Glide.with(this).load(R.drawable.electrical_head).into(header);

        } else if (subject.equals("COMMUNICATION SKILLS")) {
            Glide.with(this).load(R.drawable.communication_head).into(header);

        } else if (subject.equals("ENVIRONMENTAL")) {
            Glide.with(this).load(R.drawable.environmental_head).into(header);

        } else if (subject.equals("BME")) {
            Glide.with(this).load(R.drawable.mechanical_head).into(header);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                this.finish();
                return true;
            }
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void toolbarTextAppearance() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
        collapsingToolbarLayout.setTitle(subject);
    }
}
