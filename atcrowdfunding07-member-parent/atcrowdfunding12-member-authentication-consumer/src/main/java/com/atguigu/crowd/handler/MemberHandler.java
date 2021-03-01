package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.api.RedisRemoteService;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.entity.po.ProjectPO;
import com.atguigu.crowd.entity.vo.MemberLoginVO;
import com.atguigu.crowd.entity.vo.MemberVO;
import com.atguigu.crowd.entity.vo.MyCrowdProjectVO;
import com.atguigu.crowd.entity.vo.MySupportVO;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.MailService;
import com.atguigu.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private MySQLRemoteService mySQLRemoteService;

    @Autowired
    private MailService mailService;

    private Logger logger = LoggerFactory.getLogger(MemberHandler.class);

    @ResponseBody
    @RequestMapping("/auth/confirm/receipt")
    public ResultEntity<String> confirmMyReceipt(@RequestParam("orderId") Integer orderId) {
        ResultEntity<String> resultEntity =  mySQLRemoteService.confirmMyReceipt(orderId);
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            return ResultEntity.successWithoutData();
        }
        return ResultEntity.failed("失败");
    }


    @RequestMapping("auth/save/personal/information")
    public String savePersonalInformation(MemberPO memberPO,HttpSession session) {
        mySQLRemoteService.updateMember(memberPO);
        MemberPO loginMember = (MemberPO) session.getAttribute("loginMember");
        loginMember.setLoginacct(memberPO.getLoginacct());
        loginMember.setUsername(memberPO.getUsername());
        loginMember.setEmail(memberPO.getEmail());
        loginMember.setPhonenum(memberPO.getPhonenum());
        session.setAttribute("loginMember",loginMember);
        return "redirect:http://localhost/auth/member/to/center/page";
    }

    @RequestMapping("/delete/my/project/{projectId}")
    public String deleteMyProjectById(@PathVariable("projectId")Integer projectId) {
        ResultEntity<String> resultEntity =  mySQLRemoteService.deleteMyProjectByIdRemote(projectId);
        return "redirect:http://localhost/member/my/crowd";
    }

    @RequestMapping("/unsubscribe/{projectId}")
    public String unsubscribe(@PathVariable("projectId") Integer projectId,HttpSession session){
        MemberPO loginMember = (MemberPO) session.getAttribute("loginMember");
        ResultEntity<String> resultEntity =  mySQLRemoteService.unsubscribeRemote(projectId,loginMember.getId());
        return "redirect:http://localhost/member/my/crowd#attension";
    }

    @RequestMapping("/delete/my/order/{orderId}")
    public String deleteMyOrder(@PathVariable("orderId") Integer orderId) {
            ResultEntity<String> resultEntity =  mySQLRemoteService.deleteMyOrderRemote(orderId);
            return "redirect:http://localhost/member/my/crowd";
    }

    /**
     * 请求转发到我的众筹页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/member/my/crowd")
    public String toMyCrowd(HttpSession session, Model model) throws ParseException {
        MemberPO loginMember = (MemberPO) session.getAttribute("loginMember");
        Integer memberId = loginMember.getId();
        ResultEntity<List<MySupportVO>> mySupport1 = mySQLRemoteService.getMySupport(memberId);
        ResultEntity<List<ProjectPO>> myFocus1 = mySQLRemoteService.getMyFocus(memberId);
        ResultEntity<List<ProjectPO>> myProject1 = mySQLRemoteService.getMyProject(memberId);
        if(ResultEntity.SUCCESS.equals(mySupport1.getResult())
                && ResultEntity.SUCCESS.equals(myFocus1.getResult())
                && ResultEntity.SUCCESS.equals(myProject1.getResult())){ //全部数据查找且无误
            List<MySupportVO> mySupport =mySupport1.getData();
            //mySupport的剩余时间、还没有计算,支持日期
            for (MySupportVO supportVO : mySupport) {
                logger.info(supportVO.toString());
                Integer day = supportVO.getDay();
                String deploydate = supportVO.getDeploydate();
                Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(deploydate);
                Date date = new Date();
                Long existDay =(Long)( (date.getTime() - parse.getTime())/1000/60/60/24);
                supportVO.setLastDays(day - existDay);
                String timeString = supportVO.getOrderNum().substring(0,14);
                Date supportime = new SimpleDateFormat("yyyyMMddHHmmss").parse(timeString);
                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(supportime);
                supportVO.setSupportTime(format);
                int per = (int) (supportVO.getSupportmoney() * 100 / supportVO.getMoney() ) ;
                logger.info("per"+per);
                supportVO.setPercentage(per);
            }
            List<ProjectPO> myFocusPO = myFocus1.getData();
            ArrayList<MyCrowdProjectVO> myFocus = new ArrayList<MyCrowdProjectVO>();
            for (ProjectPO focusPO : myFocusPO) {
                MyCrowdProjectVO focusVO = new MyCrowdProjectVO();
                BeanUtils.copyProperties(focusPO,focusVO);
                Integer day = focusVO.getDay();
                Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(focusVO.getDeploydate());
                Date date = new Date();
                Long existDay =(Long)( (date.getTime() - parse.getTime())/1000/60/60/24);
                focusVO.setLastDays(day - existDay);
                int per = (int) (focusVO.getSupportmoney()* 100 / focusVO.getMoney() ) ;
                focusVO.setPercentage(per);
                myFocus.add(focusVO);
            }
            //我的关注
            List<ProjectPO> myProjectPO = myProject1.getData();
            ArrayList<MyCrowdProjectVO> myProject = new ArrayList<MyCrowdProjectVO>();
            for (ProjectPO projectPO : myProjectPO) {
                MyCrowdProjectVO projectVO = new MyCrowdProjectVO();
                BeanUtils.copyProperties(projectPO,projectVO);
                Integer day = projectVO.getDay();
                String deploydate = projectVO.getDeploydate();
                Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(deploydate);
                Date date = new Date();
                Long existDay =(Long)( (date.getTime() - parse.getTime())/1000/60/60/24);
                projectVO.setLastDays(day - existDay);
                int per = (int) (projectVO.getSupportmoney() * 100 / projectVO.getMoney() ) ;
                projectVO.setPercentage(per);
                myProject.add(projectVO);
            }
            model.addAttribute("mySupport",mySupport);
            model.addAttribute("myFocus",myFocus);
            model.addAttribute("myProject",myProject);
            return "member-crowd";
        }
        return "member-center"; //如果查询数据失败，就返回到自己的前一个页面
    }

    @ResponseBody
    @RequestMapping("/do/verifiy")
    public ResultEntity<String> doVerifiy(MemberPO memberPO, @RequestParam("code") String code, HttpSession session) {
        logger.info(memberPO.toString());
        logger.info(code);
        memberPO.setAuthstatus(1);
        //省份证检查
        ResultEntity<String> resultEntity = CrowdUtil.sendCardByShortMessage(memberPO.getCardnum(), memberPO.getRealname());
        if (!ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
            return ResultEntity.failed("身份证号码不匹配！").setCodeWithRe("400");
        }
        logger.info("身份通过！");
        //验证码检查
        String key = CrowdConstant.REDIS_CODE_PREFIX + memberPO.getEmail();
        ResultEntity<String> saveCodeResultEntity = redisRemoteService.getRedisStringValueByKeyRemote(key);
        if (ResultEntity.SUCCESS.equals(saveCodeResultEntity.getResult())) {
            String redisCode = saveCodeResultEntity.getData();
            logger.info("rediscode=" + redisCode);
            if (redisCode == null) {
                return ResultEntity.failed("验证码已失效！").setCodeWithRe("200");
            } else {
                if (Objects.equals(redisCode, code)) {
                    //验证码无误
                    MemberPO loginMember = (MemberPO) session.getAttribute("loginMember");
                    loginMember.setAuthstatus(1);
                    loginMember.setUsertype(memberPO.getUsertype());
                    loginMember.setRealname(memberPO.getRealname());
                    loginMember.setCardnum(memberPO.getCardnum());
                    loginMember.setAccttype(memberPO.getAccttype());
                    logger.info(loginMember.toString());
                    session.setAttribute("loginMember", loginMember);
                    mySQLRemoteService.updateMember(loginMember);
                    return ResultEntity.successWithoutData();
                } else {
                    return ResultEntity.failed("验证码不正确！");
                }
            }
        } else {
            return ResultEntity.failed("服务器异常").setCodeWithRe("300");
        }
    }


    @ResponseBody
    @RequestMapping("/send/code/by/email")
    public ResultEntity<String> sendMessageByEmail(@RequestParam("email") String email) {
        logger.info(email);
        //生成随机码
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * 10);
            builder.append(random);
        }
        String code = builder.toString();
        String subject = "西大众筹网！实名认证";
        String content = "<p>您的验码如下<p>" + "<h2 text-align='center'>" + code + "</h2>" + "<p>请在2分钟之内完成注册，否则验证码失效</p>";
        boolean result = mailService.sendHtmlMail(email, subject, content);
        if (result == false) {
            return ResultEntity.failed("发送验证码失败！");
        } else {
            //3、发送成果存入到Redis中
            String key = CrowdConstant.REDIS_CODE_PREFIX + email;
            ResultEntity<String> saveCodeResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, code, 2/*, TimeUnit.MINUTES*/);
            if (!ResultEntity.SUCCESS.equals(saveCodeResultEntity.getResult())) {
                return ResultEntity.failed("发送验证码失败！");
            }
            return ResultEntity.successWithoutData();
        }
    }

    @RequestMapping("/to/verified/page.html/{acctType}")
    public String toVerifiedPage(@PathVariable("acctType") Integer acctType, Model model) {
        if (acctType == 1) {
            model.addAttribute("userType", 1);
            model.addAttribute("acctType", 0);
        } else if (acctType == 2) {
            model.addAttribute("userType", 0);
            model.addAttribute("acctType", 1);
        } else if (acctType == 3) {
            model.addAttribute("userType", 0);
            model.addAttribute("acctType", 2);
        } else if (acctType == 4) {
            model.addAttribute("userType", 1);
            model.addAttribute("acctType", 3);
        } else {
            return "identity-select";
        }
        return "member-apply";
    }


    @RequestMapping("/auth/member/logout")
    public String logout(HttpSession session) {
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
        logger.info("memberPO" + memberPO);
        String dbpswd = memberPO.getUserpswd();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //有有户名，但密码不争取
        if (!passwordEncoder.matches(userpswd, dbpswd)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_FAILED).setCodeWithRe("200");
            //200表示密码错误
        }

        MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUsername(), memberPO.getEmail());

        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER, memberPO); //这里被我修改了

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
        if (ResultEntity.FAILED.equals(result)) {
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
        BeanUtils.copyProperties(memberVO, memberPO);
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
        System.out.println("phoneNum" + phoneNum);
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
