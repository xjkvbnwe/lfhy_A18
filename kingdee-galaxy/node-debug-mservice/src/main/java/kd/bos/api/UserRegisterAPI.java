package kd.bos.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import kd.bos.bill.IBillWebApiPlugin;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.entity.api.ApiResult;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.operation.SaveServiceHelper;

public class UserRegisterAPI implements IBillWebApiPlugin{
	
	@Override
	public ApiResult doCustomService(Map<String, Object> params) {
		//设置apiResult基本属性
		ApiResult apiResult = new ApiResult();
		apiResult.setSuccess(true);
		apiResult.setErrorCode("success");
		apiResult.setMessage("对接成功！");
		//根据微信小程序传入的手机号码查找对应的用户信息
		DynamicObject dy = BusinessDataServiceHelper.loadSingle("ozwe_usermassage"/*基础资料的标识*/, 
	      		  "ozwe_amountfield,"
	      		+ "name,"
	      		+ "ozwe_deposit,"
	      		+ "ozwe_credit,"
	      		+ "ozwe_canborrow,"
	      		+ "ozwe_telephone,"
	      		+ "ozwe_userpicture,"
	      		+ "ozwe_face"/*要获取的基础资料的内容，用,隔开*/,
	      		new QFilter[] {new QFilter("ozwe_telephone", QCP.equals, params.get("phone"))});
		
		if (dy == null) {
			DynamicObject newUser = BusinessDataServiceHelper.newDynamicObject("bos_user");
			newUser.set("name", params.get("name").toString());
			newUser.set("simplepinyin", params.get("simplepinyin").toString());
			newUser.set("fullpinyin", params.get("fullpinyin").toString());
			StringBuffer sb1 = new StringBuffer();
			for (int i1 = 1 ; i1<=6; i1++) {
				int ascii = 48+(int)(Math.random()*9);
				char c = (char) ascii;
				sb1.append(c);
			}
			newUser.set("number", "ID-"+sb1.toString());
			newUser.set("usertype", "1451728497704697856");
			newUser.set("gender", params.get("gender").toString());
			newUser.set("country", 1000001);
			newUser.set("phone", params.get("phone").toString());
			newUser.set("status", "C");
			newUser.set("enable", '1');
			
			DynamicObjectCollection rows = newUser.getDynamicObjectCollection("entryentity");
			DynamicObject dObject = rows.addNew();
			
			DynamicObject contract1 = BusinessDataServiceHelper.loadSingle("bos_adminorg",
					"number,"
					+ "name,"
					+ "parent,"
					+ "orgpattern,"
					+ "fcomment,"
					+ "structure",
					new QFilter[]{new QFilter("name",QCP.equals,"cosmic-simple")});
			
			dObject.set("dpt", contract1);
			dObject.set("position", "师生");
			rows.set(0, dObject);
			
			SaveServiceHelper.saveOperate("bos_user", new DynamicObject[] {newUser}, null);
			
			//获取新增的用户信息
        	DynamicObject[] contract = BusinessDataServiceHelper.load("bos_user",
					"number,"
					+ "name,"
					+ "phone,"
					+ "gender,"
					+ "modifytime,"
					+ "picturefield,"
					+ "status",
					new QFilter[]{new QFilter("number",QCP.is_notnull,null)});
        	long nowDate = new Date().getTime();
        	for (DynamicObject dy1 : contract) {
        		//判断新增时间
        		long startDateTime = dy1.getDate("modifytime").getTime();
                int diffSeconds = (int) ((nowDate - startDateTime) / 1000);
        		if (diffSeconds <=1) {
        			//将信息添加到用户信息
        			DynamicObject dynamicObject = BusinessDataServiceHelper.newDynamicObject("ozwe_usermassage");
        			dynamicObject.set("number", dy1.getString("number"));
        			dynamicObject.set("ozwe_userpicture", dy1.getString("picturefield"));
        			dynamicObject.set("ozwe_combofield", dy1.getString("gender"));
        			dynamicObject.set("name", dy1.getString("name"));
        			dynamicObject.set("ozwe_telephone", dy1.getString("phone"));
        			dynamicObject.set("enable", 1);
        			dynamicObject.set("status", dy1.getString("status"));
        			
        			dynamicObject.set("ozwe_amountfield", 0);
        			dynamicObject.set("ozwe_canborrow", 20);
        			dynamicObject.set("ozwe_deposit", 0);
        			dynamicObject.set("ozwe_credit", 100);
        			dynamicObject.set("ozwe_userid", dy1.getPkValue().toString());
        			SaveServiceHelper.saveOperate("ozwe_usermassage", new DynamicObject[] {dynamicObject}, null);
        			
        			DynamicObject dy11 = BusinessDataServiceHelper.loadSingle("ozwe_usermassage"/*基础资料的标识*/, 
      		      		  "ozwe_amountfield,"
      		      		+ "name,"
      		      		+ "ozwe_deposit,"
      		      		+ "ozwe_credit,"
      		      		+ "ozwe_canborrow,"
      		      		+ "ozwe_telephone,"
      		      		+ "ozwe_point1,"
      		      		+ "ozwe_point2,"
      		      		+ "ozwe_point3,"
      		      		+ "ozwe_point4,"
      		      		+ "ozwe_point5,"
      		      		+ "ozwe_userpicture,"
      		      		+ "ozwe_face"/*要获取的基础资料的内容，用,隔开*/,
      		      		new QFilter[] {new QFilter("ozwe_telephone", QCP.equals, params.get("phone"))});
      			int jyAll = dy11.getInt("ozwe_point1");
      			int kpAll = dy11.getInt("ozwe_point2");
      			int zzAll = dy11.getInt("ozwe_point3");
      			int wxAll = dy11.getInt("ozwe_point4");
      			int zlAll = dy11.getInt("ozwe_point5");
      			JSONArray jsonArray = new JSONArray(params.get("selectLables").toString());
      			for (Object object : jsonArray) {
      				JSONObject jo = (JSONObject)object;
      				int jy = jo.getInt("jy");
      				int kp = jo.getInt("kp");
      				int zz = jo.getInt("zz");
      				int wx = jo.getInt("wx");
      				int zl = jo.getInt("zl");
      				jyAll+=jy+50;
      				kpAll+=kp+50;
      				zzAll+=zz+50;
      				wxAll+=wx+50;
      				zlAll+=zl+50;
      			}
      			dy11.set("ozwe_point1", jyAll);
      			dy11.set("ozwe_point2", kpAll);
      			dy11.set("ozwe_point3", zzAll);
      			dy11.set("ozwe_point4", wxAll);
      			dy11.set("ozwe_point5", zlAll);
      			SaveServiceHelper.saveOperate("ozwe_usermassage", new DynamicObject[] {dy11}, null);
      			Map<String , Object> map = new HashMap<>();
      			map.put("data", dy11);
      			apiResult.setData(map);
      			
      			return apiResult;
        			
        		}
        	}
			
		} else {
			DynamicObject dy1 = BusinessDataServiceHelper.loadSingle("ozwe_usermassage"/*基础资料的标识*/, 
		      		  "ozwe_amountfield,"
		      		+ "name,"
		      		+ "ozwe_deposit,"
		      		+ "ozwe_credit,"
		      		+ "ozwe_canborrow,"
		      		+ "ozwe_telephone,"
		      		+ "ozwe_point1,"
		      		+ "ozwe_point2,"
		      		+ "ozwe_point3,"
		      		+ "ozwe_point4,"
		      		+ "ozwe_point5,"
		      		+ "ozwe_userpicture,"
		      		+ "ozwe_face"/*要获取的基础资料的内容，用,隔开*/,
		      		new QFilter[] {new QFilter("ozwe_telephone", QCP.equals, params.get("phone"))});
			int jyAll = dy1.getInt("ozwe_point1");
			int kpAll = dy1.getInt("ozwe_point2");
			int zzAll = dy1.getInt("ozwe_point3");
			int wxAll = dy1.getInt("ozwe_point4");
			int zlAll = dy1.getInt("ozwe_point5");
			JSONArray jsonArray = new JSONArray(params.get("selectLables").toString());
			for (Object object : jsonArray) {
				JSONObject jo = (JSONObject)object;
				int jy = jo.getInt("jy");
				int kp = jo.getInt("kp");
				int zz = jo.getInt("zz");
				int wx = jo.getInt("wx");
				int zl = jo.getInt("zl");
				jyAll+=jy+50;
				kpAll+=kp+50;
				zzAll+=zz+50;
				wxAll+=wx+50;
				zlAll+=zl+50;
			}
			dy1.set("ozwe_point1", jyAll);
			dy1.set("ozwe_point2", kpAll);
			dy1.set("ozwe_point3", zzAll);
			dy1.set("ozwe_point4", wxAll);
			dy1.set("ozwe_point5", zlAll);
			SaveServiceHelper.saveOperate("ozwe_usermassage", new DynamicObject[] {dy1}, null);
			Map<String , Object> map = new HashMap<>();
			map.put("data", dy1);
			apiResult.setData(map);
			
			return apiResult;
		}
		
		//将信息添加至map内返回给微信小程序
		Map<String , Object> map = new HashMap<String , Object>();
		
		apiResult.setData(map);
		
		return apiResult;
		
	}

}
