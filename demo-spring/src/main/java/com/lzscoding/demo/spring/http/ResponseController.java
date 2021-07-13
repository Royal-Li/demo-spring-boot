package com.lzscoding.demo.spring.http;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 响应controller
 */
@Slf4j
@RestController
public class ResponseController {


    @PostMapping("/response/test1")
    public String test1(HttpServletRequest httpServletRequest) throws InterruptedException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if (authorization != null && authorization.equals(RequestController.TOKEN)) {
            Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
            System.out.println("请求header是 : " + authorization);
            System.out.println("请求参数是 : " + JSONObject.toJSONString(parameterMap));
        }

        Thread.sleep(5000);

        return authorization;
    }

}
