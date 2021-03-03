package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.vo.PortalTypeVO;
import com.atguigu.crowd.entity.vo.VipProtalProjectVO;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
        ResultEntity<List<VipProtalProjectVO>> vipProjectDataRemote = mySQLRemoteService.getVipProjectDataRemote();
        List<VipProtalProjectVO> vip1 = new ArrayList<VipProtalProjectVO > ();
        List<VipProtalProjectVO> vip2 = new ArrayList<VipProtalProjectVO > ();
        for (VipProtalProjectVO vipProtalProjectVO : vipProjectDataRemote.getData()) {
            if (vipProtalProjectVO.getVipLevel() == 1) {
                vip1.add(vipProtalProjectVO);
            } else if (vipProtalProjectVO.getVipLevel() == 2) {
                vip2.add(vipProtalProjectVO);
            }
        }
        //2、查询结果是否正确
        String result = potalTypeProjectDataRemote.getResult();
        if (ResultEntity.SUCCESS.equals(result)) {
            List<PortalTypeVO> data = potalTypeProjectDataRemote.getData();
            model.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_DATA, data);
            model.addAttribute(CrowdConstant.ATTR_NAME_VIP_PROJECT_1, vip1); //vip1
            model.addAttribute(CrowdConstant.ATTR_NAME_VIP_PROJECT_2, vip2); //vip2
        }
        return "portal";
    }
}
