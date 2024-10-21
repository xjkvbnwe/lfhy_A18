package com.buy.intelligentmarketing.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapMergePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.buy.intelligentmarketing.dao.AlipayDao;
import com.buy.intelligentmarketing.entity.truth.Alipay;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class AlipayService implements AlipayDao {
    @Override
    public void aliPay(HttpServletResponse response, HttpServletRequest request , String order , String amount) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(Alipay.gatewayUrl, Alipay.app_id, Alipay.merchant_private_key, "json", Alipay.charset, Alipay.alipay_public_key, Alipay.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest  aliPayRequest = new AlipayTradePagePayRequest ();
        aliPayRequest.setReturnUrl(Alipay.return_url);
        aliPayRequest.setNotifyUrl(Alipay.notify_url);

        //订单名称，必填
        String subject = "随手买进货";
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order + "\","
                + "\"total_amount\":\"" + amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(aliPayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //输出
        out.println(result);
    }
}
