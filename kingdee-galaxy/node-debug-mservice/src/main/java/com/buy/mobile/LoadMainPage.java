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

public class LoadMainPage extends AbstractMobFormPlugin{
	@Override
    public void afterCreateNewData(EventObject e) {
		
		Long currentUserId = UserServiceHelper.getCurrentUserId();
		Long mainOrgId = UserServiceHelper.getUserMainOrgId(currentUserId);
		getModel().setValue("ozwe_orgfield", mainOrgId);
		
		Label label = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_kdtest_labelap11");
		label.setText(RequestContext.get().getUserName());
		
		DynamicObject[] goods = BusinessDataServiceHelper.load("ozwe_goodslist",
				"ozwe_orgfield",
				new QFilter[]{new QFilter("ozwe_orgfield",QCP.equals,mainOrgId)});
		Label label2 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_kdtest_kdtest_kdtest_labelap91");
		label2.setText(goods.length+"");
		
		DynamicObject[] applies = BusinessDataServiceHelper.load("ozwe_apply",
				"billno,"
				+ "billstatus,"
				+ "ozwe_createdate,"
				+ "ozwe_org",
				new QFilter[]{new QFilter("ozwe_org",QCP.equals,mainOrgId)});
		Label label3 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_labelap933");
		label3.setText(applies.length+"");
		
		DynamicObject[] appliesWaiting = BusinessDataServiceHelper.load("ozwe_apply",
				"billno,"
				+ "billstatus,"
				+ "ozwe_org",
				new QFilter[]{new QFilter("billstatus",QCP.equals,"B"),
						new QFilter("ozwe_org",QCP.equals,mainOrgId)});
		Label label4 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_kdtest_kdtest_kdtest_labelap92");
		label4.setText(appliesWaiting.length+"");
		
		DynamicObject[] orders = BusinessDataServiceHelper.load("ozwe_order1",
				"billno,"
				+ "ozwe_ozwe_org",
				new QFilter[]{new QFilter("ozwe_ozwe_org",QCP.equals,mainOrgId)});
		Label label5 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_kdtest_kdtest_kdtest_labelap9");
		label5.setText(orders.length+"");
		
		long todayApply = 0;
		long todayPass = 0;
		long todayGet = 0;
		long finish = 0;
		for (DynamicObject dObject : applies) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(dObject.getDate("ozwe_createdate"));
			String today = sdf.format(new Date());
			if (date.equalsIgnoreCase(today)) {
				todayApply++;
				if (dObject.getString("billstatus").equalsIgnoreCase("C")) {
					DynamicObject orderFinish = BusinessDataServiceHelper.loadSingle("ozwe_order1",
							"billno",
							new QFilter[]{new QFilter("billno",QCP.equals,dObject.getString("billno"))});
					if (orderFinish != null) {
						finish++;
					} else {
						todayGet++;
					}
				} else if (dObject.getString("billstatus").equalsIgnoreCase("B")) {
					todayPass++;
				}
			}
			Label label6 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_labelap1251");
			label6.setText(todayApply+"");
			Label label7 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_kdtest_labelap12513");
			label7.setText(todayPass+"");
			Label label8 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_kdtest_labelap12514");
			label8.setText(todayGet+"");
			Label label9 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_kdtest_kdtest_labelap12513");
			label9.setText(finish+"");
		}
//		Label label6 = getView().getControl("ozwe_kdtest_kdtest_kdtest_kdtest_labelap1251");
//		label6.setText(appliesToday.length+"");
	}
}
