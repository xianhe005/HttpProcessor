package com.hxh.httpprocessor.api.impl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.hxh.httpprocessor.api.ICallback;
import com.hxh.httpprocessor.api.Processor;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HXH at 2019/8/28
 * OkHttp实现
 */
public class OkHttpProcessor implements Processor {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private OkHttpClient mOkHttpClient;

    public OkHttpProcessor(Context context) {
        File cacheDir = context.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(cacheDir.getAbsoluteFile(), cacheSize));
        mOkHttpClient = builder.build();
    }

    @Override
    public void get(String url, Map<String, Object> map, ICallback callback) {
        String finalUrl = generateGetUrl(url, map);
        Request request = new Request.Builder()
                .url(finalUrl)
                .get()
                .addHeader("user-agent", "a")
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mHandler.post(() -> callback.onFailure(e.toString()));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    mHandler.post(() -> callback.onSuccess(result));
                } else {
                    String error = response.message();
                    mHandler.post(() -> callback.onFailure(error));
                }
            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> map, ICallback callback) {
        FormBody formBody = generateFormBody(map);
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .addHeader("user-agent", "a")
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mHandler.post(() -> callback.onFailure(e.toString()));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    mHandler.post(() -> callback.onSuccess(result));
                } else {
                    String error = response.message();
                    mHandler.post(() -> callback.onFailure(error));
                }
            }
        });
    }
}
