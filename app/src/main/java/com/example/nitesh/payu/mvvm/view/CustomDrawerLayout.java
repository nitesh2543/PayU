package com.example.nitesh.payu.mvvm.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by user on 8/26/2017.
 */

public class CustomDrawerLayout extends DrawerLayout{

    private RecyclerView mRecyclerView;

    public CustomDrawerLayout(Context context) {
        super(context);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (isInside(ev) && isDrawerOpen(Gravity.LEFT))
//            return false;
        return super.onInterceptTouchEvent(ev);
    }

    //    http://stackoverflow.com/questions/32134373/horiziontal-recyclerview-on-drawerlayout
    private boolean isInside(MotionEvent ev) { //check whether user touch recylerView or not
        return ev.getX() >= mRecyclerView.getLeft() && ev.getX() <= mRecyclerView.getRight() &&
                ev.getY() >= mRecyclerView.getTop() && ev.getY() <= mRecyclerView.getBottom();
    }

    public void set(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }
}
