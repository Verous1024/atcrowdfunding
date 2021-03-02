package com.atguigu.crowd.entity;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-02 下午 07:34
 */
public class MemberLaunchInfoVO {
    private String descriptionSimple;
    private String  descriptionDetail;
    private String phoneNum;
    private String serviceNum;

    public MemberLaunchInfoVO() {
    }

    public String getDescriptionSimple() {
        return descriptionSimple;
    }

    public void setDescriptionSimple(String descriptionSimple) {
        this.descriptionSimple = descriptionSimple;
    }

    public String getDescriptionDetail() {
        return descriptionDetail;
    }

    public void setDescriptionDetail(String descriptionDetail) {
        this.descriptionDetail = descriptionDetail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }

    public MemberLaunchInfoVO(String descriptionSimple, String descriptionDetail, String phoneNum, String serviceNum) {
        this.descriptionSimple = descriptionSimple;
        this.descriptionDetail = descriptionDetail;
        this.phoneNum = phoneNum;
        this.serviceNum = serviceNum;
    }


}
