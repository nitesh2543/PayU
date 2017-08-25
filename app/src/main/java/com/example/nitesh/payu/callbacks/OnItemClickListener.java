package com.example.nitesh.payu.callbacks;

import android.view.View;

/**
 * Created by nitesh on 13/8/17.
 */

public interface OnItemClickListener<T> {

    void onItemClick(T t, View view, int position);
}
