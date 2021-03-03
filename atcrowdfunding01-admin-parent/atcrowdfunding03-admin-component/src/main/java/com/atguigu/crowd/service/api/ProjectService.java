package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.ProjectDetailVO;
import com.atguigu.crowd.entity.ProjectTabVO;
import com.github.pagehelper.PageInfo;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-02 下午 04:08
 */
public interface ProjectService {
    PageInfo<ProjectTabVO> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    ProjectDetailVO getProjectDetailVO(Integer projectId);

    void doExaminationPass(Integer projectId,Integer type);
}
