package cn.saosao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.ClaimListMapper;
import cn.saosao.pojo.Claim_List;
import cn.saosao.service.IClaimListService;
@Service
public class ClaimListServiceImpl implements IClaimListService {
	@Autowired
	ClaimListMapper claimListMapper;
		
	@Override
	public List getAll(Map map) {
		return claimListMapper.findAll(map);
	}
	
}
