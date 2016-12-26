package com.example.dell.dtustudies;

import android.view.View;

/**
 * Created by Dell on 16-Dec-16.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
