package com.lzscoding.dubbo.provider.service;

import com.lzscoding.dubbo.common.service.ErrorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.management.RuntimeErrorException;
import java.util.concurrent.TimeUnit;

/**
 * @author 180626
 */
@Slf4j
@Service(version = "1.0.0", interfaceClass = ErrorService.class)
public class ErrorServiceImpl implements ErrorService {


    @Override
    public String outTime(String str) {
        log.info("调用超时方法");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error("调用超时!!!!");
        return "处理过的:" + str;
    }

    @Override
    public String err(String str) {
        log.error("调用出错方法");
        try {
            str = "before " + str;
            String strNull = null;
            System.out.println(strNull.length());
            str = str + " after";
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用出错!!!!");
        }
        return "出错的结果 " + str;
    }
}
