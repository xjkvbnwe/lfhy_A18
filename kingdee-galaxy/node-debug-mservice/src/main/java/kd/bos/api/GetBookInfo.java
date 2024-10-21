package kd.bos.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kd.bos.bill.IBillWebApiPlugin;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.entity.api.ApiResult;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;

public class GetBookInfo implements IBillWebApiPlugin{
	
	@Override
	public ApiResult doCustomService(Map<String, Object> params) {
		//设置apiResult基本属性
		ApiResult apiResult = new ApiResult();
		apiResult.setSuccess(true);
		apiResult.setErrorCode("success");
		apiResult.setMessage("对接成功！");
		
		if (params.get("isbn").equals("all")) {
			
			DynamicObject dObject[] = BusinessDataServiceHelper.load("ozwe_book",
					"number,"
					+ "name,"
					+ "status,"
					+ "ozwe_textfield,"
					+ "ozwe_borrowamount,"
					+ "ozwe_bookpicture,"
					+ "ozwe_category,"
					+ "ozwe_borrownumber,"
					+ "ozwe_bookinfo,"
					+ "ozwe_borrowstatus",
					new QFilter[]{
							new QFilter("status",QCP.equals , "C"),
							});
			
			List<Object> bookList = new ArrayList<>();
			for (DynamicObject dy : dObject) {
				Map<String , Object> map = new HashMap<>();
				DynamicObjectCollection rows = dy.getDynamicObjectCollection("ozwe_bookinfo");
				int i = 0;
		    	for (DynamicObject dynamicObject : rows) {
		    		if (dynamicObject.getString("ozwe_borrowstatus").equals("可借阅")) {
		    			break;
		    		}
		    		i++;
		    	}
		    	if (i == rows.size()) {
		    		map.put("isBorrow", 1);
		    	}
		    	else {
		    		map.put("isBorrow", 0);
		    	}
				
				map.put("picture", dy.getString("ozwe_bookpicture"));
				map.put("name", dy.getString("name"));
				map.put("author", dy.getString("ozwe_textfield"));
				map.put("isbn", dy.getString("number"));
				map.put("category", dy.getString("ozwe_category"));
				map.put("amount", dy.getString("ozwe_borrownumber"));
				bookList.add(map);
			}
			Map<String , Object> map = new HashMap<>();
			map.put("info", bookList);
			apiResult.setData(map);
			return apiResult;
		} else {
		
		DynamicObject dy = BusinessDataServiceHelper.loadSingle("ozwe_book",
				"number,"
				+ "name,"
				+ "ozwe_textfield,"
				+ "ozwe_borrowamount,"
				+ "ozwe_introduction,"
				+ "ozwe_bookpicture,"
				+ "ozwe_category,"
				+ "ozwe_bookinfo,"
				+ "ozwe_borrowstatus",
				new QFilter[]{
						new QFilter("number",QCP.equals, params.get("isbn")),
						});
		
		
		Map<String , Object> map = new HashMap<>();
		if (dy != null) {
			DynamicObjectCollection rows = dy.getDynamicObjectCollection("ozwe_bookinfo");
			int i = 0;
	    	for (DynamicObject dynamicObject : rows) {
	    		if (dynamicObject.getString("ozwe_borrowstatus").equals("可借阅")) {
	    			break;
	    		}
	    		i++;
	    	}
	    	if (i == rows.size()) {
	    		map.put("isBorrow", 1);
	    	}
	    	else {
	    		map.put("isBorrow", 0);
	    	}
			map.put("pkId", dy.getPkValue().toString());
			map.put("isbn", dy.getString("number"));
			map.put("name", dy.getString("name"));
			map.put("author", dy.getString("ozwe_textfield"));
			map.put("amount", dy.getString("ozwe_borrowamount"));
			map.put("introduction", dy.getString("ozwe_introduction"));
			map.put("category", dy.getString("ozwe_category"));
			map.put("picture", dy.getString("ozwe_bookpicture"));
		}
		else {
			map.put("pkId", "未获取到书籍");
		}
		apiResult.setData(map);
		
		return apiResult;
		}
	}

}
