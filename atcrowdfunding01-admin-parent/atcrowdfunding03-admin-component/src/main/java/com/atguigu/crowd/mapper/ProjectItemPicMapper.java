package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.ProjectItemPic;
import com.atguigu.crowd.entity.ProjectItemPicExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectItemPicMapper {
    int countByExample(ProjectItemPicExample example);

    int deleteByExample(ProjectItemPicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectItemPic record);

    int insertSelective(ProjectItemPic record);

    List<ProjectItemPic> selectByExample(ProjectItemPicExample example);

    ProjectItemPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectItemPic record, @Param("example") ProjectItemPicExample example);

    int updateByExample(@Param("record") ProjectItemPic record, @Param("example") ProjectItemPicExample example);

    int updateByPrimaryKeySelective(ProjectItemPic record);

    int updateByPrimaryKey(ProjectItemPic record);
}