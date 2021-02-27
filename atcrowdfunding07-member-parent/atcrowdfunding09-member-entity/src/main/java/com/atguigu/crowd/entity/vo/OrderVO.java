package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-25 下午 03:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id; //数据库id

    private String orderNum; //商户订单

    private String payOrderNum; //支付宝流水单号--

    private Double orderAmount; //订单金额--

    private Integer invoice; //是否开发票，1开-

    private String invoiceTitle; // 发票抬头-

    private String orderRemark; //备注-

    private String addressId; //收件人的地址的id-

    private Integer projectId;

    private Integer returnId;

    private OrderProjectVO orderProjectVO; //订单的产品信息--
}
