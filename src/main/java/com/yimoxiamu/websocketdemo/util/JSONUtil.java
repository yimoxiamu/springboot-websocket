package com.yimoxiamu.websocketdemo.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName JSONUtil
 * @Description TODO
 * @Author xiamu
 * @Email 768840822@qq.com
 * @Date 2018/8/20 17:42
 * @VERSION 1.0
 **/
public class JSONUtil {
    public static JSONObject getJSONObject(String id,String data,String toId){
        JSONObject object=new JSONObject();
        object.put("id",id);
        object.put("data",data);
        object.put("toId",toId);
        return object;
    }
}
