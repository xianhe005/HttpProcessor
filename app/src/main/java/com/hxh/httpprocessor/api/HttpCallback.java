package com.hxh.httpprocessor.api;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by HXH at 2019/8/28
 * 泛型解析
 */
public abstract class HttpCallback<R> implements ICallback {

    private static final Gson sGson = new Gson();

    @Override
    public void onSuccess(String result) {
        try {
            Class<?> clazz = getClazz(this);
            R r = (R) sGson.fromJson(result, clazz);
            onSuccess(r);
        } catch (Exception e) {
            e.printStackTrace();
            onFailure(e.toString());
        }
    }

    @SuppressWarnings("all")
    private static Class<?> getClazz(Object obj) {
        try {
            Type type = obj.getClass().getGenericSuperclass();
            return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract void onSuccess(R r);
}
