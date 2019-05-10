package cn.saosao.pojo;

import java.io.Serializable;
/**
 * 物品清单表
 * @author THINK
 *
 */
public class Items_List implements Serializable {
	private Integer itemid;//物品编号
	private String item_name;//物品名称
	private String deductible;//免赔额
	private String excess;//免赔率
	private String dep_rate;//折旧率
	private String industry;//所属行业编号
	private String del_status;//逻辑删除
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
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}
	
}
