package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.po.AdvertisementPO;
import com.atguigu.crowd.entity.po.AdvertisementPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertisementPOMapper {
    int countByExample(AdvertisementPOExample example);

    int deleteByExample(AdvertisementPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdvertisementPO record);

    int insertSelective(AdvertisementPO record);

    List<AdvertisementPO> selectByExample(AdvertisementPOExample example);

    AdvertisementPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdvertisementPO record, @Param("example") AdvertisementPOExample example);

    int updateByExample(@Param("record") AdvertisementPO record, @Param("example") AdvertisementPOExample example);

    int updateByPrimaryKeySelective(AdvertisementPO record);

    int updateByPrimaryKey(AdvertisementPO record);
}