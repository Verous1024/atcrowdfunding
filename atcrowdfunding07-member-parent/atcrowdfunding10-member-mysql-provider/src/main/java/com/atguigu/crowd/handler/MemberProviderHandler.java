package com.atguigu.crowd.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.service.api.MemberService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
