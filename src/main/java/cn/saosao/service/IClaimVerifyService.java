package cn.saosao.service;

import java.util.List;

import cn.saosao.pojo.Claim_Verify;

public interface IClaimVerifyService {
	
	public List getList(String claimid);
	public boolean addClaimVer(Claim_Verify cla_ver);
}
