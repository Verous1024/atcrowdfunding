package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Return;
import com.atguigu.crowd.entity.ReturnExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnMapper {
    int countByExample(ReturnExample example);

    int deleteByExample(ReturnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Return record);

    int insertSelective(Return record);

    List<Return> selectByExample(ReturnExample example);

    Return selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Return record, @Param("example") ReturnExample example);

    int updateByExample(@Param("record") Return record, @Param("example") ReturnExample example);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);
}