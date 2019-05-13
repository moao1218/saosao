package cn.saosao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.PolicyMapper;
import cn.saosao.pojo.Policy;
import cn.saosao.service.IPolicyService;
@Service
public class PolicyServiceImpl implements IPolicyService {
	@Autowired
	PolicyMapper policyMapper;
	
	@Override
	public Policy getPolicyById(String policyid) {
		return policyMapper.getPolicyByid(policyid);
	}

}
