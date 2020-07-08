package com.coding.controller.api;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import com.guanweiming.common.utils.JsonUtil;
import com.guanweiming.common.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author guanweiming
 */
@Slf4j
@RestController
public class WeatherController {

    private static final Cache<String, Weather> CACHE = CacheBuilder.newBuilder()
            .initialCapacity(100)
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    @ApiOperation("天气情况")
    @RequestMapping(value = "weather", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<Weather> weather(String city, String location) {
        Weather weather = getWeather(city, location);
        return Result.createBySimple(weather != null, weather, "请求失败");
    }

    @ApiOperation("天气情况")
    @RequestMapping(value = "weatherList", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<List<Weather>> weather(String city) {
        if (StringUtils.isBlank(city)) {
            return Result.createByErrorMessage("查询失败");
        }
        String[] split = city.split(",");
        List<Weather> weatherList = Lists.newArrayList(split).stream()
                .map(item -> getWeather(item, null))
                .collect(Collectors.toList());
        return Result.createBySimple(!CollectionUtils.isEmpty(weatherList), weatherList, "请求失败");
    }


    @Data
    private static class Weather {

        /**
         * city : 北京
         * cityid : 1
         * citycode : 101010100
         * date : 2020-05-14
         * week : 星期四
         * weather : 多云
         * temp : 22
         * temphigh : 29
         * templow : 15
         * img : 1
         * humidity : 52
         * pressure : 1001
         * windspeed : 2.4
         * winddirect : 南风
         * windpower : 2级
         * updatetime : 2020-05-14 07:23:00
         * aqi : {"so2":"3","so224":0,"no2":"43","no224":0,"co":"0.6","co24":"0.000","o3":"39","o38":0,"o324":0,"pm10":"118","pm1024":0,"pm2_5":"43","pm2_524":0,"iso2":2,"ino2":21,"ico":6,"io3":14,"io38":0,"ipm10":86,"ipm2_5":62,"aqi":84,"quality":"良","timepoint":"2020-05-14 07:00:00","aqiinfo":{"level":"二级","color":"#FFFF00","affect":"空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响","measure":"极少数异常敏感人群应减少户外活动"},"primarypollutant":"118"}
         * index : [{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"较适宜","detail":"天气较好，但因风力稍强，户外可选择对风力要求不高的运动，推荐您进行室内运动。"},{"iname":"紫外线指数","ivalue":"中等","detail":"属中等强度紫外线辐射天气，建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"},{"iname":"感冒指数","ivalue":"少发","detail":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},{"iname":"洗车指数","ivalue":"较不宜","detail":"较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"},{"iname":"空气污染扩散指数","ivalue":"良","detail":"气象条件有利于空气污染物稀释、扩散和清除。"},{"iname":"穿衣指数","ivalue":"热","detail":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"}]
         * daily : [{"date":"2020-05-14","week":"星期四","sunrise":"05:00","sunset":"19:21","night":{"weather":"多云","templow":"15","img":"1","winddirect":"东南风","windpower":"3级"},"day":{"weather":"多云","temphigh":"29","img":"1","winddirect":"南风","windpower":"4级"}},{"date":"2020-05-15","week":"星期五","sunrise":"04:59","sunset":"19:22","night":{"weather":"阴","templow":"15","img":"2","winddirect":"东南风","windpower":"3级"},"day":{"weather":"阴","temphigh":"26","img":"2","winddirect":"东南风","windpower":"3级"}},{"date":"2020-05-16","week":"星期六","sunrise":"04:58","sunset":"19:23","night":{"weather":"晴","templow":"16","img":"0","winddirect":"西北风","windpower":"3级"},"day":{"weather":"晴","temphigh":"28","img":"0","winddirect":"北风","windpower":"3级"}},{"date":"2020-05-17","week":"星期日","sunrise":"04:57","sunset":"19:23","night":{"weather":"多云","templow":"16","img":"1","winddirect":"北风","windpower":"4级"},"day":{"weather":"多云","temphigh":"26","img":"1","winddirect":"西风","windpower":"3级"}},{"date":"2020-05-18","week":"星期一","sunrise":"04:56","sunset":"19:24","night":{"weather":"多云","templow":"14","img":"1","winddirect":"北风","windpower":"4级"},"day":{"weather":"多云","temphigh":"23","img":"1","winddirect":"北风","windpower":"5级"}},{"date":"2020-05-19","week":"星期二","sunrise":"04:55","sunset":"19:25","night":{"weather":"晴","templow":"15","img":"0","winddirect":"南风","windpower":"3级"},"day":{"weather":"晴","temphigh":"29","img":"0","winddirect":"北风","windpower":"4级"}},{"date":"2020-05-20","week":"星期三","sunrise":"04:55","sunset":"19:26","night":{"weather":"晴","templow":"15","img":"0","winddirect":"南风","windpower":"3级"},"day":{"weather":"晴","temphigh":"30","img":"0","winddirect":"南风","windpower":"3级"}}]
         * hourly : [{"time":"09:00","weather":"多云","temp":"24","img":"1"},{"time":"10:00","weather":"多云","temp":"25","img":"1"},{"time":"11:00","weather":"多云","temp":"26","img":"1"},{"time":"12:00","weather":"多云","temp":"27","img":"1"},{"time":"13:00","weather":"多云","temp":"27","img":"1"},{"time":"14:00","weather":"多云","temp":"28","img":"1"},{"time":"15:00","weather":"多云","temp":"28","img":"1"},{"time":"16:00","weather":"多云","temp":"27","img":"1"},{"time":"17:00","weather":"多云","temp":"27","img":"1"},{"time":"18:00","weather":"多云","temp":"25","img":"1"},{"time":"19:00","weather":"多云","temp":"23","img":"1"},{"time":"20:00","weather":"多云","temp":"21","img":"1"},{"time":"21:00","weather":"多云","temp":"19","img":"1"},{"time":"22:00","weather":"多云","temp":"18","img":"1"},{"time":"23:00","weather":"多云","temp":"18","img":"1"},{"time":"00:00","weather":"多云","temp":"17","img":"1"},{"time":"01:00","weather":"多云","temp":"16","img":"1"},{"time":"02:00","weather":"多云","temp":"16","img":"1"},{"time":"03:00","weather":"多云","temp":"15","img":"1"},{"time":"04:00","weather":"多云","temp":"15","img":"1"},{"time":"05:00","weather":"多云","temp":"15","img":"1"},{"time":"06:00","weather":"多云","temp":"15","img":"1"},{"time":"07:00","weather":"多云","temp":"16","img":"1"},{"time":"08:00","weather":"多云","temp":"16","img":"1"}]
         */

        private String city;
        private int cityid;
        private String citycode;
        private String date;
        private String week;
        private String weather;
        private String temp;
        private String temphigh;
        private String templow;
        private String img;
        private String humidity;
        private String pressure;
        private String windspeed;
        private String winddirect;
        private String windpower;
        private String updatetime;


        /**
         * aqi : {"so2":"4","so224":"0","no2":"9","no224":"0","co":"0.330","co24":"0.000","o3":"61","o38":"0","o324":"0","pm10":"3","pm1024":"0","pm2_5":"2","pm2_524":"0","iso2":"2","ino2":"5","ico":"4","io3":"20","io38":"0","ipm10":"9","ipm2_5":"3","aqi":"9","primarypollutant":"o3","quality":"优","timepoint":"2020-06-14 09:00:00","aqiinfo":{"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}}
         * index : [{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"较不宜","detail":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},{"iname":"紫外线指数","ivalue":"最弱","detail":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},{"iname":"感冒指数","ivalue":"较易发","detail":"天气转凉，空气湿度较大，较易发生感冒，体质较弱的朋友请注意适当防护。"},{"iname":"洗车指数","ivalue":"不宜","detail":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"iname":"空气污染扩散指数","ivalue":"良","detail":"气象条件有利于空气污染物稀释、扩散和清除。"},{"iname":"穿衣指数","ivalue":"舒适","detail":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"}]
         * daily : [{"date":"2020-06-14","week":"星期日","sunrise":"06:03","sunset":"19:49","night":{"weather":"阴","templow":"19","img":"2","winddirect":"微风","windpower":"3级"},"day":{"weather":"小雨","temphigh":"25","img":"7","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-15","week":"星期一","sunrise":"06:03","sunset":"19:49","night":{"weather":"阴","templow":"19","img":"2","winddirect":"微风","windpower":"3级"},"day":{"weather":"阴","temphigh":"26","img":"2","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-16","week":"星期二","sunrise":"06:03","sunset":"19:50","night":{"weather":"阴","templow":"19","img":"2","winddirect":"微风","windpower":"3级"},"day":{"weather":"多云","temphigh":"25","img":"1","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-17","week":"星期三","sunrise":"06:03","sunset":"19:50","night":{"weather":"小雨","templow":"20","img":"7","winddirect":"微风","windpower":"3级"},"day":{"weather":"多云","temphigh":"26","img":"1","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-18","week":"星期四","sunrise":"06:03","sunset":"19:50","night":{"weather":"中雨","templow":"19","img":"8","winddirect":"微风","windpower":"3级"},"day":{"weather":"小雨","temphigh":"24","img":"7","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-19","week":"星期五","sunrise":"06:03","sunset":"19:51","night":{"weather":"小雨","templow":"19","img":"7","winddirect":"微风","windpower":"3级"},"day":{"weather":"阴","temphigh":"24","img":"2","winddirect":"微风","windpower":"3级"}},{"date":"2020-06-20","week":"星期六","sunrise":"06:04","sunset":"19:51","night":{"weather":"阴","templow":"18","img":"2","winddirect":"微风","windpower":"3级"},"day":{"weather":"小雨","temphigh":"23","img":"7","winddirect":"微风","windpower":"3级"}}]
         * hourly : [{"time":"11:00","weather":"小雨","temp":"21","img":"7"},{"time":"12:00","weather":"小雨","temp":"23","img":"7"},{"time":"13:00","weather":"小雨","temp":"24","img":"7"},{"time":"14:00","weather":"小雨","temp":"24","img":"7"},{"time":"15:00","weather":"小雨","temp":"24","img":"7"},{"time":"16:00","weather":"小雨","temp":"25","img":"7"},{"time":"17:00","weather":"阴","temp":"25","img":"2"},{"time":"18:00","weather":"小雨","temp":"24","img":"7"},{"time":"19:00","weather":"小雨","temp":"23","img":"7"},{"time":"20:00","weather":"小雨","temp":"23","img":"7"},{"time":"21:00","weather":"多云","temp":"22","img":"1"},{"time":"22:00","weather":"多云","temp":"22","img":"1"},{"time":"23:00","weather":"阴","temp":"22","img":"2"},{"time":"00:00","weather":"阴","temp":"22","img":"2"},{"time":"01:00","weather":"阴","temp":"21","img":"2"},{"time":"02:00","weather":"阴","temp":"20","img":"2"},{"time":"03:00","weather":"阴","temp":"20","img":"2"},{"time":"04:00","weather":"阴","temp":"20","img":"2"},{"time":"05:00","weather":"多云","temp":"20","img":"1"},{"time":"06:00","weather":"阴","temp":"20","img":"2"},{"time":"07:00","weather":"阴","temp":"20","img":"2"},{"time":"08:00","weather":"阴","temp":"20","img":"2"},{"time":"09:00","weather":"阴","temp":"20","img":"2"},{"time":"10:00","weather":"阴","temp":"21","img":"2"}]
         */

        private AqiBean aqi;
        private List<IndexBean> index;
        private List<DailyBean> daily;
        private List<HourlyBean> hourly;


        @Data
        public static class AqiBean {
            /**
             * so2 : 4
             * so224 : 0
             * no2 : 9
             * no224 : 0
             * co : 0.330
             * co24 : 0.000
             * o3 : 61
             * o38 : 0
             * o324 : 0
             * pm10 : 3
             * pm1024 : 0
             * pm2_5 : 2
             * pm2_524 : 0
             * iso2 : 2
             * ino2 : 5
             * ico : 4
             * io3 : 20
             * io38 : 0
             * ipm10 : 9
             * ipm2_5 : 3
             * aqi : 9
             * primarypollutant : o3
             * quality : 优
             * timepoint : 2020-06-14 09:00:00
             * aqiinfo : {"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}
             */

            private String so2;
            private String so224;
            private String no2;
            private String no224;
            private String co;
            private String co24;
            private String o3;
            private String o38;
            private String o324;
            private String pm10;
            private String pm1024;
            private String pm2_5;
            private String pm2_524;
            private String iso2;
            private String ino2;
            private String ico;
            private String io3;
            private String io38;
            private String ipm10;
            private String ipm2_5;
            private String aqi;
            private String primarypollutant;
            private String quality;
            private String timepoint;
            private AqiinfoBean aqiinfo;


            @Data
            public static class AqiinfoBean {
                /**
                 * level : 一级
                 * color : #00e400
                 * affect : 空气质量令人满意，基本无空气污染
                 * measure : 各类人群可正常活动
                 */

                private String level;
                private String color;
                private String affect;
                private String measure;


            }
        }

        @Data
        public static class IndexBean {
            /**
             * iname : 空调指数
             * ivalue : 较少开启
             * detail : 您将感到很舒适，一般不需要开启空调。
             */

            private String iname;
            private String ivalue;
            private String detail;


        }

        @Data
        public static class DailyBean {
            /**
             * date : 2020-06-14
             * week : 星期日
             * sunrise : 06:03
             * sunset : 19:49
             * night : {"weather":"阴","templow":"19","img":"2","winddirect":"微风","windpower":"3级"}
             * day : {"weather":"小雨","temphigh":"25","img":"7","winddirect":"微风","windpower":"3级"}
             */

            @SerializedName("date")
            private String dateX;
            @SerializedName("week")
            private String weekX;
            private String sunrise;
            private String sunset;
            private Weather night;
            private Weather day;


        }

        @Data
        public static class HourlyBean {
            /**
             * time : 11:00
             * weather : 小雨
             * temp : 21
             * img : 7
             */

            private String time;
            @SerializedName("weather")
            private String weatherX;
            @SerializedName("temp")
            private String tempX;
            @SerializedName("img")
            private String imgX;


        }
    }


    public static Weather getWeather(String city, String location) {
        String url = "https://jisutqybmf.market.alicloudapi.com/weather/query?city=" + city;
        if (StringUtils.isNotBlank(location)) {
            url = "https://jisutqybmf.market.alicloudapi.com/weather/query?location=" + location;
        }

        Weather weather = CACHE.getIfPresent(url);
        if (weather != null) {
            return weather;
        }

        String appCode = "9891bddb967348139c0b53a2d71248fa";


        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "APPCODE " + appCode)
                .get()
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();

        okhttp3.Call call = okHttpClient.newCall(request);
        String result = null;
        try {
            okhttp3.Response response = call.execute();
            result = response.body().string();
            log.debug(result);
            String result1 = new JSONObject(result)
                    .getJSONObject("result").toString();
            Weather weather1 = JsonUtil.fromJson(result1, Weather.class);
            if (weather1 != null) {
                CACHE.put(url, weather1);
            }
            return weather1;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
