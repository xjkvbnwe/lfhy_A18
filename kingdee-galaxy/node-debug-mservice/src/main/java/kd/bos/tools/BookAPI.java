package kd.bos.tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class BookAPI {
	/**
	 * 根据第三方接口获取书籍信息
	 * @param ISBN
	 * @param Appid
	 * @param Sign
	 * @return
	 */
	public static String getIsbnBookJson(String ISBN,String Appid,String Sign) {
		try {
			URL u = new URL("http://route.showapi.com/2218-1?showapi_appid="+Appid+"&isbn="+ISBN+"&showapi_sign="+Sign);
			  InputStream in = u.openStream();
			  ByteArrayOutputStream out = new ByteArrayOutputStream();
			  try {
			    byte buf[] = new byte[1024];
			    int read = 0;
			    while ((read = in.read(buf)) > 0) {
			      out.write(buf, 0, read);
			    }
			  } finally {
			    if ( in != null) {
			      in.close();
			    }
			  }
			  byte b[] = out.toByteArray();
			  
			return new String(b,"utf-8");
			} catch(Exception ee) {
				return null;
			}
	}
}
