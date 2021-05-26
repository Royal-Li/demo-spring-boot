package com.lzscoding.dubbo.provider.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.RpcException;

/**
 * 自定义
 * 扩展dubbo Protocol 组件
 *
 * 基于约定的路径下制定配置文件。目的是通过配置的方式轻松实现功能的扩展。
 * @author 180626
 */
public class MyProtocol implements Protocol {
    @Override
    public int getDefaultPort() {
        return 9999;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        return null;
    }

    @Override
    public void destroy() {

    }


    public static void main(String[] args) {
        Protocol myProtocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myProtocol");
        System.out.println(myProtocol.getDefaultPort());
        Protocol redis = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("redis");
        System.out.println(redis.getDefaultPort());
    }
}
