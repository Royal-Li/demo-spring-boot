package com.lzscofing.demoredis.service.impl;

import com.lzscoding.demomybatisplus.dao.OrderDao;
import com.lzscoding.demomybatisplus.dao.ProductDao;
import com.lzscoding.demomybatisplus.pojo.Order;
import com.lzscoding.demomybatisplus.pojo.Product;
import com.lzscofing.demoredis.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * 订单生成实现类
 *
 * @author 180626
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductDao productDao;
    @Autowired
    OrderDao orderDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(Integer productId, Integer number) throws InterruptedException {
        //1 获取库存
        Product product = productDao.getProduct(productId);
        log.info("剩余库存数:{}", product.getStock());
        //模拟耗时业务处理
        sleep(1000);
        if (product.getStock() <= 0) {
            throw new RuntimeException("out of stock");
        }
        //2 减库存
        int i = productDao.deductStock(productId);
        if (i == 1) {
            Order order = new Order();
            order.setUserId(UUID.randomUUID().toString());
            order.setProductId(productId);
            orderDao.insertSelective(order);
        } else {
            throw new RuntimeException("deduct stock fail, please retry");
        }

    }
}
