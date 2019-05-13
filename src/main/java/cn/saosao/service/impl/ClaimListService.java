package cn.saosao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.saosao.mapper.ClaimListMapper;
import cn.saosao.pojo.Claim_List;
import cn.saosao.service.IClaimListService;

public class ClaimListService implements IClaimListService {
	@Autowired
	ClaimListMapper claimListMapper;
		
	@Override
	public List getAll(Map map) {
		return claimListMapper.findAll(map);
	}
	
}
