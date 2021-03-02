package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Member;
import com.atguigu.crowd.entity.ProjectDetailVO;
import com.atguigu.crowd.entity.ProjectTabVO;
import com.atguigu.crowd.mapper.MemberMapper;
import com.atguigu.crowd.mapper.ProjectMapper;
import com.atguigu.crowd.service.api.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-02 下午 04:08
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public PageInfo<ProjectTabVO> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectTabVO> projects = projectMapper.selectProjectByKeyword(keyword);
        return new PageInfo<>(projects);
    }

    @Override
    public ProjectDetailVO getProjectDetailVO(Integer projectId) {
        ProjectDetailVO projectDetailVOS = projectMapper.selectGetDetailProject(projectId);
        Member member = memberMapper.selectByPrimaryKey(projectDetailVOS.getMemberId());
        projectDetailVOS.setMember(member);
        return projectDetailVOS;
    }
}
