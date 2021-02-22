package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.api.RedisRemoteService;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.entity.vo.MemberVO;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 10:05
 */
@Controller
public class MemberHandler {

    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MySQLRemoteService  mySQLRemoteService;

    @RequestMapping("/auth/do/member/register")
    public String register(MemberVO memberVO,Model model) {
        System.out.println(memberVO.toString());
        //1、获取注册的手机号
        String phonenum = memberVO.getPhonenum();
        //2、拼接检查Reids的前缀的key
        String key = CrowdConstant.REDIS_CODE_PREFIX + phonenum;
        //3、从Redis读取value
        ResultEntity<String> resultEntity = redisRemoteService.getRedisStringValueByKeyRemote(key);

        //4、检查结果是否有效
        String result = resultEntity.getResult();
        if(ResultEntity.FAILED.equals(result)){
            //操作失败，Redis工程操作读取key值失败；
            model.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, resultEntity.getMessage());
            return "member-reg";
        }
        String redisCode = resultEntity.getData();

        if (redisCode == null) {
            //操作成功，Redis工程操作读取key值成功,但验证码不存在，过期了
            model.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_NOT_EXISTS);
            return "member-reg";
        }
        //获取表单中的验证码
        String formCode = memberVO.getCode();
        //校验验证码是否一致
        if (!Objects.equals(formCode, redisCode)) {
            //验证码不一致
            model.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_INVALID);
            return "member-reg";
        }
        //验证码一致，删除Redis中的校验码
        ResultEntity<String> removeResultEntity = redisRemoteService.removeRedisKeyRemote(key);


        //将表单中的密码加密后放回到memberVO
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userpswdBeforeEncode = memberVO.getUserpswd();
        String userpswdAfterEncode = passwordEncoder.encode(userpswdBeforeEncode);
        memberVO.setUserpswd(userpswdAfterEncode);
        //memberVO转换到merberPO，然后调用sql工程的方法执行保存
        MemberPO memberPO = new MemberPO();
        BeanUtils.copyProperties(memberVO,memberPO);
        ResultEntity<String> saveResultEntity = mySQLRemoteService.saveMember(memberPO);
        if (ResultEntity.FAILED.equals(saveResultEntity.getResult())) {
            //保存用户信息失败
            model.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, saveResultEntity.getMessage());
            return "member-reg";
        }
        return "member-login";
    }

    @ResponseBody
    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {
        System.out.println("phoneNum"+phoneNum);
        //1、发送验证码到手机
        ResultEntity<String> sendMesssageResultEntity = CrowdUtil.sendCodeByShortMessage(phoneNum);
        //2、判断短信发送结果
        if (sendMesssageResultEntity.getResult() == "SUCCESS") {
            //3、发送成果存入到Redis中
            String code = sendMesssageResultEntity.getData();
            String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;
            ResultEntity<String> saveCodeResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, code, 1/*, TimeUnit.MINUTES*/);
           // ResultEntity<String> saveCodeResultEntity = redisRemoteService.setRedisKeyValueRemote(key, code);
            if (ResultEntity.SUCCESS.equals(saveCodeResultEntity.getResult())) {
                return ResultEntity.successWithoutData();
            } else {
                return saveCodeResultEntity;
            }
        } else {
            //发送失败
            return sendMesssageResultEntity;
        }
    }

}
