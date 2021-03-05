package com.lzscoding.demozookeeper.config.props;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ZkProps.class)
public class CuratorCfg {

    private final ZkProps zkProps;

    @Autowired
    public CuratorCfg(ZkProps zkProps) {
        this.zkProps = zkProps;
    }

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(zkProps.getTimeout(),zkProps.getRetry());
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkProps.getUrl(),retryPolicy);
        //client.start();
        return client;
    }
}
