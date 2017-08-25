package com.example.nitesh.payu.util.retrofit;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by nitesh on 13/8/17.
 */

public class RetrofitUtil {

    public static Errors parseError(Response<?> response) {
        Converter<ResponseBody, Errors> converter =
                APIClient.retrofit()
                        .responseBodyConverter(Errors.class, new Annotation[0]);

        Errors error;
        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new Errors();
        }

        return error;
    }

    @NonNull
    public static RequestBody getRequestBody(String value) {
        return RequestBody.create(MultipartBody.FORM, value);
    }

}
