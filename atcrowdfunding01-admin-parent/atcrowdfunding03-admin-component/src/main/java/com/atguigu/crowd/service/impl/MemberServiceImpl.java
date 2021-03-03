package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Member;
import com.atguigu.crowd.entity.MemberExample;
import com.atguigu.crowd.mapper.MemberMapper;
import com.atguigu.crowd.service.api.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-03 上午 11:14
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public PageInfo<Member> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        MemberExample memberExample = new MemberExample();
        MemberExample.Criteria criteria = memberExample.createCriteria();
        Boolean flag = false;
        if(keyword.contains("企业用户")){
            criteria.andUsertypeEqualTo(0);
            flag = true;
        } else if (keyword.contains("个人用户")) {
            criteria.andUsertypeEqualTo(1);
            flag = true;
        }else if (keyword.contains("个人")) {
            criteria.andAccttypeEqualTo(0);
            flag = true;
        }else if (keyword.contains("企业")) {
            criteria.andAccttypeEqualTo(1);
            flag = true;
        }else if (keyword.contains("政府")) {
            criteria.andAccttypeEqualTo(2);
            flag = true;
        }else if (keyword.contains("个体")) {
            criteria.andAccttypeEqualTo(3);
            flag = true;
        }else if (keyword.contains("未实名认证")) {
            criteria.andAuthstatusEqualTo(0);
            flag = true;
        }else if (keyword.contains("实名认证申请中")) {
            criteria.andAuthstatusEqualTo(1);
            flag = true;
        }else if (keyword.contains("已实名认证")) {
            criteria.andAuthstatusEqualTo(2);
            flag = true;
        }else if (keyword.contains("实名认证申请失败")) {
            criteria.andAuthstatusEqualTo(3);
            flag = true;
        }else if (keyword.contains("账号异常")) {
            criteria.andAuthstatusEqualTo(4);
            flag = true;
        }

        if (flag) {
            PageHelper.startPage(pageNum, pageSize);
            List<Member> members = memberMapper.selectByExample(memberExample);
            return new PageInfo<>(members);
        }else{
            PageHelper.startPage(pageNum, pageSize);
            List<Member> members = memberMapper.selectMemberByKeyword(keyword);
            return new PageInfo<>(members);
        }
    }

    @Override
    public Member getMember(Integer memberId) {
       return memberMapper.selectByPrimaryKey(memberId);
    }

    @Override
    public void doExaminationPass(Integer memberId, Integer status) {
        Member member = new Member();
        member.setId(memberId);
        member.setAuthstatus(status);
        memberMapper.updateByPrimaryKeySelective(member);
    }
}
