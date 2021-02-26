package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-25 下午 12:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id; //id
    private String receiveName; //接收人名字
    private String phoneNum; //手机号码
    private String address; //地址
    private Integer memberId; //会员id
}

