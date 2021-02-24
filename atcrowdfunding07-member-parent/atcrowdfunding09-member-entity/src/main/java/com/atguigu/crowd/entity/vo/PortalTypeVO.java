package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Descriptions: 具体产品属于的类型：科技、公益等等
 *
 * @author Verous
 * @version 1.0 2021-02-24 下午 12:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PortalTypeVO {
    private Integer id;
    private String name;
    private String remark;
    private List<PortalProjectVO> portalProjectVOList;
}
