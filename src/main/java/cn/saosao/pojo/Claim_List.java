package cn.saosao.pojo;

import java.io.Serializable;
/**
 * 理赔列表
 * @author THINK
 *
 */
public class Claim_List implements Serializable {
	private String claimid;  //理赔编号
	private String claim_name;//申请人姓名
	private String claim_card;//申请人证件号码
	private String claim_phone;//申请人电话号码
	private String reason;//申请理由
	private String claim_date;//申请时间
	private String pol_property;//保单性质
	private String site_photo;//现场照片
	private String third_pic;//第三方证明照片
	private String initial_amount;//初始金额
	private String final_amount;//最终金额
	private String upper_operator;//上一级操作人
	private String upper_opinion;//上一级意见
	private String upper_date;//上一级操作时间
	private String scout;//勘察操作人
	private String first_auditor;//一审操作人
	private String second_auditor;//二审操作人
	private String third_auditor;//三审操作人
	private String accountant;//会计操作人
	private String final_date;//理赔结束时间
	private String del_status;//逻辑删除
	private String transfer_failed;//转账失败原因
	
	private Status status;//;理赔状态对象
	private Users users;//会员对象
	private Policy policy;//保单号对象
	
	
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	public String getClaimid() {
		return claimid;
	}
	public void setClaimid(String claimid) {
		this.claimid = claimid;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public String getClaim_name() {
		return claim_name;
	}
	public void setClaim_name(String claim_name) {
		this.claim_name = claim_name;
	}
	public String getClaim_card() {
		return claim_card;
	}
	public void setClaim_card(String claim_card) {
		this.claim_card = claim_card;
	}
	public String getClaim_phone() {
		return claim_phone;
	}
	public void setClaim_phone(String claim_phone) {
		this.claim_phone = claim_phone;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getClaim_date() {
		return claim_date;
	}
	public void setClaim_date(String claim_date) {
		this.claim_date = claim_date;
	}
	public String getPol_property() {
		return pol_property;
	}
	public void setPol_property(String pol_property) {
		this.pol_property = pol_property;
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
	public String getInitial_amount() {
		return initial_amount;
	}
	public void setInitial_amount(String initial_amount) {
		this.initial_amount = initial_amount;
	}
	public String getFinal_amount() {
		return final_amount;
	}
	public void setFinal_amount(String final_amount) {
		this.final_amount = final_amount;
	}
	public String getUpper_operator() {
		return upper_operator;
	}
	public void setUpper_operator(String upper_operator) {
		this.upper_operator = upper_operator;
	}
	public String getUpper_opinion() {
		return upper_opinion;
	}
	public void setUpper_opinion(String upper_opinion) {
		this.upper_opinion = upper_opinion;
	}
	public String getUpper_date() {
		return upper_date;
	}
	public void setUpper_date(String upper_date) {
		this.upper_date = upper_date;
	}
	public String getScout() {
		return scout;
	}
	public void setScout(String scout) {
		this.scout = scout;
	}
	public String getFirst_auditor() {
		return first_auditor;
	}
	public void setFirst_auditor(String first_auditor) {
		this.first_auditor = first_auditor;
	}
	public String getSecond_auditor() {
		return second_auditor;
	}
	public void setSecond_auditor(String second_auditor) {
		this.second_auditor = second_auditor;
	}
	public String getThird_auditor() {
		return third_auditor;
	}
	public void setThird_auditor(String third_auditor) {
		this.third_auditor = third_auditor;
	}
	public String getAccountant() {
		return accountant;
	}
	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}
	public String getFinal_date() {
		return final_date;
	}
	public void setFinal_date(String final_date) {
		this.final_date = final_date;
	}
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}
	public String getTransfer_failed() {
		return transfer_failed;
	}
	public void setTransfer_failed(String transfer_failed) {
		this.transfer_failed = transfer_failed;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
