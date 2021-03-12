package com.lzscoding.dubbo.common.service;

/**
 * 测试远程调用错误的接口
 * @author 180626
 */
public interface ErrorService {

    /**
     * 超时接口
     * @param str
     * @return
     */
    String outTime(String str);

    /**
     * 错误接口
     * @param str
     * @return
     */
    String err(String str);
}
