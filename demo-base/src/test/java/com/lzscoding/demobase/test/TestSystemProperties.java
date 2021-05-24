package com.lzscoding.demobase.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Properties;

public class TestSystemProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        System.out.println(JSONObject.toJSONString(properties, SerializerFeature.PrettyFormat));
    }
}
