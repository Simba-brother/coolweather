package com.example.a21966.coolweather.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a21966.coolweather.R;
import com.example.a21966.coolweather.util.HttpCallbackListener;
import com.example.a21966.coolweather.util.HttpUtil;
import com.example.a21966.coolweather.util.Utility;

/**
 * Created by 21966 on 2018/2/12.
 */

public class WeatherActivity extends Activity implements View.OnClickListener {
    private LinearLayout weatherInfoLayout;
    private TextView cityNameText;
    private TextView publishText;
    private TextView weatherDespText;
    private TextView temp1Text;
    private TextView temp2Text;
    private TextView currentDateText;
    private Button switchCity;
    private Button refeshWeather;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_layout);
        weatherInfoLayout = (LinearLayout)findViewById(R.id.weather_info_layout);
        cityNameText = (TextView)findViewById(R.id.city_name);
        publishText = (TextView)findViewById(R.id.publish_text);
        weatherDespText = (TextView)findViewById(R.id.weather_desp);
        temp1Text = (TextView)findViewById(R.id.temp1);
        temp2Text = (TextView)findViewById(R.id.temp2);
        currentDateText = (TextView)findViewById(R.id.current_date);
        String countyCode = getIntent().getStringExtra("county_code");
       // switchCity = (Button)findViewById(R.id.sw)
       // refeshWeather = (Button)findViewById(R.id)
//        String countryCode = getIntent().getStringExtra("countryCode");
//        if (!TextUtils.isEmpty(countryCode)){
//            publishText.setText("同步中...");
//            weatherInfoLayout.setVisibility(View.INVISIBLE);
//            cityNameText.setVisibility(View.INVISIBLE);
//            //queryWeatherCode(countryCode);
//        }else {
//            showWeather();
//        }
        queryWeatherInfo("101190404");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
        }
    }
    private void queryWeatherInfo(String weatherCode){
        String address = "http://www.weather.com.cn/data/cityinfo/" + weatherCode +".html";
        queryFromServer(address,"weatherCode");
    }
    private void queryFromServer(final String address, final String type ){

        HttpUtil.sentHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {

                if ("weatherCode".equals(type)){

                    Utility.handleWeatherResponse(WeatherActivity.this,response);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showWeather();
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        publishText.setText("同步失败");
                    }
                });
            }
        });
    }
    private void showWeather(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        cityNameText.setText(prefs.getString("city_name",""));
        temp1Text.setText(prefs.getString("temp1", ""));
        temp2Text.setText(prefs.getString("temp2", ""));
        weatherDespText.setText(prefs.getString("weather_desp", ""));
        publishText.setText("今天" + prefs.getString("publish_time", "") + "发布");
        currentDateText.setText(prefs.getString("current_date", ""));
        weatherInfoLayout.setVisibility(View.VISIBLE);
        cityNameText.setVisibility(View.VISIBLE);
    }
}
