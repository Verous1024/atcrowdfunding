package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.MemberLaunchInfo;
import com.atguigu.crowd.entity.MemberLaunchInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberLaunchInfoMapper {
    int countByExample(MemberLaunchInfoExample example);

    int deleteByExample(MemberLaunchInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberLaunchInfo record);

    int insertSelective(MemberLaunchInfo record);

    List<MemberLaunchInfo> selectByExample(MemberLaunchInfoExample example);

    MemberLaunchInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberLaunchInfo record, @Param("example") MemberLaunchInfoExample example);

    int updateByExample(@Param("record") MemberLaunchInfo record, @Param("example") MemberLaunchInfoExample example);

    int updateByPrimaryKeySelective(MemberLaunchInfo record);

    int updateByPrimaryKey(MemberLaunchInfo record);
}