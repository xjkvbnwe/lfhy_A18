package kd.bos.debug.mservice;

import kd.bos.config.client.util.ConfigUtils;
import kd.bos.service.webserver.JettyServer;

@SuppressWarnings("deprecation")
public class DebugServer {

	public static void main(String[] args) throws Exception 
	{
		System.setProperty(ConfigUtils.APP_NAME_KEY, "mservice-biz1.5-cosmic");
		//设置集群环境名称和配置服务器地址
		System.setProperty(ConfigUtils.CLUSTER_NAME_KEY, "cosmic");
		System.setProperty(ConfigUtils.CONFIG_URL_KEY, "127.0.0.1:2181");
	    System.setProperty("configAppName", "mservice,web");
	    System.setProperty("webmserviceinone", "true");

		System.setProperty("file.encoding", "utf-8");
	    System.setProperty("xdb.enable", "false");
		
		System.setProperty("mq.consumer.register", "true");
	    System.setProperty("MONITOR_HTTP_PORT", "9998");
	    System.setProperty("JMX_HTTP_PORT", "9091");
	    System.setProperty("dubbo.protocol.port", "28888");
	    System.setProperty("dubbo.consumer.url", "dubbo://www.dream-y.top:28888");
	    System.setProperty("dubbo.consumer.url.qing", "dubbo://www.dream-y.top:30880");
	    System.setProperty("dubbo.registry.register", "true");
		//System.setProperty("mq.debug.queue.tag", "whb1133");
		System.setProperty("dubbo.service.lookup.local", "true");
	    System.setProperty("appSplit", "false");

	    System.setProperty("lightweightdeploy","true");
		
		System.setProperty("db.sql.out", "false");

		System.setProperty("JETTY_WEB_PORT","8080");
		System.setProperty("JETTY_WEBAPP_PATH", "../../../mservice-cosmic/webapp");
		System.setProperty("JETTY_WEBRES_PATH", "../../../static-file-service");

		System.setProperty("domain.contextUrl","https://www.dream-y.top/ierp");		
	    System.setProperty("domain.tenantCode","cosmic-simple");
	    System.setProperty("tenant.code.type","config");
		
		//System.setProperty("fileserver","https://www.dream-y.top:8100/fileserver/");
	    //System.setProperty("imageServer.url","https://www.dream-y.top:8100/fileserver/");
	    System.setProperty("bos.app.special.deployalone.ids","");
		System.setProperty("mc.server.url","http://www.dream-y.top:8090/");
		JettyServer.main(null);
	}

}