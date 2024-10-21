package com.buy.intelligentmarketing.controller;

import com.buy.intelligentmarketing.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/payment")
public class AlipayController {
    @Autowired
    AlipayService alipayService;


    @RequestMapping("/pay/{order}&{amount}")
    public void payMent(HttpServletResponse response, HttpServletRequest request , @PathVariable String order , @PathVariable String amount) {
        try {
            alipayService.aliPay(response,request , order , amount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
