package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-25 上午 09:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProjectVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String projectName;
    private String launchName; //发起人名字/公司
    private String returnContent;
    private Integer returnCount; //购买的数量
    private Integer supportPrice;
    private Integer freight;
    private Integer orderId;
    private Integer signalPurchase;
    private Integer purchase;
}

