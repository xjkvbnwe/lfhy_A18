package kd.bos.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class CompareFaceAPI {
	
	@SuppressWarnings("finally")
	public static String CompareFace(String url , Map<String , String> requestParams) {
		try {
			String result = null;
			CloseableHttpClient httpClient = HttpClients.createDefault();
			/** HttpPost */
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> it = requestParams.entrySet().iterator();
//			System.out.println(params.toString());
			while (it.hasNext()) {
				Entry<String, String> en = it.next();
				String key = en.getKey();
				String value = en.getValue();
				if (value != null) {
					params1.add(new BasicNameValuePair(key, value));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(params1, "UTF-8"));
			/** HttpResponse */
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			try {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity, "utf-8");
				EntityUtils.consume(httpEntity);
			} finally {
				try {
					if (httpResponse != null) {
						httpResponse.close();
					}
				} catch (IOException e) {
					System.out.println("## release resouce error ##" + e);
				}
				return result;
			}
		} catch (Exception ee) {
			ee.printStackTrace();
			return "出错了！";
		}
  }
}
