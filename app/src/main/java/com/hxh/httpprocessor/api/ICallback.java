package com.hxh.httpprocessor.api;

/**
 * Created by HXH at 2019/8/28
 * 回调接口
 */
public interface ICallback {

    void onSuccess(String result);

    void onFailure(String error);
}
