package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.po.ProjectPO;
import com.atguigu.crowd.entity.vo.DetailProjectVO;
import com.atguigu.crowd.entity.vo.PortalTypeVO;
import com.atguigu.crowd.entity.vo.ProjectVO;
import com.atguigu.crowd.entity.vo.VipProtalProjectVO;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-23 下午 01:19
 */
public interface ProjectService {
    void saveProject(ProjectVO projectVO, Integer memberId);

    List<PortalTypeVO> getPortalTypeVO();

    DetailProjectVO getDetailProjectVO(Integer projectId);

    List<VipProtalProjectVO> getVipProject(Integer vipLevel);

    List<ProjectPO> getAllProject(Integer typeId, Integer status, Integer orderType);
}
