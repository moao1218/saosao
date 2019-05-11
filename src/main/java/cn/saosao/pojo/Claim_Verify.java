package cn.saosao.pojo;

import java.io.Serializable;
/**
 * 理赔勘查清单表
 * @author THINK
 *
 */
public class Claim_Verify implements Serializable {
	private String cla_ver_id; //勘察编号
	private String item_name;//物品名称
	private String site_photo;//现场拍摄照片
	private String third_pic;//第三方证明照片
	private String user_age;//使用年限
	private String invoice_pic;//发票图片
	private String invoice;//发票金额
	private String mark;//品牌
	private String itme_model;//型号
	private String house_no;//房产证号码
	private String house_pic;//房产证照片
	private String address;//房屋地址
	private String building_pic;//房屋照片
	private String acreage;//房屋面积
	private String house_market;//房屋市值
	private String house_age;//房屋使用年限
	private String completed_date;//房屋竣工时间
	private String verify_date;//勘察时间
	private String scout;//勘察员
	private String del_status;//逻辑删除
	
	private Items_List items_list; //物品清单对象
	private Claim_List claim_list;//理赔单列表对象
	
	
	
	public Claim_List getClaim_list() {
		return claim_list;
	}

	public void setClaim_list(Claim_List claim_list) {
		this.claim_list = claim_list;
	}

	public String getCla_ver_id() {
		return cla_ver_id;
	}

	public void setCla_ver_id(String cla_ver_id) {
		this.cla_ver_id = cla_ver_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getSite_photo() {
		return site_photo;
	}

	public void setSite_photo(String site_photo) {
		this.site_photo = site_photo;
	}

	public String getThird_pic() {
		return third_pic;
	}

	public void setThird_pic(String third_pic) {
		this.third_pic = third_pic;
	}

	public String getUser_age() {
		return user_age;
	}

	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}

	public String getInvoice_pic() {
		return invoice_pic;
	}

	public void setInvoice_pic(String invoice_pic) {
		this.invoice_pic = invoice_pic;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getItme_model() {
		return itme_model;
	}

	public void setItme_model(String itme_model) {
		this.itme_model = itme_model;
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

	public Items_List getItems_list() {
		return items_list;
	}

	public void setItems_list(Items_List items_list) {
		this.items_list = items_list;
	}
	
	
}
