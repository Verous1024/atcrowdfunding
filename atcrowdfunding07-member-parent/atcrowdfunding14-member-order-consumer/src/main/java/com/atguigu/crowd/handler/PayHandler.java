package com.atguigu.crowd.handler;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.config.PayProperties;
import com.atguigu.crowd.entity.vo.OrderProjectVO;
import com.atguigu.crowd.entity.vo.OrderVO;
import com.atguigu.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-25 下午 03:07
 */
@Controller
public class PayHandler {

    private Logger logger = LoggerFactory.getLogger(PayHandler.class);

    @Autowired
    private PayProperties payProperties;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @ResponseBody
    @RequestMapping("/notify")
    public String notifyUrlMethod(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name =  iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, payProperties.getAlipayPublicKey(), payProperties.getCharset(), payProperties.getSignType()); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        4、验证app_id是否为该商户本身。
        */
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            logger.info("trade_status="+trade_status);
            return "";

        }else {//验证失败
            logger.info("校验失败");
        }
        return "";
    }


    @RequestMapping("/return")
    public String returnUrlMethod(HttpServletRequest request, HttpSession session, Model model) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, payProperties.getAlipayPublicKey(), payProperties.getCharset(), payProperties.getSignType()); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String orderNum  = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String payOrderNum = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String orderAmount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            //保存到数据库
            OrderVO orderVO = (OrderVO)session.getAttribute("orderVO");
            Integer orderedProjectId =(Integer) session.getAttribute("orderedProjectId");
            Integer orderedReturnId =(Integer) session.getAttribute("orderedReturnId");
            logger.info(orderVO.toString());
            orderVO.setProjectId(orderedProjectId);
            orderVO.setReturnId(orderedReturnId);
            orderVO.setPayOrderNum(payOrderNum);
            logger.info(orderVO.toString());
            ResultEntity<String> resultEntity = mySQLRemoteService.saveOrderRemote(orderVO);
            logger.info("订单信息保存到数据库的操作"+resultEntity.getResult());
            model.addAttribute("message","支付成功！");
            return "system";
        }else {
            //校验失败
            model.addAttribute("message","支付成功！");
            return "system";
        }
    }

    //这里必须添加responsebody让浏览器显示支付宝登陆界面
    @ResponseBody
    @RequestMapping("/pay/generate/order")
    public String generateOrder(HttpSession session, OrderVO orderVO) throws AlipayApiException {
        //取出orderProjectVO并存入orderVO
        OrderProjectVO orderProjectVO = (OrderProjectVO)session.getAttribute("orderProjectVO");
        orderVO.setOrderProjectVO(orderProjectVO);
        //生成订单号并存入VO
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String user = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String orderNum = time+ user;
        orderVO.setOrderNum(orderNum);
        //生成订单的金额
        Double orderAmount =(double) (orderProjectVO.getSupportPrice() * orderProjectVO.getReturnCount() + orderProjectVO.getFreight());
        orderVO.setOrderAmount(orderAmount);

        session.setAttribute("orderVO",orderVO);

        //调用专门封装好的方法执行
        return sendRequestToAliPay(orderNum,orderAmount.toString(),orderProjectVO.getProjectName(),orderProjectVO.getReturnContent());
    }

    /**
     * 为了调用阿里字支付使用的方法
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param total_amount 付款金额，必填
     * @param subject 订单名称，必填
     * @param body 商品描述，可空
     * @throws AlipayApiException
     */
    private String sendRequestToAliPay(String out_trade_no,String total_amount,String subject,String body) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(payProperties.getGatewayUrl(), payProperties.getAppId(), payProperties.getMerchantPrivateKey(), "json", payProperties.getCharset(), payProperties.getAlipayPublicKey(), payProperties.getSignType());

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(payProperties.getReturnUrl());
        alipayRequest.setNotifyUrl(payProperties.getNotifyUrl());

/*        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");*/

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        return result; //将请求返回
    }
}
