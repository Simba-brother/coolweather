package com.example.a21966.coolweather.util;

import com.example.a21966.coolweather.model.CoolWeatherDB;
import com.example.a21966.coolweather.model.Province;

/**
 * Created by 21966 on 2018/2/11.
 */

public class Utility {

    public Utility(CoolWeatherDB coolWeatherDB){
        Province province = new Province();
        province.setProvinceName("北京");
        province.setProvinceCode("101010100");
        coolWeatherDB.saveProvince(province);
    }

}
