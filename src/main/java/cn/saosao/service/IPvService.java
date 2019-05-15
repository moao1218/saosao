package cn.saosao.service;

import java.util.List;
import java.util.Map;

import cn.saosao.pojo.Items;

public interface IPvService {

	public boolean insertPv(Map<String,Object> map);
	public List<Items> findAllItems();
}
