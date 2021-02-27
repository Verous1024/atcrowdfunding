package com.atguigu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-27 下午 04:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyCrowdProjectVO {
    private Integer id;

    private String projectName;

    private String projectDescription;

    private Integer money;

    private Integer day;

    private Integer status;

    private String deploydate;

    private Long supportmoney;

    private Integer supporter;

    private Integer completion;

    private Integer memberid;

    private String createdate;

    private Integer follower;

    private String headerPicturePath;

    private Integer percentage; //百分比

    private Long lastDays; //剩余天数
}
