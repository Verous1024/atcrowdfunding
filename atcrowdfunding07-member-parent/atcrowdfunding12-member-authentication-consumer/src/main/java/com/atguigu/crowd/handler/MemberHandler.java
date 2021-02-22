package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.api.RedisRemoteService;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.entity.vo.MemberLoginVO;
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

import javax.servlet.http.HttpSession;
import java.util.Objects;


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


    @RequestMapping("/auth/member/logout")
    public String logout(HttpSession session){
        session.invalidate();

        return "portal";

    }

    @ResponseBody
    @RequestMapping("/auth/member/do/login")
    public ResultEntity<String> login(@RequestParam("loginacct") String loginacct,
                        @RequestParam("userpswd") String userpswd,
                        HttpSession session) {

        ResultEntity<MemberPO> memberPOByLoginAcctRemote = mySQLRemoteService.getMemberPOByLoginAcctRemote(loginacct);

        //获取失败，mysql工程出现错误
        if (ResultEntity.FAILED.equals(memberPOByLoginAcctRemote.getResult())) {
            //300属于服务器的问题，不归属用户
           return ResultEntity.failed(CrowdConstant.MESSAGE_UNIVERSAL_ERROR_INFORMATION).setCodeWithRe("300");
        }

        MemberPO memberPO = memberPOByLoginAcctRemote.getData();

        //获取到的对象是一个空的值。即用户不存在
        if (memberPO == null) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_FAILED_WITHOUT_ACCT).setCodeWithRe("100");
            //100:表示用户不存在
        }

        String dbpswd = memberPO.getUserpswd();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //有有户名，但密码不争取
        if (! passwordEncoder.matches(userpswd, dbpswd)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_FAILED).setCodeWithRe("200");
            //200表示密码错误
        }

        MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUsername(), memberPO.getEmail());

        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER,memberLoginVO);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/auth/do/member/register")
    public ResultEntity<String> register(MemberVO memberVO) {
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
            //redis中没有获取到对应key,认为没有获取验证码
            return ResultEntity.failed("请获取验证码后再输入！"); //默认状态码100
        }
        String redisCode = resultEntity.getData();

        if (redisCode == null) {
            //操作成功，Redis工程操作读取key值成功,但验证码不存在，过期了
            return ResultEntity.failed(CrowdConstant.MESSAGE_CODE_NOT_EXISTS); //默认状态码100
        }
        //获取表单中的验证码
        String formCode = memberVO.getCode();
        //校验验证码是否一致
        if (!Objects.equals(formCode, redisCode)) {
            //验证码不一致
            return ResultEntity.failed(CrowdConstant.MESSAGE_CODE_INVALID); //默认状态码100
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
            return ResultEntity.failed("数据库保存用户信息失败！").setCodeWithRe("200");
        }
        return ResultEntity.successWithoutData();
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
