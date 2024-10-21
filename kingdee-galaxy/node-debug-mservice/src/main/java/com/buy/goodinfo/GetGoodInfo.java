package com.buy.goodinfo;

import java.util.EventObject;

import org.json.JSONObject;

import kd.bos.base.AbstractBasePlugIn;
import kd.bos.form.control.events.ItemClickEvent;
import kd.bos.servicehelper.user.UserServiceHelper;

public class GetGoodInfo extends AbstractBasePlugIn {
	
	@Override
    public void afterCreateNewData(EventObject e) {
		Long currentUserId = UserServiceHelper.getCurrentUserId();
		long mainOrgid = UserServiceHelper.getUserMainOrgId(currentUserId);
		this.getModel().setValue("ozwe_orgfield", mainOrgid);
	}
	
	@Override
    //注册监听器并添加响应控件
    public void registerListener(EventObject e) {
        super.registerListener(e);
        this.addItemClickListeners("tbmain");
        this.addItemClickListeners("ozwe_getgoodinfo");
    }
	
	@Override
    //点击后执行方法体
    public void itemClick(ItemClickEvent e) {
    	super.itemClick(e);
        //获取点击控件的字段
        String itemKey = e.getItemKey();
        if (itemKey.equals("ozwe_getgoodinfo")) {
        	String name = "";
        	String category = "";
        	String introduction = "";
        	String size = "";
        	String company = "";
        	String img = "";
        	try {
        	//使用接口获取信息
        	String barcode = this.getModel().getValue("number").toString();
        	String barcodeJson = BarcodeAPI.getBarcodeJson(barcode, "1015196", "3a4fc08e4f7d42238eb3d702e67a80b0");
        	JSONObject jsonObject=new JSONObject(barcodeJson);
        	JSONObject result = jsonObject.getJSONObject("showapi_res_body");

        	name = result.getString("goodsName");
        	category = result.getString("goodsType");
        	//检测分类的另一个参数
        	String gpcType = result.getString("gpcType");
        	introduction = result.getString("description");
        	size = result.getString("spec");
        	company = result.getString("manuName");
        	img = result.getString("img");
        	
        	String[] s = category.split(">>");
        	category = s[s.length-1];
        	
        	if (category.contains("纸")) {
        		category = "纸制品";
        	} else if (category.contains("饮") || 
        			category.contains("水") || 
        			category.contains("茶")) {
        		
        		category = "饮品";
        		
        	} else if (category.contains("吃") ||
        			gpcType.contains("食") ||
        			category.contains("饼") ||
        			category.contains("甜点") ||
        			category.contains("薯") ||
        			category.contains("肉") ||
        			category.contains("面") ||
        			category.contains("蛋") ||
        			category.contains("糕") ||
        			category.contains("零食")) {
        		
        		category = "食品";
        		
        	} else if (gpcType.contains("纸")) {
        		category = "纸制品";
        	} else if (gpcType.contains("饮") || 
        			gpcType.contains("水") || 
        			gpcType.contains("茶")) {
        		
        		category = "饮品";
        		
        	} else if (gpcType.contains("吃") ||
        			gpcType.contains("食") ||
        			gpcType.contains("饼") ||
        			gpcType.contains("甜点") ||
        			gpcType.contains("薯") ||
        			gpcType.contains("肉") ||
        			gpcType.contains("面") ||
        			gpcType.contains("蛋") ||
        			gpcType.contains("糕") ||
        			gpcType.contains("零食")) {
        		
        		category = "食品";
        		
        	}
        	else {
        		category = "其他";
        	}
        	
        	//填写到表单中
        	this.getModel().setValue("name", name);
        	this.getModel().setValue("ozwe_goodcategory", category);
        	this.getModel().setValue("ozwe_introduction", introduction);
        	this.getModel().setValue("ozwe_goodpicture", img);
        	
        	this.getModel().setValue("ozwe_size", size , 0);
        	this.getModel().setValue("ozwe_company", company , 0);
        	
        } catch (Exception eee) {
        	this.getView().showMessage("条形码不正确或货物未找到！");
        	return;
        	}
        }
    }

}
