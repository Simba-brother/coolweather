package com.example.a21966.coolweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.a21966.coolweather.model.City;
import com.example.a21966.coolweather.model.CoolWeatherDB;
import com.example.a21966.coolweather.model.County;
import com.example.a21966.coolweather.model.Province;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 21966 on 2018/2/11.
 */

public class Utility {
    //网三张表里插
    public  synchronized  static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB){
        Province province = new Province();
        province.setProvinceName("江苏");
        province.setProvinceCode("19");
        coolWeatherDB.saveProvince(province);
        return true;
    }
    public  static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, int provinceId){
        City city = new City();
        city.setCityName("苏州");
        city.setCityCode("1904");
        city.setProvinceId(provinceId);
        coolWeatherDB.saveCity(city);
        return true;
    }
    public static boolean handleCountriesResponses(CoolWeatherDB coolWeatherDB,int cityId){
        County county =new County();
        county.setCountyName("昆山");
        county.setCountyCode("101190404");
        county.setCityId(cityId);
        coolWeatherDB.saveCountry(county);
        return true;
    }
    public static void handleWeatherResponse(Context context,String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject weatherInfo = jsonObject.getJSONObject("weatherinfo");
            String cityName = weatherInfo.getString("city");
            String weatherCode = weatherInfo.getString("cityid");
            String temp1 = weatherInfo.getString("temp1");
            String temp2 = weatherInfo.getString("temp2");
            String weatherDesp = weatherInfo.getString("weather");
            String publishTime = weatherInfo.getString("ptime");
            saveWeatherInfo(context,cityName,weatherCode,temp1,temp2,weatherDesp,publishTime);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public static void saveWeatherInfo(Context context,String cityName,String weatherCode,String temp1, String temp2, String weatherDesp, String publishTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("city_selected",true);
        editor.putString("city_name", cityName);
        editor.putString("weather_code",weatherCode);
        editor.putString("temp1", temp1);
        editor.putString("temp2", temp2);
        editor.putString("weather_desp", weatherDesp);
        editor.putString("publish_time", publishTime);
        editor.putString("current_date", sdf.format(new Date()));
        editor.commit();
    }

}
