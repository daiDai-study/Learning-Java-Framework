package org.daistudy.springframework.ioc.xml.utils;

import okhttp3.OkHttpClient;

public class OkhttpUtilsWithObject {
    private OkHttpClient okHttpClient;
    public OkHttpClient getInstance(){
        if(okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder().build();
        }
        return okHttpClient;
    }
}
