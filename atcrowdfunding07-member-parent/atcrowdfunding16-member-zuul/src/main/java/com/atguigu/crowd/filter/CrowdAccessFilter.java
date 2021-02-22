package com.atguigu.crowd.filter;

import com.atguigu.crowd.constant.AccessPassResources;
import com.atguigu.crowd.constant.CrowdConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.HTTP;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-22 下午 08:17
 */

public class CrowdAccessFilter extends ZuulFilter {

    /**
     * @return 返回true直接放行，返回false还需要进一步检查过滤
     */
    @Override
    public boolean shouldFilter() {
        //获取当前的请求对对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //获取当前的servletPath
        String servletPath = request.getServletPath();
        System.out.println("++++++++++++++++++"+servletPath);
        if (AccessPassResources.PASS_RES_SET.contains(servletPath)) {
            return false; //结果为true，表示为需要放行的网页，因此返回false，不执行run方法
        }
        //再判断是否是需要放行的静态资源，是则返回false
        return !AccessPassResources.judegeCurrentServletPathWetherStaticResource(servletPath);

    }

    @Override
    public Object run() throws ZuulException {

        //获取当前的请求对对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //获取当前的session
        HttpSession session = request.getSession();

        //获取session中的用户
        Object member = session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);

        if (member == null) {

            HttpServletResponse response = currentContext.getResponse();
            //存入需要的信息
            session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE_WITH_LOGIN,CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
            //注意：这里需要请求重定向，如果是从zuul工程到auth工程的首页，会不停打印，应该让浏览器发送请求
            try {
                response.sendRedirect("/auth/member/do/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @return
     * 1、pre：可以在请求被路由之前调用；
     * 2、route：在路由请求时候被调用；
     * 3、post：在route和error过滤器之后被调用；
     * 4、error：处理请求时发生错误时被调用；
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE; //目标微服务之前执行
    }

    //过滤的优先级，数字越大，优先级越低。
    @Override
    public int filterOrder() {
        return 0;
    }
}
