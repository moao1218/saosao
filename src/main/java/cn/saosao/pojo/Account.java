package cn.saosao.pojo;


/**
 * 转账明细表
 * @author wh
 *
 */
public class Account {

	private long ACCOUNTID; // 明细编号
	private long CLAIMID; //理赔编号
	private long POLICYID; //保单编号
	private String MONEY; //理赔金额
	private long USERID; //会员编号
	private String USER_CARD; //身份证
	private String BANC_CARD; //银行卡
	private String ACCOUNTANT; //操作人
	private String TRANSFERTIME; //操作时间
	private String ACCOUNT_STATUS; //操作状态
	private String REMARKS; //备注
	private String YULIU; //预留

	@Override
	public String toString() {
		return "Account [ACCOUNTID=" + ACCOUNTID + ", CLAIMID=" + CLAIMID + ", POLICYID=" + POLICYID + ", MONEY="
				+ MONEY + ", USERID=" + USERID + ", USER_CARD=" + USER_CARD + ", BANC_CARD=" + BANC_CARD
				+ ", ACCOUNTANT=" + ACCOUNTANT + ", TRANSFERTIME=" + TRANSFERTIME + ", ACCOUNT_STATUS=" + ACCOUNT_STATUS
				+ ", REMARKS=" + REMARKS + ", YULIU=" + YULIU + "]";
	}

	public long getACCOUNTID() {
		return ACCOUNTID;
	}

	public void setACCOUNTID(long aCCOUNTID) {
		ACCOUNTID = aCCOUNTID;
	}

	public long getCLAIMID() {
		return CLAIMID;
	}

	public void setCLAIMID(long cLAIMID) {
		CLAIMID = cLAIMID;
	}

	public long getPOLICYID() {
		return POLICYID;
	}

	public void setPOLICYID(long pOLICYID) {
		POLICYID = pOLICYID;
	}

	public String getMONEY() {
		return MONEY;
	}

	public void setMONEY(String mONEY) {
		MONEY = mONEY;
	}

	public long getUSERID() {
		return USERID;
	}

	public void setUSERID(long uSERID) {
		USERID = uSERID;
	}

	public String getUSER_CARD() {
		return USER_CARD;
	}

	public void setUSER_CARD(String uSER_CARD) {
		USER_CARD = uSER_CARD;
	}

	public String getBANC_CARD() {
		return BANC_CARD;
	}

	public void setBANC_CARD(String bANC_CARD) {
		BANC_CARD = bANC_CARD;
	}

	public String getACCOUNTANT() {
		return ACCOUNTANT;
	}

	public void setACCOUNTANT(String aCCOUNTANT) {
		ACCOUNTANT = aCCOUNTANT;
	}

	public String getTRANSFERTIME() {
		return TRANSFERTIME;
	}

	public void setTRANSFERTIME(String tRANSFERTIME) {
		TRANSFERTIME = tRANSFERTIME;
	}

	public String getACCOUNT_STATUS() {
		return ACCOUNT_STATUS;
	}

	public void setACCOUNT_STATUS(String aCCOUNT_STATUS) {
		ACCOUNT_STATUS = aCCOUNT_STATUS;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public String getYULIU() {
		return YULIU;
	}

	public void setYULIU(String yULIU) {
		YULIU = yULIU;
	}

	
	
	
	
}