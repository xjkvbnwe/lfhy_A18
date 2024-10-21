package kd.bos.api;

import java.util.HashMap;
import java.util.Map;

import kd.bos.bill.IBillWebApiPlugin;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.entity.api.ApiResult;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;

public class UserPkIdAPI implements IBillWebApiPlugin{
	
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
		
		String pkId = dy.getPkValue().toString();
		Double money = Double.parseDouble(dy.getString("ozwe_amountfield"));
		String face = dy.getString("ozwe_face");
		int canBorrow = Integer.parseInt(dy.getString("ozwe_canBorrow"));
		//将信息添加至map内返回给微信小程序
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("pkId", pkId);
		map.put("money", money);
		map.put("canborrow", canBorrow);
		map.put("face", face);
		
		apiResult.setData(map);
		
		return apiResult;
		
	}

}
