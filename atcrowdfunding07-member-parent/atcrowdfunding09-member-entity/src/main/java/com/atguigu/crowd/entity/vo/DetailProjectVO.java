package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-24 下午 03:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailProjectVO {
    private Integer projectId;
    private String projectName;
    private String projectDesc;
    private Integer followerCount; //关注人数
    private Integer status; //当前状态
    private String statusText;
    private Integer money; //目标资金
    private Integer day; //凑集多少天
    private Integer supportMoney; //已获取多少钱
    private Integer percentage;
    private String deployDate; //发布理器
    private Integer lastDay; //剩余多少天
    private Integer supporterCount; //支持的认数
    private String headerPicturePath;
    private List<String> detailPicturePathList;
    private List<DetailReturnVO> detailReturnVOList; //回报信息
}
