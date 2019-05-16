package cn.saosao.mapper;

import java.util.List;
import java.util.Map;

import cn.saosao.pojo.Items;

public interface PvMapper {

	public int insertPv(Map<String,Object> map);
	public List<Items> findAllItems();
}
