package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-20 下午 09:35
 */
//启动Feign客户端功能
@EnableFeignClients
//老版本需要启动
@EnableDiscoveryClient
@SpringBootApplication
public class CrowdMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass.class, args);
    }

}
