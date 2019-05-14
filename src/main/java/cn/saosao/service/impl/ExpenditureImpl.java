package cn.saosao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.Finance.ExpenditureMapper;
import cn.saosao.pojo.Account;
import cn.saosao.service.Finance.ExpenditureServer;

@Service
public class ExpenditureImpl implements ExpenditureServer{
	@Autowired
	ExpenditureMapper ex;
	
	
	@Override
	public List<Map<String, Object>> finAll(Map<String, Object> m) {
		List<Map<String, Object>> finAll = ex.finAll(m);

		return (List<Map<String, Object>>) m.get("accounts");
	}


	@Override
	public int findcount() {
		int findcount = ex.findcount();
		return findcount;
	}

	
	
}
