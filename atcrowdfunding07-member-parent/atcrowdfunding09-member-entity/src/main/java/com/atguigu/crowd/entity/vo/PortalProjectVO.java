package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Descriptions: 前台需要展示的具体产品
 *
 * @author Verous
 * @version 1.0 2021-02-24 下午 12:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PortalProjectVO {
    private Integer projectId;
    private String projectName;
    private String headerPicturePath;
    private Integer money;
    private String deployData;
    private Integer percentage;
    private Integer supporter;
    private Integer follower;
}
