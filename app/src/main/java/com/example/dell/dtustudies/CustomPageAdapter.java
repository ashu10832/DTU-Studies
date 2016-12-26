package com.example.dell.dtustudies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Dell on 14-Dec-16.
 */

public class CustomPageAdapter extends FragmentPagerAdapter {
    public CustomPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return new NotesFragment();
        }
        else if(position==1)
        {
            return new PapersFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
        {
            return "NOTES";
        }
        else
        {
            return "PAPERS";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
