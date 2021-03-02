package com.atguigu.crowd.entity;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-02 下午 08:16
 */
public class ReturnVO {
    private String content;
    private String descPicPath;

    public ReturnVO(String content, String descPicPath) {
        this.content = content;
        this.descPicPath = descPicPath;
    }

    public ReturnVO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescPicPath() {
        return descPicPath;
    }

    public void setDescPicPath(String descPicPath) {
        this.descPicPath = descPicPath;
    }
}
