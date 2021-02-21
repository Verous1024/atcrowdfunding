package com.atguigu.crowd.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 03:21
 */
@Controller
public class PortalHandler {

    @RequestMapping("/")
    public String showPortalPage(){
        //从数据库完成实际页面数据的加载
        return "portal";
    }
}
