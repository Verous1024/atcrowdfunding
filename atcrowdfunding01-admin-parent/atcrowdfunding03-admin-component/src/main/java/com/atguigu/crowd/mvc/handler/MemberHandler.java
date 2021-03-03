package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Member;
import com.atguigu.crowd.entity.MemberWithPic;
import com.atguigu.crowd.service.api.MemberService;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-03-03 上午 11:11
 */
@Controller
public class MemberHandler {
    @Autowired
    private MemberService memberService;

    @PreAuthorize("hasAuthority('apply:pass')")
    @ResponseBody
    @RequestMapping("/member/do/examination/pass.json")
    public ResultEntity<String> doExaminationPass(
            @RequestParam("memberId") Integer memberId,
            @RequestParam("status") Integer status) {
        try {
            memberService.doExaminationPass(memberId, status);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('apply:get')")
    @ResponseBody
    @RequestMapping("/member/get/detail/member.json")
    public ResultEntity<MemberWithPic> getProjectDetailVO(
            @RequestParam(value = "memberId") Integer memberId) {
        Member mymember = memberService.getMember(memberId);
        List<String> mypic = memberService.getPic(memberId);
        MemberWithPic memberWithPic = new MemberWithPic();
        memberWithPic.setMember(mymember);
        memberWithPic.setDetailPicturePath(mypic);
        return ResultEntity.successWithData(memberWithPic);
    }

    @PreAuthorize("hasAuthority('apply:get')")
    @ResponseBody
    @RequestMapping("/verify/get/page/info.json")
    public ResultEntity<PageInfo<Member>> getPageInfo(
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
        PageInfo<Member> pageInfo = memberService.getPageInfo(pageNum, pageSize, keyword);
        return ResultEntity.successWithData(pageInfo);
    }
}
