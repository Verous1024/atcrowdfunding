package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Descriptions: 用户提交时--需要验证身份证号码和支付宝账号
 *
 * @author Verous
 * @version 1.0 2021-02-23 下午 01:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberConfirmInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    //易付宝账号
    private String paynum;
    //法人身份证号
    private String cardnum;
}

