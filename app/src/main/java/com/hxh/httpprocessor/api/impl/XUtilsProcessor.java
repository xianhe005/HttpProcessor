package com.hxh.httpprocessor.api.impl;

import android.app.Application;
import android.content.Context;

import com.hxh.httpprocessor.api.ICallback;
import com.hxh.httpprocessor.api.Processor;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by HXH at 2019/8/28
 * XUtils实现
 */
public class XUtilsProcessor implements Processor {

    public XUtilsProcessor(Application context) {
        x.Ext.init(context);
    }

    @Override
    public void get(String url, Map<String, Object> map, ICallback callback) {
        RequestParams params = new RequestParams(generateGetUrl(url, map));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> map, ICallback callback) {
        RequestParams params = new RequestParams(url);
        if (map != null && !map.isEmpty()) {
            for (String s : map.keySet()) {
                params.addBodyParameter(s, map.get(s).toString());
            }
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFailure(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
