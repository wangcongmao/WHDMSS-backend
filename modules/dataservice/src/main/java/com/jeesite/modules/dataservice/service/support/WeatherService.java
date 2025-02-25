package com.jeesite.modules.dataservice.service.support;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.jeesite.common.web.http.ResultUtils;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    /**
     * 未来3天天气
     * @return
     */
    public List<String> getCurrentWeather() {
        // 1. 创建HttpRequest对象 - 指定好 url 地址
//        HttpRequest httpRequest = new HttpRequest("https://api.caiyunapp.com/v2.6/WSdGXS69DBpBFflP/101.6656,39.2072/realtime?alert=true");
        HttpRequest httpRequest = new HttpRequest("https://api.caiyunapp.com/v2.6/WSdGXS69DBpBFflP/101.6656,39.2072/daily?dailysteps=3");
        // 2. 设置请求方式，默认是GET请求
        httpRequest.setMethod(Method.GET);

        // 5. 执行请求，得到http响应类
        HttpResponse execute = httpRequest.execute();

        // 6. 解析这个http响应类，可以获取到响应主体、cookie、是否请求成功等信息
        boolean ok = execute.isOk(); // 是否请求成功 判断依据为：状态码范围在200~299内

        String body = execute.body();   // 获取响应主体
//        System.out.println(body);

        List<String> weatherSummaryList = new ArrayList<>();
        JSONObject response = new JSONObject(body);
        JSONArray dailyArray = response.getJSONObject("result").getJSONObject("daily").getJSONArray("temperature");
        String whichDay = "";

        for (int i = 0; i < dailyArray.length(); i++) {
            JSONObject day = dailyArray.getJSONObject(i);
            String date = day.getString("date");
            double dayAvgTemp = day.getDouble("avg");
            switch (i) {
                case 0:
                    whichDay = "今天 ";
                    break;
                case 1:
                    whichDay = "明天 ";
                    break;
                case 2:
                    whichDay = "后天 ";
                    break;
            }

            // 获取夜晚气温（从temperature_20h_32h数组中提取）
            JSONObject nightTemp = response.getJSONObject("result").getJSONObject("daily").getJSONArray("temperature_20h_32h").getJSONObject(i);
            double nightAvgTemp = nightTemp.getDouble("avg");

            // 获取风速（从wind_20h_32h数组中提取）
            JSONObject wind = response.getJSONObject("result").getJSONObject("daily").getJSONArray("wind_20h_32h").getJSONObject(i);
            double avgWindSpeed = wind.getJSONObject("avg").getDouble("speed");

            // 将结果格式化为中文字符串
            String weatherSummary = String.format( whichDay + "白天%.2f°C，夜晚%.2f°C，风速%.2f m/s",
                    dayAvgTemp, nightAvgTemp, avgWindSpeed);  // 假设全天平均温度等于白天平均温度

            weatherSummaryList.add(weatherSummary);
        }
        return weatherSummaryList;
    }

    /**
     * 未来24小时天气情况 https://platform.caiyunapp.com/api/manage
     * @param
     * @return
     */
    public List<DateTemp> getFuture24hoursWeather() throws JSONException {
        // 1. 创建HttpRequest对象 - 指定好 url 地址
//        HttpRequest httpRequest = new HttpRequest("https://api.caiyunapp.com/v2.6/WSdGXS69DBpBFflP/101.6656,39.2072/realtime?alert=true");
        HttpRequest httpRequest = new HttpRequest("https://api.caiyunapp.com/v2.6/WSdGXS69DBpBFflP/101.6656,39.2072/hourly?hourlysteps=24");
        // 2. 设置请求方式，默认是GET请求
        httpRequest.setMethod(Method.GET);

        // 5. 执行请求，得到http响应类
        HttpResponse execute = httpRequest.execute();

        // 6. 解析这个http响应类，可以获取到响应主体、cookie、是否请求成功等信息
        boolean ok = execute.isOk(); // 是否请求成功 判断依据为：状态码范围在200~299内

        String body = execute.body();   // 获取响应主体
//        System.out.println(body);

        List<String> weatherSummaryList = new ArrayList<>();
        JSONObject response = new JSONObject(body);
        JSONArray hourlyArray = response.getJSONObject("result").getJSONObject("hourly").getJSONArray("temperature");
        List<DateTemp> res = new ArrayList<>();
        for (int i = 0; i < hourlyArray.length(); i++) {
            JSONObject day = hourlyArray.getJSONObject(i);
            String date = day.getString("datetime").substring(0, day.getString("datetime").indexOf('+'));
            double temp = day.getDouble("value");
            DateTemp dateTemp = new DateTemp();
            dateTemp.setDate(date);
            dateTemp.setTemperature(temp);
            res.add(new DateTemp(dateTemp));
        }
        return (res);
    }

    @Data
    public class DateTemp {
        String date;
        double temperature;
        public DateTemp() {}
        public DateTemp(DateTemp dateTemp) {
            setDate(dateTemp.getDate());
            setTemperature(dateTemp.getTemperature());
        }
    }

    /**
     * 预警信息 https://platform.caiyunapp.com/api/manage
     * @param
     * @return
     */
    public Alert getWarning() throws JSONException {
        // 1. 创建HttpRequest对象 - 指定好 url 地址
//        HttpRequest httpRequest = new HttpRequest("https://api.caiyunapp.com/v2.6/WSdGXS69DBpBFflP/101.6656,39.2072/realtime?alert=true");
        HttpRequest httpRequest = new HttpRequest("https://api.caiyunapp.com/v2.6/WSdGXS69DBpBFflP/116.3176,39.9760/realtime?alert=true");
        // 2. 设置请求方式，默认是GET请求
        httpRequest.setMethod(Method.GET);

        // 5. 执行请求，得到http响应类
        HttpResponse execute = httpRequest.execute();

        // 6. 解析这个http响应类，可以获取到响应主体、cookie、是否请求成功等信息
        boolean ok = execute.isOk(); // 是否请求成功 判断依据为：状态码范围在200~299内

        String body = execute.body();   // 获取响应主体
//        System.out.println(body);

        List<String> weatherSummaryList = new ArrayList<>();
        JSONObject response = new JSONObject(body);
        JSONObject warningObject = response.getJSONObject("result");
        if (warningObject.has("alert")) {
            JSONObject alert = warningObject.getJSONObject("alert");
            Alert alert1 = new Alert();
            alert1.setTitle(alert.getString("title"));
            alert1.setDescription(alert.getString("description"));
            return (alert1);
        } else {
            Alert alert1 = new Alert();
            alert1.setTitle("近期没有预警信息");
            alert1.setDescription("");
            return (alert1);
        }
//        List<DateTemp> res = new ArrayList<>();
//        for (int i = 0; i < hourlyArray.length(); i++) {
//            JSONObject day = hourlyArray.getJSONObject(i);
//            String date = day.getString("datetime").substring(0, day.getString("datetime").indexOf('+'));
//            double temp = day.getDouble("value");
//            DateTemp dateTemp = new DateTemp();
//            dateTemp.setDate(date);
//            dateTemp.setTemperature(temp);
//            res.add(new DateTemp(dateTemp));
//        }
    }

    @Data
    public class Alert {
        String title;
        String description;
        public Alert() {}
        public Alert(Alert alert) {
            setTitle(alert.getTitle());
            setDescription(alert.getDescription());
        }
    }
}
