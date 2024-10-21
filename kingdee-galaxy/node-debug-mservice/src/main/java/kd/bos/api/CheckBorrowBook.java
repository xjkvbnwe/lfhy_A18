package kd.bos.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kd.bos.bill.IBillWebApiPlugin;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.entity.api.ApiResult;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;

public class CheckBorrowBook implements IBillWebApiPlugin{
	
	@Override
	public ApiResult doCustomService(Map<String, Object> params) {
		//设置apiResult基本属性
		ApiResult apiResult = new ApiResult();
		apiResult.setSuccess(true);
		apiResult.setErrorCode("success");
		apiResult.setMessage("对接成功！");
		
		DynamicObject userInfo = BusinessDataServiceHelper.loadSingle("ozwe_usermassage"/*基础资料的标识*/, 
	      		  "ozwe_amountfield,"
	      		+ "name,"
	      		+ "ozwe_deposit,"
	      		+ "ozwe_credit,"
	      		+ "ozwe_canborrow,"
	      		+ "ozwe_telephone,"
	      		+ "ozwe_userpicture"/*要获取的基础资料的内容，用,隔开*/,
	      		new QFilter[] {new QFilter("ozwe_telephone", QCP.equals, params.get("phone"))});
		
		String userName = userInfo.getString("name");
		
		List<Object> bookList = new ArrayList<>();
		
		if (params.get("type").equals("borrow")) {
			
		DynamicObject[] dObject = BusinessDataServiceHelper.load("ozwe_ledbookbill", 
				"creator.name,"
				+ "billno,"
				+ "ozwe_return,"
				+ "ozwe_bookname,"
				+ "ozwe_datefield,"
				+ "ozwe_bookpicture", 
				
				new QFilter[] {new QFilter("creator.name", QCP.equals, userName)});
		
		for (DynamicObject dy : dObject) {
			Map<String , Object> map = new HashMap<>();
			map.put("isreturn", dy.getString("ozwe_return"));
			map.put("bookName", dy.getString("ozwe_bookname"));
			map.put("returnDate", dy.getString("ozwe_datefield"));
			map.put("bookPicture", dy.getString("ozwe_bookpicture"));
			bookList.add(map);
		}
		
		Map<String , Object> map = new HashMap<>();
		map.put("data", bookList);
		apiResult.setData(map);
		
		return apiResult;
		} else {
			DynamicObject[] dObject = BusinessDataServiceHelper.load("ozwe_overduelog", 
					"ozwe_username,"
					+ "billno,"
					+ "billstatus,"
					+ "ozwe_book,"
					+ "ozwe_bookauthor,"
					+ "ozwe_oughtreturn,"
					+ "ozwe_returnstatus",
					new QFilter[] {new QFilter("ozwe_username", QCP.equals, userName)});
			for (DynamicObject dy : dObject) {
				Map<String , Object> map = new HashMap<>();
				map.put("isreturn", dy.getString("ozwe_returnstatus"));
				map.put("bookName", dy.getString("ozwe_book"));
				map.put("returnDate", dy.getString("ozwe_oughtreturn"));
				bookList.add(map);
			}
			
			Map<String , Object> map = new HashMap<>();
			map.put("data", bookList);
			apiResult.setData(map);
			return apiResult;
		}
	}

}
