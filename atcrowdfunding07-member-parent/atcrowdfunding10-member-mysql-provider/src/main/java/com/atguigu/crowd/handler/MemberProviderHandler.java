package com.atguigu.crowd.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.service.api.MemberService;
import com.atguigu.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 01:06
 */
@RestController
public class MemberProviderHandler {

    @Autowired
    private MemberService memberService;

    private Logger logger = LoggerFactory.getLogger(MemberProviderHandler.class);

    @RequestMapping("/save/member/qualification")
    public ResultEntity<String> saveMemberQualification(@RequestParam("detailPicturePathList") List<String> detailPicturePathList,@RequestParam("memberId")Integer memberId){
        logger.info(detailPicturePathList.get(0));
        logger.info(detailPicturePathList.size()+"");
        try {
            //先删除所有关于该member的文件
            memberService.deleteMyQua(memberId);
            //然后再保存
            memberService.insertMyQua(memberId,detailPicturePathList);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/update/member")
    public ResultEntity<String> updateMember(@RequestBody MemberPO loginMember){
        try {
            memberService.updateMember(loginMember);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            return ResultEntity.failed(e.getMessage());
        }
    }

    //通过远程接口调用mysql工程的请求时，必须要有@RequestBody
    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO) {
        try {
            memberService.saveMember(memberPO);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct) {

        try {

            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);

            return ResultEntity.successWithData(memberPO);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }
}
