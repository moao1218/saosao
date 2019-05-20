package cn.saosao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IDUtil {
	
	public static String getTest(String id,String name) throws IOException {
		String host = "https://idcert.market.alicloudapi.com";
        String path = "/idcard";
        String appcode = "5330e23d4fd44c32a7aa5509b5e04669";

        String urlSend = host + path + "?idCard=" + id + "&name=" + name;
		
        URL url = new URL(urlSend);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "APPCODE " + appcode);//鏍煎紡Authorization:APPCODE (涓棿鏄嫳鏂囩┖鏍�)
        int httpCode = httpURLConnection.getResponseCode();
        String json = read(httpURLConnection.getInputStream());
        System.out.println(httpCode);
        
	    return json;
	}
	private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
