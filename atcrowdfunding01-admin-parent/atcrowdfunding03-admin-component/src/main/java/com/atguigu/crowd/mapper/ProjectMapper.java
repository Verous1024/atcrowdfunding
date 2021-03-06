package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Project;
import com.atguigu.crowd.entity.ProjectDetailVO;
import com.atguigu.crowd.entity.ProjectExample;
import com.atguigu.crowd.entity.ProjectTabVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    int countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<ProjectTabVO> selectProjectByKeyword(@Param("keyword") String keyword);

    List<ProjectTabVO> selectProjectByStatus(@Param("keyword") Integer keyword);

    ProjectDetailVO selectGetDetailProject(Integer id);
}