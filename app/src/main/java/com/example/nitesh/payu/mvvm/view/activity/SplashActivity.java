package com.example.nitesh.payu.mvvm.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.nitesh.payu.R;
import com.example.nitesh.payu.mvvm.model.Project;
import com.example.nitesh.payu.util.Network;
import com.example.nitesh.payu.util.PreferenceManager;
import com.example.nitesh.payu.util.retrofit.APIClient;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        APIClient.create();
    }


    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                downloadData();
            }
        }, 3000);
    }

    private void downloadData() {
        final PreferenceManager preferenceManager = PreferenceManager.getsInstance(this);
        if (!preferenceManager.isFirstLaunch()) {
            startHomeActivity();
        } else {
            if (!Network.isConnected(this)) {
                Toast.makeText(this, R.string.network_err, Toast.LENGTH_LONG).show();
                return;
            }
            Call<List<Project>> call = APIClient.getAPIInterface().downloadProjects();
            call.enqueue(new Callback<List<Project>>() {
                @Override
                public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        if (Project.isSugarEntity(Project.class))
                            Project.deleteAll(Project.class);
                        for (Project project : response.body()) {
                            project.save();
                        }
                        preferenceManager.setFirstLaunch(false);
                        startHomeActivity();
                    }

                }

                @Override
                public void onFailure(Call<List<Project>> call, Throwable t) {
                    Log.e("Network", "onFailure: " + t.getMessage());
                    showErrorMsg(t.getMessage());
                    return;
                }
            });

        }
    }


    private void showErrorMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void startHomeActivity() {
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
    }
}
