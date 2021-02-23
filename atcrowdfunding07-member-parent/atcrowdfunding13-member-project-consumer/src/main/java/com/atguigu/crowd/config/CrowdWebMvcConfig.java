package com.atguigu.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-23 下午 02:01
 */
@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // view-controller是在  project-consumer内部定义的，所以这里是一个不经过Zuul访问的地址，所以这个路径前面不加路由规则中定义的前缀：“/project”
        //总之，这里做跳转是不需要，/project的
        registry.addViewController("/agree/protocol/page").setViewName("project-agree");
        registry.addViewController("/launch/project/page").setViewName("project-launch");
        registry.addViewController("/return/info/page").setViewName("project-return");
        registry.addViewController("/create/confirm/page.html").setViewName("project-confirm");
    }
}

