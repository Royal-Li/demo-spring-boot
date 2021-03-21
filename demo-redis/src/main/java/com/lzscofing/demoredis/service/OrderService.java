package com.lzscofing.demoredis.service;

/**
 * 订单生成接口
 *
 * @author 180626
 */
public interface OrderService {

    /**
     * 生成订单减库存
     *
     * @param productId 商品id
     * @param number    数量
     */
    void createOrder(Integer productId, Integer number) throws InterruptedException;

}
