package com.hxh.httpprocessor.api;

import java.util.Map;

/**
 * Created by HXH at 2019/8/28
 * 接口入口类
 */
public class HttpHelper implements Processor {

    private static final HttpHelper sInstance = new HttpHelper();
    private Processor mHttpProcessor;

    private HttpHelper() {

    }

    public static HttpHelper obtain() {
        return sInstance;
    }

    public static void init(Processor processor) {
        sInstance.mHttpProcessor = processor;
    }

    @Override
    public void get(String url, Map<String, Object> map, ICallback callback) {
        mHttpProcessor.get(url, map, callback);
    }

    @Override
    public void post(String url, Map<String, Object> map, ICallback callback) {
        mHttpProcessor.post(url, map, callback);
    }
}
