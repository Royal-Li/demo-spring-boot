package com.lzscoding.demobase;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * @author 180626
 */
@SpringBootApplication
public class DemoBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBaseApplication.class, args);
        Properties properties = System.getProperties();
        System.out.println(JSONObject.toJSONString(properties, SerializerFeature.PrettyFormat));
    }

}
