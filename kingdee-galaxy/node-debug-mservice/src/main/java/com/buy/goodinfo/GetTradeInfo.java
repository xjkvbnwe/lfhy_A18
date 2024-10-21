package com.buy.goodinfo;

import java.util.EventObject;

import kd.bos.bill.AbstractBillPlugIn;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;

public class GetTradeInfo extends AbstractBillPlugIn {
	
	@Override
    public void afterLoadData(EventObject e) {
		try {
			DynamicObjectCollection dy = this.getModel().getEntryEntity("ozwe_entryentity");
			for (int i = 0; i<dy.size(); i++) {
				String goodsId = dy.get(i).getString("goods_id");
				DynamicObject dObject = BusinessDataServiceHelper.loadSingle("ozwe_ownergoods"/*基础资料的标识*/, 
			      		  "goods_id,"
			      		+ "goods_img,"
			      		+ "goods_number,"
			      		+ "goods_name,"
			      		+ "goods_price"/*要获取的基础资料的内容，用,隔开*/,
			      		(new QFilter("goods_id", QCP.equals, goodsId/*条件*/)).toArray());
				this.getModel().setValue("ozwe_picture", dObject.getString("goods_img"), i);
				this.getModel().setValue("ozwe_number", dObject.getString("goods_number"), i);
				this.getModel().setValue("ozwe_goodname", dObject.getString("goods_name"), i);
				this.getModel().setValue("ozwe_amountsingle", dObject.getString("goods_price"), i);
				this.getModel().setValue("ozwe_amount", Double.parseDouble(dObject.getString("goods_price"))*Integer.parseInt(this.getModel().getValue("buy_total")+""), i);
			}
			this.getModel().setDataChanged(false);
		} catch(Exception ee) {
			
		}
		
	}

}
