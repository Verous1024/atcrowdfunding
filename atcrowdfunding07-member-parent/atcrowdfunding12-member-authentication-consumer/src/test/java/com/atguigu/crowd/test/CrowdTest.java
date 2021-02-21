package com.atguigu.crowd.test;

import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.atguigu.crowd.util.MailService;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-21 下午 08:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrowdTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMessage() {
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "493cbdd6fcfb45a99bbc3dfb86b1bfc1";

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "123123213213123");
        querys.put("param", "**code**:QQQQQQ,**minute**:3");
        querys.put("smsSignId", "392298cf5475452c9109c49ccd5cde79");
        querys.put("templateId", "908e94ccf08b4476ba6c876d13f084ad");
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
            System.out.println(response.toString());
            //获取response的body
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(EntityUtils.toString(response.getEntity()));
            System.out.println(statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void sendSimpleMail() throws Exception{
        mailService.sendMail("1987313921@qq.com","简单邮件","springboot实现邮件发送");
    }

    @Test
    public  void sendHtmlMail() throws  Exception{
        String content="<html>\n"+"<body>\n"
                + "<h3>hello world!测试发送html格式邮件</h3>\n"
                +"<button><a href=\"https://www.baidu.com/\">点击转发</a></button>"
                +"</body>\n"+"</html>";
        mailService.sendHtmlMail("1987313921@qq.com","发送html格式邮件",content);
    }


}
