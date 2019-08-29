package com.hxh.httpprocessor.api;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

/**
 * Created by HXH at 2019/8/28
 * 网络请求接口
 */
public interface Processor {

    void get(String url, Map<String, Object> map, ICallback callback);

    void post(String url, Map<String, Object> map, ICallback callback);

    default FormBody generateFormBody(Map<String, Object> map) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String s : map.keySet()) {
            builder.add(s, map.get(s).toString());
        }
        return builder.build();
    }

    default Map<String, String> generateMap(Map<String, Object> map) {
        Map<String, String> sMap = new HashMap<>();
        for (String s : map.keySet()) {
            sMap.put(s, map.get(s).toString());
        }
        return sMap;
    }

    default String generateGetUrl(String url, Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        sb.append("?");
        for (String s : map.keySet()) {
            sb.append(s).append("=").append(map.get(s)).append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            return sb.substring(0, sb.length() - 1);
        }
        return s;
    }
}
