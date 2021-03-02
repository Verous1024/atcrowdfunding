package com.atguigu.crowd.entity;


import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-02 下午 07:18
 */
public class ProjectDetailVO {
    private Integer projectId;
    private Integer memberId;
    private String projectName;
    private String projectDescription;
    private Long money;
    private Integer day;
    private Integer status;
    private String createdate;
    private String headerPicturePath;
    private List<String> detailPicturePath;
    private List<ReturnVO> listReturn;
    private MemberLaunchInfoVO memberLaunchInfo;
    private Member member;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
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

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getHeaderPicturePath() {
        return headerPicturePath;
    }

    public void setHeaderPicturePath(String headerPicturePath) {
        this.headerPicturePath = headerPicturePath;
    }

    public List<String> getDetailPicturePath() {
        return detailPicturePath;
    }

    public void setDetailPicturePath(List<String> detailPicturePath) {
        this.detailPicturePath = detailPicturePath;
    }


    public List<ReturnVO> getListReturn() {
        return listReturn;
    }

    public void setListReturn(List<ReturnVO> listReturn) {
        this.listReturn = listReturn;
    }


    public MemberLaunchInfoVO getMemberLaunchInfo() {
        return memberLaunchInfo;
    }

    public void setMemberLaunchInfo(MemberLaunchInfoVO memberLaunchInfo) {
        this.memberLaunchInfo = memberLaunchInfo;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public ProjectDetailVO() {
    }

    public ProjectDetailVO(Integer projectId, String projectName, String projectDescription, Long money, Integer day, Integer status, String createdate, String headerPicturePath, List<String> detailPicturePath, List<ReturnVO> listReturn, MemberLaunchInfoVO memberLaunchInfo, Member member) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.money = money;
        this.day = day;
        this.status = status;
        this.createdate = createdate;
        this.headerPicturePath = headerPicturePath;
        this.detailPicturePath = detailPicturePath;
        this.listReturn = listReturn;
        this.memberLaunchInfo = memberLaunchInfo;
        this.member = member;
    }
}
