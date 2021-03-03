package com.atguigu.crowd.entity;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-03 下午 08:10
 */
public class MemberWithPic {
    private Member member;
    private List<String> detailPicturePath;

    public MemberWithPic(Member member, List<String> detailPicturePath) {
        this.member = member;
        this.detailPicturePath = detailPicturePath;
    }

    public MemberWithPic() {
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<String> getDetailPicturePath() {
        return detailPicturePath;
    }

    public void setDetailPicturePath(List<String> detailPicturePath) {
        this.detailPicturePath = detailPicturePath;
    }
}
