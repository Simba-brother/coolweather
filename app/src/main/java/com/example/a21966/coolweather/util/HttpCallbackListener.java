package com.example.a21966.coolweather.util;

/**
 * Created by 21966 on 2018/2/10.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
