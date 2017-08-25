package com.example.nitesh.payu.mvvm.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.nitesh.payu.R;
import com.example.nitesh.payu.mvvm.model.Project;
import com.example.nitesh.payu.mvvm.view.fragment.FilterResultFragment;
import com.example.nitesh.payu.mvvm.view.fragment.HomeFragment;
import com.example.nitesh.payu.mvvm.view.fragment.ProjectDetailFragment;
import com.example.nitesh.payu.util.Constants;
import com.example.nitesh.payu.util.FragmentHelper;
import com.example.nitesh.payu.util.Network;
import com.example.nitesh.payu.util.retrofit.APIInterface;


public class HomeActivity extends AppCompatActivity implements Constants.ProjectClickListener, Constants.ProjectFilterListener {

    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FragmentHelper.addFragment(this, R.id.home_container, new HomeFragment());
    }

    @Override
    public void onProjectClicked(Project project) {
        if (!Network.isConnected(this)) {
            Toast.makeText(this, R.string.network_err, Toast.LENGTH_LONG).show();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BundleKeys.URL, APIInterface.BASE_URL.concat(project.getUrl()));
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        fragment.setArguments(bundle);
        FragmentHelper.replaceAndAddFragment(this, R.id.home_container, fragment);
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
}
