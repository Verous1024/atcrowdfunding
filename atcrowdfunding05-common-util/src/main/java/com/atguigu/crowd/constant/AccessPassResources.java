package com.atguigu.crowd.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * Descriptions:
 * 存放好需要放行的资源，包括动态的网页、静态的资源
 * @author Verous
 * @version 1.0 2021-02-22 下午 07:59
 */
public class AccessPassResources {

    public static final Set<String> PASS_RES_SET = new HashSet<>();

    static{
        PASS_RES_SET.add("/");
        PASS_RES_SET.add("/auth/member/to/reg/page.html");
        PASS_RES_SET.add("/auth/member/to/login/page.html");
        PASS_RES_SET.add("/auth/member/logout");
        PASS_RES_SET.add("/auth/member/do/login");
        PASS_RES_SET.add("/auth/member/send/short/message.json");
        PASS_RES_SET.add("/auth/do/member/register");
        PASS_RES_SET.add("/service/about/me");
    }

    public static final Set<String> STATIC_RES_SET = new HashSet<>();

    static {
        STATIC_RES_SET.add("bootstrap");
        STATIC_RES_SET.add("css");
        STATIC_RES_SET.add("fonts");
        STATIC_RES_SET.add("img");
        STATIC_RES_SET.add("jquery");
        STATIC_RES_SET.add("layer");
        STATIC_RES_SET.add("script");
        STATIC_RES_SET.add("ztree");
    }

    /**
     * 测试servletPath中是否包含静态资源的目录，如果是返回true
     * @param servletPath
     * @return
     * true: 静态资源
     * false: 不是静态资源
     */
    public static boolean judegeCurrentServletPathWetherStaticResource(String servletPath) {

        if (servletPath == null || servletPath.length() == 0) {
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        //根据/ 进行拆分servletPath字符串
        String[] split = servletPath.split("/");

        String firstLevelPath = split[1];

        return STATIC_RES_SET.contains(firstLevelPath);
    }

}
