package cn.saosao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.PvMapper;
import cn.saosao.pojo.Items;
import cn.saosao.service.IPvService;

@Service
public class PvServiceImpl implements IPvService {
	
	@Autowired
	private PvMapper pvmapper;

	@Override
	public List<Items> findAllItems() {
		return pvmapper.findAllItems();
	}

	@Override
	public boolean insertPv(Map<String,Object> map) {
		return pvmapper.insertPv(map)>0;
	}

}
