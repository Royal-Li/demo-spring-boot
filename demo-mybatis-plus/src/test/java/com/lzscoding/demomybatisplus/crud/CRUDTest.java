package com.lzscoding.demomybatisplus.crud;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzscoding.demomybatisplus.DemoMybatisPlusApplicationTests;
import com.lzscoding.demomybatisplus.dao.UserDao;
import com.lzscoding.demomybatisplus.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 如果不继承启动类 则@autowired注解失效 nullpoint
 */
@Slf4j
public class CRUDTest extends DemoMybatisPlusApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    public void helloTest() {
        System.out.println("helloTest");
    }

    @Test
    public void mybatisplusTest1() {
        log.warn("测试mybatis-plus查询");
        List<User> users = userDao.selectList(null);
        log.warn("用戶列表:{}", JSONObject.toJSONString(users, SerializerFeature.PrettyFormat));
    }

    @Test
    public void mybatisplusTest2() {
        log.warn("测试mybatis-plus条件构造查询");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", "20");
        List<User> users = userDao.selectList(queryWrapper);
        log.warn("用戶列表:{}", JSONObject.toJSONString(users, SerializerFeature.PrettyFormat));
    }

    @Test
    public void mybatisSqlTest() {
        log.warn("测试mybatis注解自定义查询");
        List<User> users = userDao.selectAge20();
        log.warn("用戶列表:{}", JSONObject.toJSONString(users, SerializerFeature.PrettyFormat));
    }

    @Test
    public void mybatisXmlTest() {
        log.warn("测试mybatisXML自定义查询");
        List<User> users = userDao.selectByCondition(User.builder().name("Jone").age(24).build());
        log.warn("用戶列表:{}", JSONObject.toJSONString(users, SerializerFeature.PrettyFormat));
    }

    @Test
    @Transactional()
    @Rollback(value = false)
    public void mybatisTransactionalTest() {
        log.warn("测试mybatis事务");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); //设置时间格式
        String sdate = sdf.format(new Date());

        User user = userDao.selectById(1);
        user.setName(user.getName() + sdate);
        int result = userDao.updateById(user);
        if (result == 1) {
            user = userDao.selectById(1);
            log.warn("userid为1的人的信息:{}", JSONObject.toJSONString(user));
        }
        int e = 1 / 0;

    }


    //TODO: 不受spring管理?怎么处理


}
