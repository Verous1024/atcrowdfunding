package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.entity.po.MemberPOExample;
import com.atguigu.crowd.mapper.MemberPOMapper;
import com.atguigu.crowd.service.api.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 01:07
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberPOMapper;
    private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginacct) {

        MemberPOExample example=new MemberPOExample();

        MemberPOExample.Criteria criteria=example.createCriteria();

        criteria.andLoginacctEqualTo(loginacct);

        List<MemberPO> list = memberPOMapper.selectByExample(example);

        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class,readOnly = false)
    @Override
    public void saveMember(MemberPO memberPO) {
        logger.info(memberPO.toString());
        memberPOMapper.insertSelective(memberPO);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class,readOnly = false)
    @Override
    public void updateMember(MemberPO loginMember) {
        logger.info(loginMember.toString());
        memberPOMapper.updateByPrimaryKey(loginMember);
    }
}
