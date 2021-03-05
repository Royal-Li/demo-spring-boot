package com.lzscoding.demozookeeper.controller;

import com.lzscoding.demozookeeper.service.OrderService;
import com.lzscoding.demozookeeper.config.props.ZkProps;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 180626
 */
@RestController
@RequestMapping("/test")
@EnableConfigurationProperties({ZkProps.class})
public class TestController {

    @Value("${server.port}")
    String port;
    @Resource
    ZkProps zkProps;
    @Autowired
    OrderService orderService;
    @Autowired
    private CuratorFramework curatorFramework;


    @RequestMapping("/show")
    public ResponseEntity showInfo() {
        List<Object> list = new ArrayList();
        list.add(port);
        list.add(zkProps);
        return new ResponseEntity(list, HttpStatus.OK);
    }


    @RequestMapping("/stock/deduct")
    public ResponseEntity reduceStock(Integer id) throws Exception {
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/product_" + id);
        try {
            lock.acquire();
            orderService.reduceStock(id);

        } catch (Exception e) {

            if (e instanceof RuntimeException) {
                throw e;
            }

        }finally {
            lock.release();
        }

        return new ResponseEntity("ok:" + port, HttpStatus.OK);

    }

}
