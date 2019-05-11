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
	public static String weather() {
		StringBuffer sb = new StringBuffer();//用来存放查询到的json字符串
		try {
			URL u = new URL("https://way.jd.com/jisuapi/weather?city=深圳&cityid=111&citycode=101260301&appkey=462f2012cf4243e72597521b7738fee2");
			URLConnection openConnection = u.openConnection();
			InputStream inputStream = openConnection.getInputStream();
			
			InputStreamReader is = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(is);
			String line;
			while((line=br.readLine())!=null) {	
				sb.append(line);//每查询到一行就追加进去
				line=br.readLine();//追加一次更新一次字符串,然后继续读
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	/**BCrypt加密
	 * original:原密码
	 * salt：盐
	 */
	public static String getCode(String original, Integer salt) {
		String Code=BCrypt.hashpw(original, BCrypt.gensalt(salt));
		return Code;
	}
	/**BCrypt验证密文
	 * testCode：需要确认的密码（明文）
	 * Code：原密码（密文）
	 */
	public static boolean checkMatch(String testCode, String Code) {
		boolean checkpw = BCrypt.checkpw(testCode, Code);
		return checkpw;
	}
	
}
