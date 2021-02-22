package com.atguigu.crowd.entity.vo;


/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-22 上午 10:55
 */

public class MemberVO {

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String phonenum;

    private String code;

    public MemberVO() {
    }

    public MemberVO(String loginacct, String userpswd, String username, String email, String phonenum, String code) {
        this.loginacct = loginacct;
        this.userpswd = userpswd;
        this.username = username;
        this.email = email;
        this.phonenum = phonenum;
        this.code = code;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "loginacct='" + loginacct + '\'' +
                ", userpswd='" + userpswd + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
