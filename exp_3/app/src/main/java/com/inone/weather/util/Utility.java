package com.inone.weather.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.inone.weather.db.City;
import com.inone.weather.db.County;
import com.inone.weather.db.Province;
import com.inone.weather.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/*
* 解析和处理JSON数据
* */
public class Utility {
    /*
    * 解析和处理服务器返回的省级数据
    * */
    private static final String TAG = "Utility";
    public static  boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for(int i =0; i< allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


    /*
     *
     * 解析和处理服务器返回市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for(int i = 0;i < allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 解析和处理服务器返回的县级数据
     */

    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();   //数据存在数据库中
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


    /*
     * 将返回的JSON数据解析成weather实体类
     * */

    public static Weather handleWeatherResponse(String response){

        // JSONObject和JSONArray将天气的主体数据解析出来
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent =jsonArray.getJSONObject(0).toString();
            //fromJson()方法将Json数据转化成weather对象
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){

            e.printStackTrace();
        }
        return null;
    }

}
