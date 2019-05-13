package cn.saosao.pojo;

import java.io.Serializable;
/**
 * 天气(webservice)
 * @author THINK
 *
 */
public class Weather implements Serializable {

	private String province;//省份
	private String cityname;//城市
	private String cityno;//城市代码
	private String cityImage;//城市图片名称
	private String lastUpdateTime;//最后更新时间
	private String temperature;//当天的 气温
	private String survey;//概况,比如   11月30日 多云
	private String windPower;//风向和风力
	private String startPic;//天气趋势开始图片名称
	private String endPic;//天气趋势结束图片名称
	private String weatherTruth;//现在的天气实况
	private String introduction;//天气和生活指数
	private String cityDesc;//被查询的城市或地区的介绍
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCityno() {
		return cityno;
	}
	public void setCityno(String cityno) {
		this.cityno = cityno;
	}
	public String getCityImage() {
		return cityImage;
	}
	public void setCityImage(String cityImage) {
		this.cityImage = cityImage;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getSurvey() {
		return survey;
	}
	public void setSurvey(String survey) {
		this.survey = survey;
	}
	public String getWindPower() {
		return windPower;
	}
	public void setWindPower(String windPower) {
		this.windPower = windPower;
	}
	public String getStartPic() {
		return startPic;
	}
	public void setStartPic(String startPic) {
		this.startPic = startPic;
	}
	public String getEndPic() {
		return endPic;
	}
	public void setEndPic(String endPic) {
		this.endPic = endPic;
	}
	public String getWeatherTruth() {
		return weatherTruth;
	}
	public void setWeatherTruth(String weatherTruth) {
		this.weatherTruth = weatherTruth;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getCityDesc() {
		return cityDesc;
	}
	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}
}
