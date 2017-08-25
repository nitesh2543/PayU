package com.example.nitesh.payu.util;

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
    }

    public interface ProjectClickListener{
        void onProjectClicked(Project project);
    }

    public interface ProjectListListener {
        void onSearchResult(int resultCount);
    }

    public interface ProjectFilterListener {
        void onFilterClicked();
    }
}
