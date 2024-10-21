package kd.bos.api;

import java.util.HashMap;
import java.util.Map;

import kd.bos.bill.IBillWebApiPlugin;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.entity.api.ApiResult;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;

public class GetNotReturnBook implements IBillWebApiPlugin{
	
	@Override
	public ApiResult doCustomService(Map<String, Object> params) {
		//设置apiResult基本属性
		ApiResult apiResult = new ApiResult();
		apiResult.setSuccess(true);
		apiResult.setErrorCode("success");
		apiResult.setMessage("对接成功！");
		
		DynamicObject dObject = BusinessDataServiceHelper.loadSingle("ozwe_usermassage", 
	      		  "ozwe_amountfield,"
	      		+ "name,"
	      		+ "ozwe_deposit,"
	      		+ "ozwe_credit,"
	      		+ "ozwe_canborrow,"
	      		+ "ozwe_telephone,"
	      		+ "ozwe_userpicture,"
	      		+ "ozwe_face,"
	      		+ "ozwe_userid",
	      		new QFilter[] {new QFilter("ozwe_telephone", QCP.equals, params.get("phone"))});
		
		DynamicObject dy = BusinessDataServiceHelper.loadSingle("ozwe_book",
				"number,"
				+ "name,"
				+ "ozwe_textfield,"
				+ "ozwe_borrowamount",
				new QFilter[]{
						new QFilter("number",QCP.equals, params.get("isbn")),
						});
		
		String bookName = dy.getString("name");
		String bookAuthor = dy.getString("ozwe_textfield");
		
		DynamicObject dyBorrow = BusinessDataServiceHelper.loadSingle("ozwe_ledbookbill",
				"billno,"
				+ "ozwe_bookname,"
				+ "ozwe_bookauthor,"
				+ "ozwe_return,"
				+ "ozwe_telephone,"
				+ "ozwe_userid",
				new QFilter[]{
						new QFilter("ozwe_bookname",QCP.equals,bookName),
						new QFilter("ozwe_bookauthor",QCP.equals,bookAuthor),
						new QFilter("ozwe_return",QCP.equals,"否"),
						new QFilter("ozwe_userid",QCP.equals,dObject.getString("ozwe_userid"))
						});
		
		Map<String , Object> map = new HashMap<>();
		if (dyBorrow != null) {
			map.put("pkId", dyBorrow.getPkValue().toString());
		}
		else {
			map.put("pkId", "无未归还书籍");
		}
		apiResult.setData(map);
		
		return apiResult;
		
	}

}
