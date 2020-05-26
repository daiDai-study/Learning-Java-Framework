package org.daistudy.springframework.ioc.java.utils;

import okhttp3.OkHttpClient;

public class OkHttpUtils {
    private static OkHttpClient okHttpClient;
    public static OkHttpClient getInstance(){
        if(okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder().build();
        }
        return okHttpClient;
    }
}
