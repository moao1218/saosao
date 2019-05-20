package cn.saosao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;





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
	/**
	 * 文件上传
	 * @param request
	 * @param pic
	 * @return
	 */
	public static String upload(HttpServletRequest request,MultipartFile pic) {
		OutputStream os = null;
		String ul ="";
		try {
		    /*String realpath = request.getSession().getServletContext().getRealPath("/upload");*/
			String realpath = "C:\\Users\\Administrator\\Desktop\\我的项目\\saosao\\src\\main\\resources\\static\\upload";
			String oldname = pic.getOriginalFilename();
			System.out.println("oldname:"+oldname);
			String endname = oldname.substring(oldname.lastIndexOf("."));
			String uuid = UUID.randomUUID().toString();
			File file = new File(realpath + "/" + uuid + endname);
			os = new FileOutputStream(file);
			
			ul = "upload/" + uuid + endname;
			FileCopyUtils.copy(pic.getInputStream(), os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ul;
	}
	
}
