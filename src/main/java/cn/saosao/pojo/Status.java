package cn.saosao.pojo;

import java.io.Serializable;
/**
 * 理赔状态表
 * @author THINK
 *
 */
public class Status implements Serializable {
	private Integer statusid; //理赔状态编号
	private String status_name;//理赔状态名称
	public Integer getStatusid() {
		return statusid;
	}
	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
}
