package kd.bos.api;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import kd.bos.bill.IBillWebApiPlugin;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.entity.api.ApiResult;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.operation.SaveServiceHelper;

public class UserInfoAPI implements IBillWebApiPlugin{
	
	@Override
	public ApiResult doCustomService(Map<String, Object> params) {
		//设置apiResult基本属性
		ApiResult apiResult = new ApiResult();
		apiResult.setSuccess(true);
		apiResult.setErrorCode("success");
		apiResult.setMessage("对接成功！");
		
		DynamicObject dy = BusinessDataServiceHelper.loadSingle("ozwe_usermassage"/*基础资料的标识*/, 
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
		int jyAll = dy.getInt("ozwe_point1");
		int kpAll = dy.getInt("ozwe_point2");
		int zzAll = dy.getInt("ozwe_point3");
		int wxAll = dy.getInt("ozwe_point4");
		int zlAll = dy.getInt("ozwe_point5");
		if (params.get("selectLables").toString().equals("getInfo")) {
			Map<String , Object> map = new HashMap<>();
			int amountAll = jyAll+kpAll+zzAll+wxAll+zlAll;
			map.put("jyPercent", (double)jyAll/amountAll);
			map.put("kpPercent", (double)kpAll/amountAll);
			map.put("zzPercent", (double)zzAll/amountAll);
			map.put("wxPercent", (double)wxAll/amountAll);
			map.put("zlPercent", (double)zlAll/amountAll);
			map.put("faceBase64", dy.getString("ozwe_face"));
			apiResult.setData(map);
			
			return apiResult;
		} else if (params.get("selectLables").toString().equals("updateFace")) {
			String base64 = params.get("base64").toString();
			dy.set("ozwe_face", base64);
			SaveServiceHelper.saveOperate("ozwe_usermassage", new DynamicObject[] {dy}, null);
			Map<String , Object> map = new HashMap<>();
			map.put("data", dy.getString("ozwe_face"));
			apiResult.setData(map);
			return apiResult;
		} else {
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
		dy.set("ozwe_point1", jyAll);
		dy.set("ozwe_point2", kpAll);
		dy.set("ozwe_point3", zzAll);
		dy.set("ozwe_point4", wxAll);
		dy.set("ozwe_point5", zlAll);
		SaveServiceHelper.saveOperate("ozwe_usermassage", new DynamicObject[] {dy}, null);
		Map<String , Object> map = new HashMap<>();
		map.put("data", dy);
		apiResult.setData(map);
		
		return apiResult;
	     }
	}

}
