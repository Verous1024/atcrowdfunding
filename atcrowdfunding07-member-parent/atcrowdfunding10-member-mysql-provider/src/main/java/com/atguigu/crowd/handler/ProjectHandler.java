package com.atguigu.crowd.handler;

import com.atguigu.crowd.entity.po.ProjectPO;
import com.atguigu.crowd.entity.vo.*;
import com.atguigu.crowd.service.api.ProjectService;
import com.atguigu.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-23 下午 01:20
 */
@RestController
public class ProjectHandler {
    @Autowired
    private ProjectService projectService;

    private Logger logger = LoggerFactory.getLogger(ProjectHandler.class);


    @RequestMapping("/get/my/support")
    ResultEntity<List<MySupportVO>> getMySupport(@RequestParam("memberId") Integer memberId){
        try {
            List<MySupportVO> mySupport = projectService.getMySupport(memberId);
            return ResultEntity.successWithData(mySupport);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/my/focus")
    ResultEntity<List<ProjectPO>> getMyFocus(@RequestParam("memberId") Integer memberId){
        try {
            List<ProjectPO> myFocus = projectService.getMyFocus(memberId);
            return ResultEntity.successWithData(myFocus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }


    @RequestMapping("/get/my/project")
    ResultEntity<List<ProjectPO>> getMyProject(@RequestParam("memberId") Integer memberId){
        try {
            List<ProjectPO> myProject = projectService.getMyProject(memberId);
            return ResultEntity.successWithData(myProject);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }



    //获取所有的值:
    @RequestMapping("/get/all/project/with/type")
    ResultEntity<List<ProjectPO>> getAllProjectWithType(
            @RequestParam(value="typeId",required = false) Integer typeId,
            @RequestParam(value="status",required = false) Integer status,
            @RequestParam(value="orderType",required = false) Integer orderType){
        logger.info("typeId="+typeId);
        logger.info("status="+status);
        logger.info("orderType="+orderType);
        try {
            List<ProjectPO> allProject = projectService.getAllProject(typeId, status, orderType);
            return ResultEntity.successWithData(allProject);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }



    //关键点：除请求参数外，还可以使用projectId路径参数来进行设置
    @RequestMapping("/get/project/detail/remote/{projectId}")
    public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId) {
        try {
            DetailProjectVO detailProjectVO = projectService.getDetailProjectVO(projectId);
            return ResultEntity.successWithData(detailProjectVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }


    //注意：实体类需要添加@RequestBody，单个的需要添加@RequestParam
    @RequestMapping("/save/project/vo/remote")
    public ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId) {
        try {
            projectService.saveProject(projectVO, memberId);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * @return 返会json数据，因为是不同的微服务之间进行传输数据，网络之间传输数据的格式要i求
     */
    @RequestMapping("/get/portal/type/project/data/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote(){
        try {
            List<PortalTypeVO> portalTypeVO = projectService.getPortalTypeVO();
            return ResultEntity.successWithData(portalTypeVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/vip/project/data/remote")
    public ResultEntity<List<VipProtalProjectVO>> getVipProjectDataRemote(){
        try {
            List<VipProtalProjectVO> vipProtalProjectVOS =  projectService.getVipProject(1);
            List<VipProtalProjectVO> vipProtalProjectVOS2 =  projectService.getVipProject(2);
            for (VipProtalProjectVO vip : vipProtalProjectVOS2) {
                vipProtalProjectVOS.add(vip);
            }
            return ResultEntity.successWithData(vipProtalProjectVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

}
