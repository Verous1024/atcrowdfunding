package com.atguigu.crowd.util;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-15 下午 09:11
 */

import com.atguigu.crowd.constant.CrowdConstant;
import com.sun.org.apache.xml.internal.security.algorithms.Algorithm;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 工具方法类
 */
public class CrowdUtil {

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
