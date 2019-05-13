package cn.saosao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.saosao.mapper.PolicyMapper;
import cn.saosao.pojo.Policy;
import cn.saosao.service.IPolicyService;

public class PolicyService implements IPolicyService {
	@Autowired
	PolicyMapper policyMapper;
	
	@Override
	public Policy getPolicyById(String policyid) {
		return policyMapper.getPolicyByid(policyid);
	}

}
