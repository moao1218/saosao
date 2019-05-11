package cn.saosao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.mindrot.bcrypt.BCrypt;





/**
 * 天气接口
 * @author THINK
 *
 */
public class InterfaceUtil {
	
	//天气接口
	public String weather() {
		StringBuffer b = new StringBuffer();
		try {
			URL u = new URL("https://way.jd.com/jisuapi/weather?city=深圳&appkey=3aa9dfcfc136e1953edb18c45d1835f6");
			URLConnection openConnection = u.openConnection();
			InputStream inputStream = openConnection.getInputStream();
			InputStreamReader is = new InputStreamReader(inputStream);
			BufferedReader bis = new BufferedReader(is);
			String readLine = bis.readLine();
			for(;readLine!=null;) {
				b.append(readLine);
				readLine = bis.readLine();
				String str = b.toString();
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(b.toString());
		return b.toString();
	}
	
	
	/**BCrypt加密
	 * original:原密码
	 * salt：盐
	 */
	public String getCode(String original, Integer salt) {
		String Code=BCrypt.hashpw(original, BCrypt.gensalt(salt));
		return Code;
	}
	/**BCrypt验证密文
	 * testCode：需要确认的密码（明文）
	 * Code：原密码（密文）
	 */
	public boolean checkMatch(String testCode, String Code) {
		boolean checkpw = BCrypt.checkpw(testCode, Code);
		return checkpw;
	}
	
}
