package com.buy.goodinfo;

import java.util.EventObject;

import kd.bos.form.plugin.AbstractMobFormPlugin;
import kd.bos.servicehelper.user.UserServiceHelper;

public class GoodInfoMobile extends AbstractMobFormPlugin{
	@Override
    public void afterCreateNewData(EventObject e) {
		try {
			Long currentUserId = UserServiceHelper.getCurrentUserId();
			long mainOrgid = UserServiceHelper.getUserMainOrgId(currentUserId);
			this.getModel().setValue("ozwe_orgfield", mainOrgid);
			this.getModel().setValue("name", getView().getFormShowParameter().getCustomParam("name"));
			this.getModel().setValue("number", getView().getFormShowParameter().getCustomParam("number"));
	    	this.getModel().setValue("ozwe_goodcategory", getView().getFormShowParameter().getCustomParam("category"));
	    	this.getModel().setValue("ozwe_introduction", getView().getFormShowParameter().getCustomParam("introduction"));
	    	this.getModel().setValue("ozwe_goodpicture", getView().getFormShowParameter().getCustomParam("img"));
	    	
	    	this.getModel().setValue("ozwe_size", getView().getFormShowParameter().getCustomParam("size") , 0);
	    	this.getModel().setValue("ozwe_company", getView().getFormShowParameter().getCustomParam("company") , 0);
		} catch(Exception ee) {
			
		}
		
	}
}
