package com.example.nitesh.payu.util;

import android.view.View;

import com.example.nitesh.payu.mvvm.model.Project;

/**
 * Created by nitesh on 13/8/17.
 */

public class Constants {

    public interface ViewType{
        int ITEM = 0;
        int FOOTER = 1;
    }

    public interface BundleKeys {
        String URL = "url";
        String POSITION = "pos";
        String NAVIGATION = "navigation";
    }

    public interface ProjectType{
        String NORMAL = "normal";
        String LOVELY = "lovely";
    }

    public interface ProjectClickListener{
        void onProjectClicked(Project project , String type);
    }

    public interface ProjectListListener {
        void onSearchResult(int resultCount);
    }

    public interface ProjectFilterListener {
        void onFilterClicked();
    }

    public interface LeftMenuListener{
        void onLeftMenuClicked(String navigation , View v,int position);
    }

    public interface LeftMenuClickListener{
        void onLeftMenuClicked(String navigation ,int position);
    }
}
