package com.lzscoding.demo.spring.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.*;

/**
 * 发送请求
 * https://blog.csdn.net/qq947297456/article/details/104782609
 */
@Slf4j
@RestController
public class RequestController {

    public final static String TOKEN = "Bearer " + "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiY29uc3VtZXItdGVzdCIsInByb3RhbC10ZXN0IiwiY3VzdG9tLXRlc3QiLCJtZW1iZXItdGVzdDIiLCJhdWRpdC10ZXN0IiwiZ3JlZXVwbXNhdXRoIiwiYmFubmVyLXRlc3QiLCJ1cG1zLW1hbmFnZXItdGVzdCIsImZpbGUtdGVzdCIsInBhcGVyLXRlc3QiLCJsb3R0ZXJ5LXRlc3QiLCJtZW1iZXItdGVzdCIsInd4LWFwaS10ZXN0IiwibXNnLXRlc3QiLCJhY2NvdW50LXRlc3QiXSwidXNlcl9pZCI6bnVsbCwicGhvbmUiOiIxNTE3NzMyNTAzNyIsInVzZXJfbmFtZSI6bnVsbCwic2NvcGUiOlsiZGVmYXVsdCJdLCJleHAiOjE2MjU2MjIxMjYsImF1dGhvcml0aWVzIjpbIlJPTEVfQ09OU1VNRVIiXSwianRpIjoiMTVjMzkxZjgtZTRhNi00OWQ4LWJlMWQtYTEzMjVhMGMyOTczIiwiY2xpZW50X2lkIjoiOTA1MDA5N2Y1NGYwYTZmYjdlNmM2NTQyYzdiYjIwNmIifQ.ObNlR9ZS0Wu1fMK91xPR6jgaEn6--UPQkZqr5DxCSImQEeYeA3815a5y3GVvlCPWJg_xL4PvYbuSsARSVA6lMd94kqLcGWGlpCwh4bFdN-1o8iBxz35lXor8VLnahvI_t3eHf7h_1bra_i1Qd2kA9yLifdCBcQHHc_PdrFB14ElM9sq0enlYwWJC1hz_yKQqwurWKDwWjaSNWxXfT1ksYzOmKpiRcqNw-ppcsfzWjQ9W6uQ-WymTSwiK2IF2PaWhvSY0tZgRq4DcqeN5n-ybG05Ps3_8C0oc0V3VjFI_OaMHPstIvaJ3BI0prDJ-5sU3T-lRek0IK9O6Oh0xDqY3cw";
    public final static String URL = "http://localhost:8080/response/test1";


//    @Resource
//    RestTemplate restTemplate;

    @RequestMapping("/request/test1")
    public String test1() throws ExecutionException, InterruptedException {

        log.info("业务开始");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return notifyOtherSystem();
            }
        });

        log.info("业务结束");

        return "ok";
    }

    private String notifyOtherSystem() throws URISyntaxException {


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", TOKEN);

        MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<>();
        headersMap.add("Authorization", TOKEN);

        MultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<>();
        parameterMap.add("newPhone", "111");
        parameterMap.add("phone", "000");


        RequestEntity<MultiValueMap<String, String>> requestEntity = new RequestEntity<>(parameterMap, headersMap, HttpMethod.POST, new URI(URL));
        ResponseEntity<String> result = restTemplate.exchange(requestEntity, String.class);
        log.info(result.getBody());
        return result.getBody();

    }

}
