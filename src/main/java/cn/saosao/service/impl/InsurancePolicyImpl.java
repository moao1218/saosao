package cn.saosao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.Finance.InsurancePolicyMapper;
import cn.saosao.service.Finance.InsurancePolicyServer;

@Service
public class InsurancePolicyImpl implements InsurancePolicyServer {
	@Autowired
	InsurancePolicyMapper ins;

	//查询全部且多条件
	@Override
	public List<Map<String, Object>> findAll(Map<String, Object> map) {
		List<Map<String, Object>> findAll = ins.findAll(map);
		return findAll;
	}

	@Override
	public int updateByidtozhaungtai(String id) {
		int updateByidtozhaungtai = ins.updateByidtozhaungtai(id);
		return updateByidtozhaungtai;
	}

	//银行卡
	@Override
	public String selectuser(String id) {
		String selectuser = ins.selectuser(id);
		return selectuser;
	}

	@Override
	public List<Map<String, Object>> selectByidall(String policyid) {
		List<Map<String, Object>> selectByidall = ins.selectByidall(policyid);
		return selectByidall;
	}

}
