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
 * @version 1.0 2021-03-01 下午 02:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupporterAddressReturnVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String loginacct; //用户账号
    private String receiveName; //接受名字
    private String phoneNum; //电话
    private String address; //地址
    private String orderRemark; //备注
    private String returnContent; //回内容
    private Integer returnCount; //回报数量
    private Integer invoice; //发票
    private String invoiceTitle; //发票头
    private Integer status; //状态
}
