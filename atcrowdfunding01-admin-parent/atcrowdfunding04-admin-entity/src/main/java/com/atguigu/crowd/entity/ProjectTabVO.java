package com.atguigu.crowd.entity;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-02 下午 04:28
 */
public class ProjectTabVO {
    private Integer id;
    private String projectName;
    private String launchMember;
    private Long money;
    private Integer day;
    private String createdate;
    private Integer status;

    public ProjectTabVO() {
    }

    public ProjectTabVO(Integer id, String projectName, String launchMember, Long money, Integer day, Integer status) {
        this.id = id;
        this.projectName = projectName;
        this.launchMember = launchMember;
        this.money = money;
        this.day = day;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLaunchMember() {
        return launchMember;
    }

    public void setLaunchMember(String launchMember) {
        this.launchMember = launchMember;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
