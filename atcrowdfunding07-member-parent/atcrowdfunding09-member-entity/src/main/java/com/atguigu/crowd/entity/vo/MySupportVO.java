package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-27 下午 02:28
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MySupportVO {
    private Integer projectId;
    private String projectName;
    private Integer money; //目标金额
    private Long supportmoney; //已筹集的金额 //用于计算百分比
    private String deploydate; //用于计算剩余的天数
    private Long lastDays; //剩余天数
    private String orderNum; //订单号
    private Date supportTime; //支持时间，从订单号中拿出来
    private Double orderAmount;// 支持金额
    private Integer returnCount; //回报数量
    private Integer status;//众筹是否成功
    private Integer freight;//运费
    private Integer day;//要求的天数
}
