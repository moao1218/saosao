package cn.saosao.pojo;

import java.io.Serializable;
/**
 * 理赔状态表
 * @author THINK
 *
 */
public class Status implements Serializable {
	private String statusid; //理赔状态编号
	private String status_name;//理赔状态名称
	
	public String getStatusid() {
		return statusid;
	}
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
}
