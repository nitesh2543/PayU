package com.example.nitesh.payu.mvvm.view.activity;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.nitesh.payu.R;
import com.example.nitesh.payu.mvvm.model.Project;
import com.example.nitesh.payu.mvvm.view.fragment.FilterResultFragment;
import com.example.nitesh.payu.mvvm.view.fragment.HomeFragment;
import com.example.nitesh.payu.mvvm.view.fragment.LovedProjectsFragment;
import com.example.nitesh.payu.mvvm.view.fragment.ProjectDetailFragment;
import com.example.nitesh.payu.util.Constants;
import com.example.nitesh.payu.util.FragmentHelper;
import com.example.nitesh.payu.util.Network;
import com.example.nitesh.payu.util.retrofit.APIInterface;


public class HomeActivity extends AppCompatActivity implements Constants.ProjectClickListener, Constants.ProjectFilterListener, Constants.LeftMenuClickListener {

    private boolean doubleBackToExitPressedOnce;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        FragmentHelper.addFragment(this, R.id.home_container, new HomeFragment());
        FragmentHelper.addFragment(this, R.id.navigation_view, new LovedProjectsFragment());
    }

    @Override
    public void onProjectClicked(Project project, String type) {
        if (!Network.isConnected(this)) {
            Toast.makeText(this, R.string.network_err_detail_page, Toast.LENGTH_LONG).show();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BundleKeys.URL, APIInterface.BASE_URL.concat(project.getUrl()));
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        fragment.setArguments(bundle);
        switch (type) {
            case Constants.ProjectType.LOVELY:
                if (FragmentHelper.getStackCount(this) > 0)
                    FragmentHelper.replaceFragment(this, R.id.home_container, fragment);
                else
                    FragmentHelper.replaceAndAddFragment(this, R.id.home_container, fragment);
                break;
            case Constants.ProjectType.NORMAL:
                FragmentHelper.replaceAndAddFragment(this, R.id.home_container, fragment);
                break;
        }
    }

    @Override
    public void onFilterClicked() {
        FragmentHelper.replaceAndAddFragment(this, R.id.home_container, new FilterResultFragment());
    }

    @Override
    public void onBackPressed() {
        if (FragmentHelper.getStackCount(this) > 0) {
            super.onBackPressed();
            return;
        }
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        } else {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, this.getResources().getString(R.string.exit_toast), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLeftMenuClicked(String navigation, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.BundleKeys.POSITION, position);
        bundle.putString(Constants.BundleKeys.NAVIGATION, navigation);
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }
}
