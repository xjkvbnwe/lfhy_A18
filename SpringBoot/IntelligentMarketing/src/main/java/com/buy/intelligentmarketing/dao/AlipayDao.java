package com.buy.intelligentmarketing.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AlipayDao {
    void  aliPay(HttpServletResponse response, HttpServletRequest request , String order , String amount) throws IOException;
}
