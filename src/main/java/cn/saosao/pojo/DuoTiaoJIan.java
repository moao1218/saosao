package cn.saosao.pojo;

/**
 * 本类使用在财务模块 详细作用是在前端传递到后台的多条件参数分装成为一个类，方便传输
 * 
 * @author wh
 *
 */
public class DuoTiaoJIan {

	public int cp;// 页码
	public int ps;// 总页码
	public String acc = "";// 明细编号
	public String claimid = "";// 理赔编号
	public String policyid = "";// 保单号
	public String cname = "";// 名字
	public String moneymin;// 价格最小
	public String moneymax;// 价格最大
	public String accountant = "";// 操作人
	public String accounts = "";// 返回的值 为空就行
	public String zys;// 总数据 用来计算总行数

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMoneymin() {
		return moneymin;
	}

	public void setMoneymin(String moneymin) {
		this.moneymin = moneymin;
	}

	public String getMoneymax() {
		return moneymax;
	}

	public void setMoneymax(String moneymax) {
		this.moneymax = moneymax;
	}

	public String getAccountant() {
		return accountant;
	}

	public void setAccountant(String accountant) {
		this.accountant = accountant;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getZys() {
		return zys;
	}

	public void setZys(String zys) {
		this.zys = zys;
	}

	@Override
	public String toString() {
		return "DuoTiaoJIan [cp=" + cp + ", ps=" + ps + ", acc=" + acc + ", claimid=" + claimid + ", policyid="
				+ policyid + ", cname=" + cname + ", moneymin=" + moneymin + ", moneymax=" + moneymax + ", accountant="
				+ accountant + ", accounts=" + accounts + ", zys=" + zys + "]";
	}

}
