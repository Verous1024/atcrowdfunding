package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.ProjectDetailVO;
import com.atguigu.crowd.entity.ProjectTabVO;
import com.atguigu.crowd.service.api.ProjectService;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-02 下午 04:14
 */
@Controller
public class ProjectHandler {
    @Autowired
    private ProjectService projectService;

    @PreAuthorize("hasAuthority('project:pass')")
    @ResponseBody
    @RequestMapping("/project/do/examination/pass.json")
    public ResultEntity<String> doExaminationPass(
            @RequestParam("projectId") Integer projectId,
            @RequestParam("status") Integer status) {
        try {
            projectService.doExaminationPass(projectId, status);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('project:get')")
    @ResponseBody
    @RequestMapping("/project/get/detail/project.json")
    public ResultEntity<ProjectDetailVO> getProjectDetailVO(
            @RequestParam(value = "projectId") Integer projectId) {
        ProjectDetailVO myProjectDetailVO = projectService.getProjectDetailVO(projectId);
        return ResultEntity.successWithData(myProjectDetailVO);
    }

    @PreAuthorize("hasAuthority('project:get')")
    @ResponseBody
    @RequestMapping("/project/get/page/info.json")
    public ResultEntity<PageInfo<ProjectTabVO>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        PageInfo<ProjectTabVO> pageInfo = projectService.getPageInfo(pageNum, pageSize, keyword);
        return ResultEntity.successWithData(pageInfo);
    }

}
