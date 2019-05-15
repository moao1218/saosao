package cn.saosao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.saosao.mapper.Finance.DaiZhiFuMapper;
import cn.saosao.pojo.Claim_List;
import cn.saosao.service.Finance.DaiZhiFuServer;
import oracle.net.aso.d;

@Service
public class DaiZhiFuImpl implements DaiZhiFuServer{
	@Autowired
	DaiZhiFuMapper dzf;
	
	
	@Override
	public List<Map<String, Object> >  findall(Map<String, Object> map) {
		List<Map<String, Object> >  findall = dzf.findall(map);
		return findall;
	}

	@Transactional
	@Override
	public int insertall(Map<String, Object> map) {
		int insertall = dzf.insertall(map);
		return insertall;
	}


	@Override
	public List<Map<String, Object>> selectByid(String id) {
		List<Map<String, Object>> selectByid = dzf.selectByid(id);
		return selectByid;
	}

	//改原因
	@Override
	public int updateByidtoyuanying(String id, String yuanying) {
		int updateByidtoyuanying = dzf.updateByidtoyuanying(id, yuanying);
		return updateByidtoyuanying;
	}

	//改状态
	@Override
	public int updateByidtozhuangtai(String id, int zhuangtai) {
		int updateByidtozhuangtai = dzf.updateByidtozhuangtai(id, zhuangtai);
		return updateByidtozhuangtai; 
	}

	@Override
	public List<Map<String, Object>> findByid(String id) {
		List<Map<String, Object>> findByid = dzf.findByid(id);
		return findByid;
	}

	
	
	
	
}
