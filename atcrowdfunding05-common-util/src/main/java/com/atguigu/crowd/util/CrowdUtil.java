package com.atguigu.crowd.util;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-15 下午 09:11
 */

import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.atguigu.crowd.constant.CrowdConstant;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 工具方法类
 */
public class CrowdUtil {


    /**
     * 专门负责上传文件到 OSS 服务器的工具方法
     *
     * @param endpoint        OSS 参数
     * @param accessKeyId     OSS 参数
     * @param accessKeySecret OSS 参数
     * @param inputStream     要上传的文件的输入流
     * @param bucketName      OSS 参数
     * @param bucketDomain    OSS 参数
     * @param originalName    要上传的文件的原始文件名
     * @return 包含上传结果以及上传的文件在 OSS 上的访问路径
     */
    public static ResultEntity<String> uploadFileToOss(String endpoint, String accessKeyId, String accessKeySecret, InputStream inputStream,
                                                       String bucketName, String bucketDomain, String originalName) {
        //创建OSSClient实例
        //需要 域名节点、该域名节点上的哪个用户、该用户的密码是多少
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //生成上传文件的目录
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 生成上传文件在 OSS 服务器上保存时的文件名
        // 原始文件名：beautfulgirl.jpg
        // 生成文件名：wer234234efwer235346457dfswet346235.jpg

        // 使用 UUID 生成文件主体名
        String fileMainName = UUID.randomUUID().toString().replace("-", "");

        // 从原始文件名中获取文件扩展名
        String extensionName = originalName.substring(originalName.lastIndexOf("."));

        // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
        String objectName = folderName + "/" + fileMainName + extensionName;

        try {

            // 调用 OSS 客户端对象的方法上传文件并获取响应结果数据
            //不设置文件路径传送过去时，会直接传送到根目录下
            //objectName是传送的文件的目录以及文件的名字 -- 目的城市的中具体位置
            //inputStream是需要传送文件的输入流 -- 道路
            //buckName表示需要传送的OSS桶 -- 目的地的城市
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);

            // 从响应结果中获取具体响应消息 -- 响应
            ResponseMessage responseMessage = putObjectResult.getResponse();

            // 根据响应状态码判断请求是否成功
            if (responseMessage == null) {
                //拼接访问刚刚上传的文件路径
                String ossFileAccessPath = bucketDomain + "/" + objectName;

                return ResultEntity.successWithData(ossFileAccessPath);
            }else{
                int statusCode = responseMessage.getStatusCode();

                String errorResponseAsString = responseMessage.getErrorResponseAsString();

                return ResultEntity.failed("当前响应状态码="+statusCode+" 错误消息="+errorResponseAsString);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());

        }
        finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

/*    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("beauty.jpg");
        ResultEntity<String> resultEntity = CrowdUtil.uploadFileToOss("http://oss-cn-chengdu.aliyuncs.com", "LTAI4G5UUmcckQKxtnoEo3yV", "710Br6Gv50nYVR0mjbbMspu440SK5r", inputStream, "verous1024", "http://verous1024.oss-cn-chengdu.aliyuncs.com", "beauty.jpg");
        System.out.println(resultEntity);
    }*/

    /**
     * 返回状态码，JSON数据中的0表示成功，1403表示手机号码不正确，1905验证不通过
     *
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
            int random = (int) (Math.random() * 10);
            builder.append(random);
        }
        String code = builder.toString();

        querys.put("param", "**code**:" + code + ",**minute**:" + minutes);
        querys.put("smsSignId", smsSignId);
        querys.put("templateId", templateId);
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

            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
