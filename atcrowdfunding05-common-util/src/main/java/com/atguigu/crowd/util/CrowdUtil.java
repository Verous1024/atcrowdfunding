package com.atguigu.crowd.util;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-15 下午 09:11
 */

import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.atguigu.crowd.constant.CrowdConstant;
import com.sun.org.apache.xml.internal.security.algorithms.Algorithm;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 工具方法类
 */
public class CrowdUtil {


    /**
     * 返回状态码，JSON数据中的0表示成功，1403表示手机号码不正确，1905验证不通过
     * @param phoneNum
     * @return
     */
    public static ResultEntity<String> sendCodeByShortMessage(String phoneNum) {
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "493cbdd6fcfb45a99bbc3dfb86b1bfc1";
        String smsSignId = "392298cf5475452c9109c49ccd5cde79";
        String templateId = "908e94ccf08b4476ba6c876d13f084ad";
        Integer minutes = 1;

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phoneNum);

        //生成随机码
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int random = (int) (Math.random()*10);
            builder.append(random);
        }
        String code = builder.toString();

        querys.put("param", "**code**:"+code+",**minute**:"+minutes);
        querys.put("smsSignId",smsSignId);
        querys.put("templateId",templateId);
        Map<String, String> bodys = new HashMap<String, String>();

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

            //获取response的body
            String responseString = EntityUtils.toString(response.getEntity());
            //判断
            if (responseString.contains("1403")) { //1403手机号不正确
                return ResultEntity.failed("1403");
            } else if (responseString.contains("成功")) { //成功发送验证码
                return ResultEntity.successWithData(code);
            } else {
                return ResultEntity.failed("运行商发送消息失败!等待后台维护！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 判断当前请求是否是Ajax请求
     *
     * @param request 请求域对象
     * @return true 表示是ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        return (acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                (xRequestHeader != null && xRequestHeader.contains("XMLHttpRequest"));
    }

    /**
     * @param source 待加密的明文字符串
     * @return 加密后的密文
     */
    public static String md5(String source) {

        if (source == null || source.length() == 0) {
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }

        String algorithm = "md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            byte[] input = source.getBytes();

            byte[] output = messageDigest.digest(input);

            int signum=1;
            BigInteger bigInteger = new BigInteger(signum, output);

            int radix=16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
