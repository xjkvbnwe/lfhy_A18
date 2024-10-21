package kd.bos.user;

import java.util.Date;
import java.util.EventObject;

import kd.bos.base.AbstractBasePlugIn;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.form.control.events.ItemClickEvent;
import kd.bos.logging.Log;
import kd.bos.logging.LogFactory;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.operation.SaveServiceHelper;

public class UserBind extends AbstractBasePlugIn{
	@SuppressWarnings("unused")
	private final static Log logger = LogFactory.getLog(UserBind.class);
    @Override
    //注册监听器并添加响应控件
    public void registerListener(EventObject e) {
        super.registerListener(e);
        this.addItemClickListeners("titleapanel");
    }
    @Override
    //点击后执行方法体
    public void itemClick(ItemClickEvent e) {
    	super.itemClick(e);
        //获取点击控件的字段
        String itemKey = e.getItemKey();
        if (itemKey.equals("bar_saveandnew")) {
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
        	for (DynamicObject dy : contract) {
        		//判断新增时间
        		long startDateTime = dy.getDate("modifytime").getTime();
                int diffSeconds = (int) ((nowDate - startDateTime) / 1000);
        		if (diffSeconds <=1) {
        			//将信息添加到用户信息
        			DynamicObject dynamicObject = BusinessDataServiceHelper.newDynamicObject("ozwe_usermassage");
        			dynamicObject.set("number", dy.getString("number"));
        			dynamicObject.set("ozwe_userpicture", dy.getString("picturefield"));
        			dynamicObject.set("ozwe_combofield", dy.getString("gender"));
        			dynamicObject.set("name", dy.getString("name"));
        			dynamicObject.set("ozwe_telephone", dy.getString("phone"));
        			dynamicObject.set("enable", 1);
        			dynamicObject.set("status", dy.getString("status"));
        			
        			dynamicObject.set("ozwe_amountfield", 0);
        			dynamicObject.set("ozwe_canborrow", 5);
        			dynamicObject.set("ozwe_deposit", 0);
        			dynamicObject.set("ozwe_credit", 100);
        			dynamicObject.set("ozwe_userid", dy.getPkValue().toString());
        			SaveServiceHelper.saveOperate("ozwe_usermassage", new DynamicObject[] {dynamicObject}, null);
        		}
        	}
        }
    }
}
