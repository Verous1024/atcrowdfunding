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
 * @version 1.0 2021-02-26 下午 03:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VipProtalProjectVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer projectId;
    private String projectName; //产品名字
    private String projectDesc; //产品描述
    private String vipPicPath; //产品图片
    private Integer vipLevel; //vip等级
}
