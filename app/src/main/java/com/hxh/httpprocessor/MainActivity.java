package com.hxh.httpprocessor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.hxh.httpprocessor.api.HttpCallback;
import com.hxh.httpprocessor.api.HttpHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "hxh";

    private String mUrl = "http://59.110.162.30/app_updater_version.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv);
        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(v ->
                HttpHelper.obtain().get(mUrl, null, new HttpCallback<UpdateBean>() {
                    @Override
                    public void onFailure(String error) {
                        Log.i(TAG, "onFailure: " + error);
                    }

                    @Override
                    public void onSuccess(UpdateBean updateBean) {
                        Log.i(TAG, "onSuccess: " + updateBean);
                        tv.setText(updateBean.toString());
                    }
                }));

    }
}
