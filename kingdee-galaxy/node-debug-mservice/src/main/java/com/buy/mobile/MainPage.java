package com.buy.mobile;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.buy.goodinfo.BarcodeAPI;

import kd.bos.base.MobileBaseShowParameter;
import kd.bos.bill.MobileBillShowParameter;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.form.ClientMethod;
import kd.bos.form.CloseCallBack;
import kd.bos.form.ShowType;
import kd.bos.form.control.Button;
import kd.bos.form.events.CustomEventArgs;
import kd.bos.form.plugin.AbstractMobFormPlugin;
import kd.bos.mvc.form.MobileFormView;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.user.UserServiceHelper;

public class MainPage extends AbstractMobFormPlugin{
	@Override
	public void registerListener(EventObject e) {
		super.registerListener(e);
		addClickListeners("ozwe_vectorap1");
		addClickListeners("ozwe_ozwe_vectorap5");
		
	}
	
	@Override
	public void click(EventObject evt) {
		super.click(evt);
		Button button = (Button) evt.getSource();
		String key = button.getKey();
		if (key.equalsIgnoreCase("ozwe_vectorap1")) {
			Map<String , String> map = new HashMap<>();
			map.put("type", "qr");
			MobileFormView view = (MobileFormView)this.getView();
			view.callClientAppMethod(ClientMethod.ScanQRCode, map);
		}
		else if (key.equalsIgnoreCase("ozwe_ozwe_vectorap5")) {
			MobileBaseShowParameter billShowParameter = new MobileBaseShowParameter();
        	billShowParameter.setFormId("ozwe_goodslist_mob");
			billShowParameter.setCloseCallBack(new CloseCallBack(this, "ozwe_goodslist_mob"));
			billShowParameter.getOpenStyle().setShowType(ShowType.Floating);
			getView().showForm(billShowParameter);
		}
	}
	@Override
	public void customEvent(CustomEventArgs e) {
		String key = e.getKey();
		if (key.equalsIgnoreCase("callAppMethod")) {
			if (e.getEventName().equalsIgnoreCase("scanQRCode")) {
				if (e.getEventArgs().length() == 16) {
					DynamicObject contract = BusinessDataServiceHelper.loadSingle("ozwe_apply",
							"billno,"
							+ "ozwe_org",
							new QFilter[]{new QFilter("billno",QCP.equals, e.getEventArgs())});
					Long currentUserId = UserServiceHelper.getCurrentUserId();
					Long mainOrgId = UserServiceHelper.getUserMainOrgId(currentUserId);
					DynamicObject dObject = contract.getDynamicObject("ozwe_org");
					/*if ((Long)dObject.getPkValue() != mainOrgId) {
						getView().showMessage("该订单不属于该场站，请仔细查询后再进行操作！");
						return;
					}*/
					MobileBillShowParameter billShowParameter = new MobileBillShowParameter();
					billShowParameter.setPkId(contract.getPkValue());
					billShowParameter.setFormId("ozwe_apply_mob");
					billShowParameter.getOpenStyle().setShowType(ShowType.Floating);
					getView().showForm(billShowParameter);
				}
				else if (e.getEventArgs().length() == 13) {
					String name = "";
		        	String category = "";
		        	String introduction = "";
		        	String size = "";
		        	String company = "";
		        	String img = "";
		        	try {
		        	//使用接口获取信息
		        	String barcode = e.getEventArgs();
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
		        	MobileBaseShowParameter billShowParameter = new MobileBaseShowParameter();
		        	billShowParameter.setFormId("ozwe_goodslist_mob");
					billShowParameter.setCustomParam("category", category);
					billShowParameter.setCustomParam("name", name);
					billShowParameter.setCustomParam("number", e.getEventArgs());
					billShowParameter.setCustomParam("introduction",introduction);
					billShowParameter.setCustomParam("img", img);
					billShowParameter.setCustomParam("size", size);
					billShowParameter.setCustomParam("company",company);
					billShowParameter.setCloseCallBack(new CloseCallBack(this, "ozwe_goodslist_mob"));
					billShowParameter.getOpenStyle().setShowType(ShowType.Floating);
					getView().showForm(billShowParameter);
		        	
		        } catch (Exception eee) {
		        	getView().showMessage("条形码不正确或货物未找到！");
		        	return;
		        }
					
			  } else {
				  getView().showMessage("请扫描正确的条码或二维码！");
			  }
			}
		}
	}
}
