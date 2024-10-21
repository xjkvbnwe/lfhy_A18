package kd.bos.api;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import kd.bos.bill.IBillWebApiPlugin;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.entity.api.ApiResult;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.operation.SaveServiceHelper;
import kd.bos.tools.CompareFaceAPI;
import kd.bos.tools.GetBase64;
import kd.bos.tools.SendMessage;

public class FaceBorrowReturn implements IBillWebApiPlugin{
	
	@Override
	public ApiResult doCustomService(Map<String, Object> params) {
		//设置apiResult基本属性
		ApiResult apiResult = new ApiResult();
		apiResult.setSuccess(true);
		apiResult.setErrorCode("success");
		apiResult.setMessage("对接成功！");
		
		JSONObject jo;
		try {
			 jo = new JSONObject(params);
		}
		catch (Exception ee) {
			jo = new JSONObject(params.get("request").toString());
		}
		
		DynamicObject[] bookList = BusinessDataServiceHelper.load("ozwe_book",
				"number,"
	        	+ "name,"
	        	+ "ozwe_bookpicture,"
	        	+ "ozwe_textfield,"
	        	+ "ozwe_iscontinue,"
	        	+ "ozwe_isreturn,"
	        	+ "ozwe_bookinfo,"
	        	+ "ozwe_borrowstatus,"
	        	+ "ozwe_bookno,"
	        	+ "ozwe_borrownumber,"
	        	+ "ozwe_borrowamount,"
	        	+ "ozwe_category",
				new QFilter[]{
						new QFilter("number" ,QCP.is_notnull, null),
						});
		
		String isbn = "";
		for (DynamicObject book : bookList) {
			if (book.getString("number").contains(jo.getString("isbn"))) {
				isbn = book.getString("number");
				break;
			}
		}
		
		DynamicObject contractBook = BusinessDataServiceHelper.loadSingle("ozwe_book",
				"number,"
	        	+ "name,"
	        	+ "ozwe_bookpicture,"
	        	+ "ozwe_textfield,"
	        	+ "ozwe_iscontinue,"
	        	+ "ozwe_isreturn,"
	        	+ "ozwe_bookinfo,"
	        	+ "ozwe_borrowstatus,"
	        	+ "ozwe_bookno,"
	        	+ "ozwe_borrownumber,"
	        	+ "ozwe_borrowamount,"
	        	+ "ozwe_category",
				new QFilter[]{
						new QFilter("number" ,QCP.equals, isbn),
						});
		
		if (contractBook == null) {
			apiResult.setData("没有找到书籍");
			return apiResult;
		}
		
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
	      		new QFilter[] {new QFilter("ozwe_telephone", QCP.equals, jo.getString("phone"))});
        	
