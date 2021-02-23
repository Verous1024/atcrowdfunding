package com.atguigu.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 09:26
 */
//注解类
@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {

    /**
     * 等同于之前的基于注解的配置view-controller
     * @param registry 注册中心
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器访问的地址
        String urlPath  ="/auth/member/to/reg/page.html";
        //目标视图的名称 会被 thymleaf解析
        String viewName  = "member-reg";
        registry.addViewController(urlPath).setViewName(viewName);

        // 浏览器访问的地址
        String urlPath1  ="/auth/member/to/login/page.html";
        //目标视图的名称 会被 thymleaf解析
        String viewName1  = "member-login";
        registry.addViewController(urlPath1).setViewName(viewName1);

        registry.addViewController("/auth/member/to/center/page").setViewName("member-center");
        registry.addViewController("/member/my/crowd").setViewName("member-crowd");

    }
}
