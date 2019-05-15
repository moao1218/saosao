package cn.saosao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.ClaimVerifyMapper;
import cn.saosao.service.IClaimVerifyService;
@Service
public class CliamVerifyImpl implements IClaimVerifyService{
	@Autowired
	ClaimVerifyMapper claimVerfyMapper;
	@Override
	public List getList(String claimid) {
		
		return claimVerfyMapper.getList(claimid);
	}
	
}
