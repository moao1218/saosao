package cn.saosao.pojo;

import java.io.Serializable;
/**
 * 单独出来的清单表(需要添加多条的)
 * @author THINK
 *
 */
public class Items implements Serializable {
	private Integer itemid;//物品编号
	private String item_name;//物品名称
	private String deductible; //免赔额
	private String excess;//免赔率
	private String dep_rate;// 折旧率
	private String industry;//所属行业编号
	
	private String site_photo;//现场拍摄照片
	private String third_pic;//第三方证明照片
	private String user_age;//使用年限
	private String invoice_pic;//发票图片
	private String invoice;//发票金额
	private String mark;//品牌
	private String itme_model;//型号
	private String del_status;
	
	@Override
	public String toString() {
		return "Items [itemid=" + itemid + ", item_name=" + item_name + ", deductible=" + deductible + ", excess="
				+ excess + ", dep_rate=" + dep_rate + ", industry=" + industry + ", site_photo=" + site_photo
				+ ", third_pic=" + third_pic + ", user_age=" + user_age + ", invoice_pic=" + invoice_pic + ", invoice="
				+ invoice + ", mark=" + mark + ", itme_model=" + itme_model + ", del_status=" + del_status + "]";
	}
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getDeductible() {
		return deductible;
	}
	public void setDeductible(String deductible) {
		this.deductible = deductible;
	}
	public String getExcess() {
		return excess;
	}
	public void setExcess(String excess) {
		this.excess = excess;
	}
	public String getDep_rate() {
		return dep_rate;
	}
	public void setDep_rate(String dep_rate) {
		this.dep_rate = dep_rate;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
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
	
	
}
