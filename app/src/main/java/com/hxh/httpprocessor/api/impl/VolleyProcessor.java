package com.hxh.httpprocessor.api.impl;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hxh.httpprocessor.api.ICallback;
import com.hxh.httpprocessor.api.Processor;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by HXH at 2019/8/28
 * Volley实现
 */
public class VolleyProcessor implements Processor {

    private RequestQueue mQueue;

    public VolleyProcessor(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, Map<String, Object> map, ICallback callback) {
        mQueue.add(new StringRequest(generateGetUrl(url, map),
                response -> {
                    try {
                        String result = new String(response.getBytes("ISO-8859-1"), "utf-8");
                        callback.onSuccess(result);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        callback.onFailure(e.toString());
                    }
                }, error -> callback.onFailure(error.toString())));
    }

    @Override
    public void post(String url, Map<String, Object> map, ICallback callback) {
        mQueue.add(new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        String result = new String(response.getBytes("ISO-8859-1"), "utf-8");
                        callback.onSuccess(result);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        callback.onFailure(e.toString());
                    }
                }, error -> callback.onFailure(error.toString())) {
            @Override
            protected Map<String, String> getParams() {
                return generateMap(map);
            }
        });
    }
}
