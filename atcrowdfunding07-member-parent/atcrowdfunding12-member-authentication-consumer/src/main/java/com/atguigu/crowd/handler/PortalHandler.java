package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.vo.PortalTypeVO;
import com.atguigu.crowd.util.ResultEntity;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 03:21
 */
@Controller
public class PortalHandler {
    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping("/")
    public String showPortalPage(Model model){
        //从数据库完成实际页面数据的加载

        ResultEntity<List<PortalTypeVO>> potalTypeProjectDataRemote = mySQLRemoteService.getPortalTypeProjectDataRemote();

        //2、查询结果是否正确
        String result = potalTypeProjectDataRemote.getResult();
        if (ResultEntity.SUCCESS.equals(result)) {
            List<PortalTypeVO> data = potalTypeProjectDataRemote.getData();
            model.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_DATA, data);
        }
        return "portal";
    }
}
