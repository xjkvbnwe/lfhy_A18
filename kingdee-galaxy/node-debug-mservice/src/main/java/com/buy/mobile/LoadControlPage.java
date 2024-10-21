package com.buy.mobile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;

import kd.bos.context.RequestContext;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.form.control.Label;
import kd.bos.form.plugin.AbstractMobFormPlugin;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.user.UserServiceHelper;

public class LoadControlPage extends AbstractMobFormPlugin{
	@Override
    public void afterCreateNewData(EventObject e) {
		
		Long currentUserId = UserServiceHelper.getCurrentUserId();
		Long mainOrgId = UserServiceHelper.getUserMainOrgId(currentUserId);
		
		Label label = getView().getControl("ozwe_labelap2");
		label.setText(RequestContext.get().getUserName());
		
		DynamicObject[] selllogs = BusinessDataServiceHelper.load("ozwe_selllog",
				"owner_id,"
				+ "buy_amount",
				new QFilter[]{new QFilter("owner_id",QCP.not_equals,null)});
		Label label2 = getView().getControl("ozwe_labelap4");
		double totalAmount = 0;
		for (DynamicObject dy : selllogs) {
			totalAmount += Double.parseDouble(dy.getString("buy_amount"));
		}
		label2.setText("¥"+totalAmount+"");
		
		DynamicObject[] profits = BusinessDataServiceHelper.load("ozwe_profit",
				"ozwe_ownerid,"
				+ "ozwe_allamount",
				new QFilter[]{new QFilter("ozwe_ownerid",QCP.not_equals,null)});
		Label label3 = getView().getControl("ozwe_labelap41");
		double totalAmountProfit = 0;
		for (DynamicObject dy : profits) {
			totalAmountProfit += Double.parseDouble(dy.getString("ozwe_allamount"));
		}
		label3.setText("¥"+totalAmountProfit+"");
		
		DynamicObject[] advertisementTotal = BusinessDataServiceHelper.load("ozwe_advertisement",
				"number",
				new QFilter[]{new QFilter("number",QCP.not_equals,null)});
		Label label4 = getView().getControl("ozwe_labelap5");
		label4.setText(advertisementTotal.length+"");
		
		Label label5 = getView().getControl("ozwe_labelap54");
		label5.setText(selllogs.length+"");
		
		DynamicObject[] machineTotal = BusinessDataServiceHelper.load("ozwe_machine",
				"number",
				new QFilter[]{new QFilter("number",QCP.not_equals,null)});
		Label label6 = getView().getControl("ozwe_labelap55");
		label6.setText(machineTotal.length+"");
		
		DynamicObject[] goodsTotal = BusinessDataServiceHelper.load("ozwe_ownergoods",
				"owner_id",
				new QFilter[]{new QFilter("owner_id",QCP.not_equals,null)});
		Label label7 = getView().getControl("ozwe_labelap56");
		label7.setText(goodsTotal.length+"");
		
//		Label label6 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_labelap1251");
//		label6.setText(appliesToday.length+"");
	}
}
