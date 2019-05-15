package cn.saosao.pojo;

import java.io.Serializable;



public class Pv implements Serializable {
	private String cla_ver_id; //勘察编号
	private String claimid;//理赔号
	private String house_no; //房产证号码
	private String house_pic; //房产证照片
	private String address; //房屋地址
	private String building_pic; //房屋照片
	private String acreage; //房屋面积
	private String house_market; //房屋市值
	private String house_age; //房屋使用年限
	private String completed_date; //房屋竣工时间
	private String verify_date; //勘察时间
	private String scout; //勘察员
	private String del_status;
	public String getCla_ver_id() {
		return cla_ver_id;
	}
	public void setCla_ver_id(String cla_ver_id) {
		this.cla_ver_id = cla_ver_id;
	}
	public String getClaimid() {
		return claimid;
	}
	public void setClaimid(String claimid) {
		this.claimid = claimid;
	}
	public String getHouse_no() {
		return house_no;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	public String getHouse_pic() {
		return house_pic;
	}
	public void setHouse_pic(String house_pic) {
		this.house_pic = house_pic;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBuilding_pic() {
		return building_pic;
	}
	public void setBuilding_pic(String building_pic) {
		this.building_pic = building_pic;
	}
	public String getAcreage() {
		return acreage;
	}
	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}
	public String getHouse_market() {
		return house_market;
	}
	public void setHouse_market(String house_market) {
		this.house_market = house_market;
	}
	public String getHouse_age() {
		return house_age;
	}
	public void setHouse_age(String house_age) {
		this.house_age = house_age;
	}
	public String getCompleted_date() {
		return completed_date;
	}
	public void setCompleted_date(String completed_date) {
		this.completed_date = completed_date;
	}
	public String getVerify_date() {
		return verify_date;
	}
	public void setVerify_date(String verify_date) {
		this.verify_date = verify_date;
	}
	public String getScout() {
		return scout;
	}
	public void setScout(String scout) {
		this.scout = scout;
	}
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}
	
}
