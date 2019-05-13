package cn.saosao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

public interface ClaimListMapper {
	
	public List findAll(Map map);
}
