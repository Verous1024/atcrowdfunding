package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.RedisRemoteService;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {


        //1、发送验证码到手机
        ResultEntity<String> sendMesssageResultEntity = CrowdUtil.sendCodeByShortMessage(phoneNum);
        //2、判断短信发送结果
        if (sendMesssageResultEntity.getResult() == "SUCCESS") {
            //3、发送成果存入到Redis中
            String code = sendMesssageResultEntity.getData();
            String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;
            ResultEntity<String> saveCodeResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, code, 1, TimeUnit.MINUTES);
            if (saveCodeResultEntity.getResult() == "SUCCESS") {
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
