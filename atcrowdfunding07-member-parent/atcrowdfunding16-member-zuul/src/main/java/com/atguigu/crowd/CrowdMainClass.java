package com.atguigu.crowd;


import com.atguigu.crowd.filter.CrowdAccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-20 下午 09:35
 */
//开启Zuul功能
@EnableZuulProxy
@SpringBootApplication
public class CrowdMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass.class, args);
    }

    @Bean
    public CrowdAccessFilter crowdAccessFilter() {
        return new CrowdAccessFilter();
    }
}
