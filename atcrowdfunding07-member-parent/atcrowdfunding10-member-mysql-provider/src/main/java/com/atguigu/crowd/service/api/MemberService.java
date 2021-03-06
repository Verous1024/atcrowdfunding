package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.po.MemberPO;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 01:07
 */
public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginacct);

    void saveMember(MemberPO memberPO);

    void updateMember(MemberPO loginMember);

    void deleteMyQua(Integer memberId);

    void insertMyQua(Integer memberId, List<String> detailPicturePathList);
}
