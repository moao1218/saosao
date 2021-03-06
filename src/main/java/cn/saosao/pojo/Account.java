package cn.saosao.pojo;


/**
 * 转账明细表
 * @author wh
 *
 */
public class Account {

	private String accountid; // 明细编号
	private String claimid; //理赔编号
	private String policyid; //保单编号
	private String money; //理赔金额
	private String userid; //会员编号
	private String user_card; //身份证
	private String banc_card; //银行卡
	private String accountant; //操作人
	private String transfertime; //操作时间
	private String account_status; //操作状态
	private String remarks; //备注
	private String yuliu; //预留
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getClaimid() {
		return claimid;
	}
	public void setClaimid(String claimid) {
		this.claimid = claimid;
	}
	public String getPolicyid() {
		return policyid;
	}
	public void setPolicyid(String policyid) {
		this.policyid = policyid;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUser_card() {
		return user_card;
	}
	public void setUser_card(String user_card) {
		this.user_card = user_card;
	}
	public String getBanc_card() {
		return banc_card;
	}
	public void setBanc_card(String banc_card) {
		this.banc_card = banc_card;
	}
	public String getAccountant() {
		return accountant;
	}
	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}
	public String getTransfertime() {
		return transfertime;
	}
	public void setTransfertime(String transfertime) {
		this.transfertime = transfertime;
	}
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getYuliu() {
		return yuliu;
	}
	public void setYuliu(String yuliu) {
		this.yuliu = yuliu;
	}
	@Override
	public String toString() {
		return "Account [accountid=" + accountid + ", claimid=" + claimid + ", policyid=" + policyid + ", money="
				+ money + ", userid=" + userid + ", user_card=" + user_card + ", banc_card=" + banc_card
				+ ", accountant=" + accountant + ", transfertime=" + transfertime + ", account_status=" + account_status
				+ ", remarks=" + remarks + ", yuliu=" + yuliu + "]";
	}
	
	
	
	
	
}