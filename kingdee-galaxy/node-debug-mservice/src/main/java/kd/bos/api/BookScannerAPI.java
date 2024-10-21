package kd.bos.api;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import kd.bos.bill.IBillWebApiPlugin;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.entity.api.ApiResult;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.operation.SaveServiceHelper;
import kd.bos.tools.BookAPI;

public class BookScannerAPI implements IBillWebApiPlugin{
	
	@Override
	public ApiResult doCustomService(Map<String, Object> params) {
		//设置apiResult基本属性
		ApiResult apiResult = new ApiResult();
		apiResult.setSuccess(true);
		apiResult.setErrorCode("success");
		apiResult.setMessage("对接成功！");
		apiResult.setData(params);
		//获取硬件传输过来的json对象中的isbn码
		String s = params.get("request").toString();
		StringBuffer sb = new StringBuffer();
		for (char c : s.toCharArray()) {
			if ((c>='0') && (c<='9')) {
				sb.append(c);
			}
		}
		String isbn = sb.toString();
		//根据isbn码和第三方接口获取书籍信息
		String bookName = "";
    	String bookAuthor = "";
    	String bookIntroduction = "";
    	String bookPress = "";
    	String bookImg = "";
    	try {
    	String bookJson = BookAPI.getIsbnBookJson(isbn, "1015196", "3a4fc08e4f7d42238eb3d702e67a80b0");
    	JSONObject jsonObject=new JSONObject(bookJson);
    	JSONObject jsonObject2 = jsonObject.getJSONObject("showapi_res_body");
    	JSONArray jsonArray = jsonObject2.getJSONArray("datas");
    	for (Object object : jsonArray) {
    		JSONObject jo = (JSONObject)object;
    		bookName = jo.getString("title");
    		bookAuthor = jo.getString("author");
    		bookIntroduction = jo.getString("gist");
    		bookPress = jo.getString("publisher");
    		bookImg = jo.getString("img");
    	}
    	//将书籍信息加入到书籍大全中
    	DynamicObject dynamicObject = BusinessDataServiceHelper.newDynamicObject("ozwe_book");
    	dynamicObject.set("number", isbn);
    	dynamicObject.set("name", bookName);
    	dynamicObject.set("ozwe_textfield", bookAuthor);
    	dynamicObject.set("ozwe_introduction", bookIntroduction);
    	dynamicObject.set("ozwe_publisher", bookPress);
    	dynamicObject.set("ozwe_bookpicture", bookImg);
    	dynamicObject.set("status", "A");
    	dynamicObject.set("ozwe_enable", "是");
    	dynamicObject.set("ozwe_iscontinue", "续借");
    	dynamicObject.set("ozwe_isreturn", "归还");
    	SaveServiceHelper.saveOperate("ozwe_book", new DynamicObject[] {dynamicObject}, null);
    	
    } catch (Exception eee) {
    	//若未找到书籍信息则这样添加
    	DynamicObject dynamicObject = BusinessDataServiceHelper.newDynamicObject("ozwe_book");
    	dynamicObject.set("number", "0");
    	dynamicObject.set("name", "未找到书籍");
    	dynamicObject.set("ozwe_textfield", "未找到书籍");
    	dynamicObject.set("ozwe_publisher", "未找到书籍");
    	dynamicObject.set("status", "A");
    	dynamicObject.set("ozwe_enable", "是");
    	dynamicObject.set("ozwe_iscontinue", "续借");
    	dynamicObject.set("ozwe_isreturn", "归还");
    	SaveServiceHelper.saveOperate("ozwe_book", new DynamicObject[] {dynamicObject}, null);
    }
		
		return apiResult;
		
	}

}
