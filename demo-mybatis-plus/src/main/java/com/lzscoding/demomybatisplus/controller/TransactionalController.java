package com.lzscoding.demomybatisplus.controller;

import com.lzscoding.demomybatisplus.dao.UserDao;
import com.lzscoding.demomybatisplus.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * transactional注解 会有几个失效场景
 * 1 异常被catch
 * 2 异常类型错误   默认回滚的是runtimeException error  如触发其他异常的回滚 需要增加配置
 *                  @Transactional(rollbackFor = Exception.class)
 * 3 方法不是public
 * 4 注解设置 不支持事务
 * 5 实例没有被spring 管理
 * 6 数据源没有配置事务管理器
 * 7 数据库引擎不支持事务
 *
 * @author 180626
 */
@RestController
@RequestMapping
@Slf4j
public class TransactionalController {

    @Autowired
    UserDao userDao;


    @RequestMapping("/test1")
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<?> transationalTest1() {
        User user = null;
        try {
            log.warn("测试mybatis事务");
            user = User.builder().name("事务插入测试人员" + this.getTimeNumber()).age(1).email("qq.com").build();
            userDao.insertSelective(user);
            int e = 1 / 0;
            log.info("异常被捕捉，回滚不生效");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping("/test2")
    @Transactional
    public ResponseEntity<?> transationalTest2() {
        log.warn("测试mybatis事务");
        User user = User.builder().name("事务插入测试人员" + this.getTimeNumber()).age(1).email("qq.com").build();
        userDao.insertSelective(user);
        int e = 1 / 0;
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private String getTimeNumber() {
        return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    }
}
