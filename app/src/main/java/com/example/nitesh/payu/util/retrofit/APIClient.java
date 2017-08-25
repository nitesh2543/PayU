package com.example.nitesh.payu.util.retrofit;


import com.example.nitesh.payu.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nitesh on 13/8/17.
 */

public class APIClient {

    private static APIClient sInstance;
    private Retrofit retrofit = null;

    /**
     * This method must be called only once. i.e in onCreate method of {@link com.example.nitesh.payu.mvvm.view.activity.SplashActivity }.
     */

    public static void create() {
        if (sInstance == null) {
            synchronized (APIClient.class) {
                if (sInstance == null) {
                    sInstance = new APIClient();
                }
            }
        }
    }


    private APIClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        httpClient.addInterceptor(new ApplicationInterceptor());
        retrofit = new Retrofit.Builder()
                .baseUrl(APIInterface.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * Returns the instance of {@link Retrofit}.
     * This method must be called after {@link #create}.
     */
    public static Retrofit retrofit() {
        synchronized (APIClient.class) {
            if (sInstance == null)
                throw new IllegalStateException("APIClient instance is not created yet. Call APIClient.create() before calling getInstance()");

        }
        return sInstance.retrofit;
    }

    /**
     * Returns the instance of {@link APIInterface}.
     * This method must be called after {@link #create}.
     */
    public static APIInterface getAPIInterface() {
        return retrofit().create(APIInterface.class);
    }

    /**
     * An interceptor is used to modify each request before it is performed and alters the request header.
     * The advantage is, that you don’t have to add @Header("Authorization") to each API method definition.
     */
    private class ApplicationInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request OriginalRequest = chain.request();
          /**
            * Pass auth token if required.
            * Passing empty string because there is no authentication required in this project
            */
            String authToken = "";
            OriginalRequest = OriginalRequest.newBuilder()
                    .header(APIInterface.Header.AUTHORIZATION, authToken)
                    .build();


            Response response = chain.proceed(OriginalRequest);
            return response;
        }
    }
}
