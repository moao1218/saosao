package cn.saosao.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import cn.saosao.pojo.Weather;

public class WeatherUtil {
	public static String getSoapRequestString(String cityname){
		String reqStr = "<?xml version='1.0' encoding='utf-8'?>"
				+ "<soap12:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
				+ "xmlns:xsd='http://www.w3.org/2001/XMLSchema' "
				+ "xmlns:soap12='http://www.w3.org/2003/05/soap-envelope'>"
				+ "<soap12:Body>"
				+ "<getWeatherbyCityName xmlns='http://WebXml.com.cn/'>"
				+ "<theCityName>"+cityname+"</theCityName>"
						+ "</getWeatherbyCityName></soap12:Body></soap12:Envelope>";
		return reqStr;
	}
	
	public static InputStream sendReq(String requestHeader){
		URL url = null;
		try {
			url = new URL("http://ws.webxml.com.cn/WebServices/WeatherWebService.asmx");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setDefaultUseCaches(false);
			
			conn.setRequestProperty("Host", "ws.webxml.com.cn");
			conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
			conn.setRequestProperty("Content-Length", requestHeader.length()+"");
			
			OutputStream os = conn.getOutputStream();
			OutputStreamWriter writer = new  OutputStreamWriter(os,"utf-8");
			writer.write(requestHeader);
			writer.flush();
			writer.close();
			
			InputStream is = conn.getInputStream();
			return is;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

	public static List<Weather> checkWeather(String cityname){
		
		InputStream is = sendReq(getSoapRequestString(cityname));
		StringBuffer buffer = new StringBuffer();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			NodeList nodelist = doc.getElementsByTagName("string");
			/*for (int i = 0; i < nodelist.getLength(); i++) {
				Node node = nodelist.item(i);
				if(!node.getFirstChild().getNodeValue().equals("查询结果为空！")){
					System.out.println(node.getFirstChild().getNodeValue());
				}
			}*/
			
			String nodevalue = nodelist.item(0).getFirstChild().getNodeValue();
			if(!nodevalue.equals("查询结果为空！")){
				//System.out.println(nodevalue);
				Weather today = new Weather();
				today.setProvince(nodevalue);
				today.setCityname(nodelist.item(1).getFirstChild().getNodeValue());
				today.setCityno(nodelist.item(2).getFirstChild().getNodeValue());
				today.setCityImage(nodelist.item(3).getFirstChild().getNodeValue());
				today.setLastUpdateTime(nodelist.item(4).getFirstChild().getNodeValue());
				today.setTemperature(nodelist.item(5).getFirstChild().getNodeValue());
				today.setSurvey(nodelist.item(6).getFirstChild().getNodeValue());
				today.setWindPower(nodelist.item(7).getFirstChild().getNodeValue());
				today.setStartPic(nodelist.item(8).getFirstChild().getNodeValue());
				today.setEndPic(nodelist.item(9).getFirstChild().getNodeValue());
				today.setWeatherTruth(nodelist.item(10).getFirstChild().getNodeValue());
				today.setIntroduction(nodelist.item(11).getFirstChild().getNodeValue());
				today.setCityDesc(nodelist.item(22).getFirstChild().getNodeValue());
				
				Weather nextday = new Weather();
				nextday.setTemperature(nodelist.item(12).getFirstChild().getNodeValue());
				nextday.setSurvey(nodelist.item(13).getFirstChild().getNodeValue());
				nextday.setWindPower(nodelist.item(14).getFirstChild().getNodeValue());
				nextday.setStartPic(nodelist.item(15).getFirstChild().getNodeValue());
				nextday.setEndPic(nodelist.item(16).getFirstChild().getNodeValue());
				
				Weather threeday = new Weather();
				threeday.setTemperature(nodelist.item(17).getFirstChild().getNodeValue());
				threeday.setSurvey(nodelist.item(18).getFirstChild().getNodeValue());
				threeday.setWindPower(nodelist.item(19).getFirstChild().getNodeValue());
				threeday.setStartPic(nodelist.item(20).getFirstChild().getNodeValue());
				threeday.setEndPic(nodelist.item(21).getFirstChild().getNodeValue());
				
				List<Weather> list = new ArrayList<Weather>();
				list.add(today);
				list.add(nextday);
				list.add(threeday);
				return list;
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
