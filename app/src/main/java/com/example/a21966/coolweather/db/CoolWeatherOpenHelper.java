package com.example.a21966.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 21966 on 2018/2/10.
 */

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_PROVINCE = "create table Province (" +
            " id integer primary key autoincrement," +
            " province_name text," +
            " province_code text)";
//    public static final String CREATE_CITY = "create table City (" +
//            " id integer primary key autoincrement" +
//            " city_name text" +
//            " city_code text" +
//            " province_id integer)";
//    public static final String CREATE_COUNTY = "create table County (" +
//            " id integer primary key" +
//            " country_name text" +
//            " country_code text" +
//            " city_id integer)";
    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_PROVINCE);
//        sqLiteDatabase.execSQL(CREATE_CITY);
//        sqLiteDatabase.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