        	//获取书籍信息
        	DynamicObject dObjectBook = BusinessDataServiceHelper.loadSingle("ozwe_book",
        			"number,"
        			+ "name,"
        			+ "ozwe_bookpicture,"
        			+ "ozwe_textfield,"
        			+ "ozwe_iscontinue,"
        			+ "ozwe_isreturn,"
        			+ "ozwe_bookinfo,"
        			+ "ozwe_borrowstatus,"
        			+ "ozwe_bookno,"
        			+ "ozwe_borrownumber,"
        			+ "ozwe_borrowamount,"
        			+ "ozwe_category",
					new QFilter[]{
							new QFilter("status",QCP.equals , "C"),
							new QFilter("number",QCP.equals , isbn)
							});
        	if (dObject != null) {
        		
        		DynamicObject dObjectBorrow = BusinessDataServiceHelper.loadSingle("ozwe_ledbookbill",
        				"billno,"
        				+ "ozwe_bookname,"
        				+ "ozwe_bookauthor,"
        				+ "ozwe_return,"
        				+ "ozwe_isreturn,"
        				+ "ozwe_realreturn,"
        				+ "ozwe_continueday,"
        				+ "ozwe_iscontinue,"
        				+ "ozwe_days,"
        				+ "ozwe_datefield,"
        				+ "ozwe_bookno,"
        				+ "ozwe_userid,"
        				+ "ozwe_borrowamount",
        				new QFilter[] {
        					new QFilter("ozwe_bookname", QCP.equals, dObjectBook.getString("name")),
        					new QFilter("ozwe_return", QCP.equals, "否")
        				});
        		
        		if (dObjectBorrow != null) {
        			dObjectBorrow.set("ozwe_return", "是");
        			dObjectBorrow.set("ozwe_realreturn", new Date());
        			dObjectBorrow.set("ozwe_isreturn", "不可归还");
        			dObjectBorrow.set("ozwe_iscontinue", "不可续借");
        			
        			DynamicObjectCollection rows = contractBook.getDynamicObjectCollection("ozwe_bookinfo");
			    	int i = 0;
			    	for (DynamicObject dynamicObject : rows) {
			    		if (dynamicObject.getString("ozwe_bookno").equals(dObjectBorrow.getString("ozwe_bookno"))) {
			    			dynamicObject.set("ozwe_borrowstatus", "可借阅");
			    			rows.set(i, dynamicObject);
			    			break;
			    		}
			    		i++;
			    	}
			    	//如果用户有逾期记录，则更改逾期记录信息
					DynamicObject dynamicObject = BusinessDataServiceHelper.loadSingle("ozwe_overduelog",
							"billno,ozwe_returnstatus,ozwe_realdate",
							new QFilter[]{new QFilter("billno",QCP.equals,dObjectBorrow.getString("billno"))});
					if (dynamicObject != null) {
						dynamicObject.set("ozwe_realdate", new Date());
						dynamicObject.set("ozwe_returnstatus", "是");
						SaveServiceHelper.saveOperate("ozwe_overduelog", new DynamicObject[] {dynamicObject}, null);
					}
					SaveServiceHelper.saveOperate("ozwe_ledbookbill", new DynamicObject[] {dObjectBorrow}, null);
					SaveServiceHelper.saveOperate("ozwe_book", new DynamicObject[] {contractBook}, null);
					
					//获取预约记录
					QFilter qFilter2 = new QFilter("ozwe_appointmentbook", QCP.equals, dObjectBorrow.getString("ozwe_bookname"));
					QFilter qFilter3 = new QFilter("ozwe_bookauthor", QCP.equals, dObjectBorrow.getString("ozwe_bookauthor"));
					DynamicObject[] dObject1 = BusinessDataServiceHelper.load("ozwe_appointment", 
							"creator.name,"
							+ "billno,"
							+ "billstatus,"
							+ "ozwe_appointmentbook,"
							+ "ozwe_bookauthor,"
							+ "ozwe_username,"
							+ "createtime,"
							+ "ozwe_isbn,"
							+ "ozwe_bookno,"
							+ "ozwe_isborrow,"
							+ "ozwe_borrowstatus,"
							+ "ozwe_userid", new QFilter[] {qFilter2,qFilter3});
					
					DynamicObject dy = BusinessDataServiceHelper.loadSingle("ozwe_usermassage", 
				      		  "ozwe_amountfield,"
				      		+ "name,"
				      		+ "ozwe_deposit,"
				      		+ "ozwe_credit,"
				      		+ "ozwe_canborrow",
				      		(new QFilter("name", QCP.equals, dObject.getString("name"))).toArray());
					
					dy.set("ozwe_canborrow", Double.parseDouble(dy.getString("ozwe_canborrow")) +1);
					//增加一点信誉积分
					if (dy.getInt("ozwe_credit") < 100) {
						dy.set("ozwe_credit", dy.getInt("ozwe_credit")+1);
					}
					SaveServiceHelper.saveOperate("ozwe_usermassage", new DynamicObject[] {dy}, null);
					for (DynamicObject dynamicObject1 : dObject1) {
						  if (dynamicObject1.getString("ozwe_username").equals(dObject.getString("name"))) {
							if (dynamicObject1.getString("ozwe_isborrow").contains("是") && dynamicObject1.getString("ozwe_borrowstatus").contains("借阅中")) {
								dynamicObject1.set("ozwe_borrowstatus", "借阅完成");
								SaveServiceHelper.saveOperate("ozwe_appointment", new DynamicObject[] {dynamicObject1}, null);
								continue;
							}
						  }
						  //如果书籍有预约，则提醒预约用户有空闲书籍
						  if (dynamicObject1.getString("ozwe_isborrow").contains("否") && (dynamicObject1.getString("ozwe_bookno").contains("all") || dynamicObject1.getString("ozwe_bookno").contains(dynamicObject1.getString("ozwe_bookno")))) {
							  List<Long> receivers = new ArrayList<>();
							receivers.add(Long.parseLong(dynamicObject1.getString("ozwe_userid")));
							  SendMessage.sendMessage(
										"书籍有空闲！", 
										"您预约的书籍《"+dynamicObject1.getString("ozwe_appointmentbook")+"》刚刚有空闲，请抓紧时间借阅！", 
										"书籍有空闲", 
										"http://www.dream-y.top:8080/ierp/mobile.html#/form/bos_moblist?billFormId=ozwe_book&type=mobilelist",
										"http://www.dream-y.top:8080/ierp/index.html?FormId=bos_list&billFormId=ozwe_book&type=list",
										receivers);
						  }
						}
					List<Long> receivers = new ArrayList<>();
	    			receivers.add(Long.parseLong(dObject.getString("ozwe_userid")));
	    			  SendMessage.sendMessage(
	    						"书籍归还成功！", 
	    						"您成功归还书籍《"+dObjectBook.getString("name")+"》，期待您的下次借阅！", 
	    						"书籍归还成功", 
	    						"",
	    						"",
	    						receivers);
					apiResult.setData("111");
	    			return apiResult;
        		}
        		
        		int bookAmount = dObjectBook.getInt("ozwe_borrowamount");
        		int userAmount = dObject.getInt("ozwe_amountfield");
        		//判断用户借阅条件是否满足
        		/*if ((dObject.getInt("ozwe_canborrow") <= 0) || (userAmount < bookAmount)) {
        			if (userAmount < bookAmount) {
        				apiResult.setData("借阅金额不足，无法借阅");
        				return apiResult;
        			}
        			else {
        			apiResult.setData("可借阅书籍数量不足，无法借阅");
        			return apiResult;
        			}
        		}*/
        		//更改书籍借阅中
        		DynamicObjectCollection rows = contractBook.getDynamicObjectCollection("ozwe_bookinfo");
        		int i = 0;
        		String bookNo = null;
    	    	for (DynamicObject dynamicObject : rows) {
    	    		if (dynamicObject.getString("ozwe_borrowstatus").equals("可借阅")) {
    	    			bookNo = dynamicObject.getString("ozwe_bookno");
    	    			dynamicObject.set("ozwe_borrowstatus", "借阅中");
    	    			rows.set(i, dynamicObject);
    	    			break;
    	    		}
    	    		i++;
    	    	}
    	    	if (i == rows.size()) {
    	    		apiResult.setData("无可借阅书籍，无法借阅");
        			return apiResult;
    	    	}
    	    	SaveServiceHelper.saveOperate("ozwe_book", new DynamicObject[] {contractBook}, null);
    	    	//筛选预约记录信息
    	    	QFilter qFilter = new QFilter("ozwe_username", QCP.equals, dObject.getString("name"));
    			QFilter qFilter2 = new QFilter("ozwe_appointmentbook", QCP.equals, dObjectBook.getString("name"));
    			QFilter qFilter3 = new QFilter("ozwe_bookauthor", QCP.equals, dObjectBook.getString("ozwe_textfield"));
    			DynamicObject[] dObject1 = BusinessDataServiceHelper.load("ozwe_appointment", 
    					"creator.name,"
    					+ "billno,"
    					+ "billstatus,"
    					+ "ozwe_appointmentbook,"
    					+ "ozwe_bookauthor,"
    					+ "ozwe_username,"
    					+ "createtime,"
    					+ "ozwe_isbn,"
    					+ "ozwe_bookno,"
    					+ "ozwe_isborrow,"
    					+ "ozwe_borrowstatus,"
    					+ "ozwe_userid", new QFilter[] {qFilter,qFilter2,qFilter3});
    			//更改预约记录中的内容
    			for (DynamicObject dynamicObject : dObject1) {
    				if (dynamicObject.getString("ozwe_isborrow").contains("否")) {
    					dynamicObject.set("ozwe_isborrow", "是");
    					dynamicObject.set("ozwe_borrowstatus", "借阅中");
    					SaveServiceHelper.saveOperate("ozwe_appointment", new DynamicObject[] {dynamicObject}, null);
    					break;
    				}
    			}
    			//获取用户信息，并且根据借阅信息修改用户信息
    			DynamicObject dy = BusinessDataServiceHelper.loadSingle("ozwe_usermassage", 
    		      		  "ozwe_amountfield,"
    		      		+ "name,"
    		      		+ "ozwe_deposit,"
    		      		+ "ozwe_credit,"
    		      		+ "ozwe_canborrow"/*要获取的基础资料的内容，用,隔开*/,
    		      		(new QFilter("name", QCP.equals, dObject.getString("name"))).toArray());
    			
    		    	Double allAmount = Double.parseDouble(dy.getString("ozwe_amountfield"));
    		    dy.set("ozwe_amountfield", allAmount- Double.parseDouble(dObjectBook.getString("ozwe_borrowamount")));
    			dObjectBook.set("ozwe_borrownumber", Integer.parseInt(dObjectBook.getString("ozwe_borrownumber")) + 1);
    			SaveServiceHelper.saveOperate("ozwe_book", new DynamicObject[] {dObjectBook}, null);
    			SaveServiceHelper.saveOperate("ozwe_usermassage", new DynamicObject[] {dy}, null);
    			//将消费信息添加至消费记录
    			DynamicObject dynamicObject = BusinessDataServiceHelper.newDynamicObject("ozwe_moneylog");
    			StringBuffer sb = new StringBuffer();
    			for (int i1 = 1 ; i1<=10; i1++) {
    				int ascii = 48+(int)(Math.random()*9);
    				char c = (char) ascii;
    				sb.append(c);
    			}
    			dynamicObject.set("number", sb.toString());
    			dynamicObject.set("ozwe_name", dObject.getString("name"));
    			dynamicObject.set("ozwe_money1", allAmount);
    			dynamicObject.set("ozwe_money2", Double.parseDouble(dObjectBook.getString("ozwe_borrowamount")));
    			dynamicObject.set("ozwe_typename", "扣款");
    			dynamicObject.set("ozwe_reason", "消费扣款");
    			dynamicObject.set("ozwe_money3", allAmount- Double.parseDouble(dObjectBook.getString("ozwe_borrowamount")));
    			dynamicObject.set("ozwe_largetextfield", "借阅书籍消费");
    			dynamicObject.set("ozwe_datetimefield", new Date());
    			SaveServiceHelper.saveOperate("ozwe_moneylog", new DynamicObject[] {dynamicObject}, null);
    			//记录用户行为并做出相应操作
    			DynamicObject dy1 = BusinessDataServiceHelper.loadSingle("ozwe_usermassage", 
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
    		      		+ "ozwe_face",
    		      		new QFilter[] {new QFilter("name", QCP.equals, dObject.getString("name"))});
    			int jyAll = dy1.getInt("ozwe_point1");
    			int kpAll = dy1.getInt("ozwe_point2");
    			int zzAll = dy1.getInt("ozwe_point3");
    			int wxAll = dy1.getInt("ozwe_point4");
    			int zlAll = dy1.getInt("ozwe_point5");
    			if (contractBook.getString("ozwe_category").contains("教育")) {
    				jyAll+=30;
    				dy1.set("ozwe_point1", jyAll);
    			}
    			else if (contractBook.getString("ozwe_category").contains("科普")) {
    				kpAll+=30;
    				dy1.set("ozwe_point2", kpAll);
    			}
    			else if (contractBook.getString("ozwe_category").contains("政治")) {
    				zzAll+=30;
    				dy1.set("ozwe_point3", zzAll);
    			}
    			else if (contractBook.getString("ozwe_category").contains("文学")) {
    				wxAll+=30;
    				dy1.set("ozwe_point4", wxAll);
    			}
    			else if (contractBook.getString("ozwe_category").contains("资料")) {
    				zlAll+=30;
    				dy1.set("ozwe_point5", zlAll);
    			}
    			SaveServiceHelper.saveOperate("ozwe_usermassage", new DynamicObject[] {dy1}, null);
    			//保存借阅信息
    			DynamicObject newBorrow = BusinessDataServiceHelper.newDynamicObject("ozwe_ledbookbill");
    			StringBuffer sb1 = new StringBuffer();
    			for (int i1 = 1 ; i1<=10; i1++) {
    				int ascii = 48+(int)(Math.random()*9);
    				char c = (char) ascii;
    				sb1.append(c);
    			}
    			newBorrow.set("ozwe_bookpicture", dObjectBook.getString("ozwe_bookpicture"));
    			newBorrow.set("billno", sb1.toString());
    			newBorrow.set("ozwe_bookno", bookNo);
    			newBorrow.set("creator", dObject.getLong("ozwe_userid"));
    			newBorrow.set("ozwe_borrownumber", 1);
    			newBorrow.set("ozwe_bookname", dObjectBook.getString("name"));
    			newBorrow.set("ozwe_bookauthor", dObjectBook.getString("ozwe_textfield"));
    			newBorrow.set("ozwe_category", dObjectBook.getString("ozwe_category"));
    			newBorrow.set("ozwe_iscontinue", "续借");
    			newBorrow.set("ozwe_isreturn", "归还");
    			newBorrow.set("ozwe_return", "否");
    			newBorrow.set("ozwe_borrowamount", dObjectBook.getString("ozwe_borrowamount"));
    			newBorrow.set("ozwe_borrowcredit", 100);
    			newBorrow.set("ozwe_deposit", 0);
    			newBorrow.set("ozwe_continueday", 1);
    			newBorrow.set("ozwe_days", jo.getInt("day"));
    			newBorrow.set("ozwe_telephone", 888);
    			Date date = new Date();
    	        Calendar ca = Calendar.getInstance();
    	        ca.setTime(date);
    	        ca.add(Calendar.DAY_OF_YEAR, jo.getInt("day"));
    	        Date endDate = ca.getTime();
    			newBorrow.set("ozwe_datefield", endDate);
    			newBorrow.set("ozwe_userid", dObject.getString("ozwe_userid"));
    			SaveServiceHelper.saveOperate("ozwe_ledbookbill", new DynamicObject[] {newBorrow}, null);
    			//向借阅人发送短信
    			List<Long> receivers = new ArrayList<>();
    			receivers.add(Long.parseLong(dObject.getString("ozwe_userid")));
    			  SendMessage.sendMessage(
    						"书籍借阅成功！", 
    						"您成功借阅书籍《"+dObjectBook.getString("name")+"》，请注意及时归还！", 
    						"书籍借阅成功", 
    						"",
    						"",
    						receivers);
    			
    			apiResult.setData("222");
    			return apiResult;
        	}
			
		
		apiResult.setData("333");
		
		return apiResult;
		
	}
	

}
