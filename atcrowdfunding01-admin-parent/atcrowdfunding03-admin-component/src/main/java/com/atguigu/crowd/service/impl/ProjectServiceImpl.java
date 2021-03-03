package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Member;
import com.atguigu.crowd.entity.Project;
import com.atguigu.crowd.entity.ProjectDetailVO;
import com.atguigu.crowd.entity.ProjectTabVO;
import com.atguigu.crowd.mapper.MemberMapper;
import com.atguigu.crowd.mapper.ProjectMapper;
import com.atguigu.crowd.service.api.ProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Integer status = 0;
        Boolean flag = false;
        if (keyword.contains("待审核")) {
            status=0;
            flag=true;
        } else if (keyword.contains("众筹中")) {
            status=1;
            flag=true;
        }else if (keyword.contains("众筹失败")) {
            status=2;
            flag=true;
        }else if (keyword.contains("众筹成功")) {
            status=3;
            flag=true;
        }else if (keyword.contains("审核失败")) {
            status=4;
            flag=true;
        }else if (keyword.contains("项目异常被暂停")) {
            status=5;
            flag=true;
        }

        if (flag) {
            PageHelper.startPage(pageNum, pageSize);
            List<ProjectTabVO> projects = projectMapper.selectProjectByStatus(status);
            return new PageInfo<>(projects);
        }else{
            PageHelper.startPage(pageNum, pageSize);
            List<ProjectTabVO> projects = projectMapper.selectProjectByKeyword(keyword);
            return new PageInfo<>(projects);
        }

    }

    @Override
    public ProjectDetailVO getProjectDetailVO(Integer projectId) {
        ProjectDetailVO projectDetailVOS = projectMapper.selectGetDetailProject(projectId);
        Member member = memberMapper.selectByPrimaryKey(projectDetailVOS.getMemberId());
        projectDetailVOS.setMember(member);
        return projectDetailVOS;
    }

    @Override
    public void doExaminationPass(Integer projectId,Integer type) {
        Project project = new Project();
        project.setId(projectId);
        project.setStatus(type);
        if (type == 1) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(date);
            project.setDeploydate(format);
        }
        projectMapper.updateByPrimaryKeySelective(project);
    }
}
