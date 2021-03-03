package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.Member;
import com.github.pagehelper.PageInfo;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-03 上午 11:14
 */
public interface MemberService {
    PageInfo<Member> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    Member getMember(Integer memberId);

    void doExaminationPass(Integer memberId, Integer status);
}
