package com.hxh.httpprocessor;

import android.app.Application;

import com.hxh.httpprocessor.api.HttpHelper;
import com.hxh.httpprocessor.api.impl.OkHttpProcessor;
import com.hxh.httpprocessor.api.impl.VolleyProcessor;
import com.hxh.httpprocessor.api.impl.XUtilsProcessor;

/**
 * Created by HXH at 2019/8/28
 * Application
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 网络请求实现类初始化
        //HttpHelper.init(new OkHttpProcessor(this));
        //HttpHelper.init(new VolleyProcessor(this));
        HttpHelper.init(new XUtilsProcessor(this));
    }
}
