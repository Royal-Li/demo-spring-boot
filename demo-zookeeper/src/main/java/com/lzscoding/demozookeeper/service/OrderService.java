package com.lzscoding.demozookeeper.service;


import com.lzscoding.demozookeeper.entity.Order;
import com.lzscoding.demozookeeper.entity.Product;
import com.lzscoding.demozookeeper.mapper.OrderDao;
import com.lzscoding.demozookeeper.mapper.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

import static java.lang.Thread.sleep;


/**
 * @author 180626
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    ProductDao productDao;

    @Transactional
    public void reduceStock(Integer id) throws InterruptedException {
        //1 获取库存
        Product product = productDao.getProduct(id);
        log.info("剩余库存数:{}",product.getStock());
        //模拟耗时业务处理
        sleep(1000);
        if (product.getStock() <= 0) {
            throw new RuntimeException("out of stock");
        }
        //2 减库存
        int i = productDao.deductStock(id);
        if (i == 1) {
            Order order = new Order();
            order.setUserId(UUID.randomUUID().toString());
            order.setProductId(id);
            orderDao.insert(order);
        } else {
            throw new RuntimeException("deduct stock fail, please retry");
        }
    }
}
