package com.example.nitesh.payu.util.retrofit;


import com.example.nitesh.payu.mvvm.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by nitesh on 13/8/17.
 */

public interface APIInterface {

    String BASE_URL = "https://www.kickstarter.com/";

    interface Header {
        String AUTHORIZATION = "Authorization";
    }

    interface Query {

    }

    interface Path {
    }

    @GET("http://starlord.hackerearth.com/kickstarter")
    Call<List<Project>> downloadProjects();
}
