package cn.saosao.mapper.Finance;

import java.util.List;
import java.util.Map;

public interface InsurancePolicyMapper {

	//查询且多条件
	public List<Map<String, Object>> findAll(Map<String, Object> map);
	
	//通过id修改状态
	public int updateByidtozhaungtai(String id);
	
	//根据用户的id查询银行卡
	public String selectuser(String id);
	
	public List<Map<String, Object>> selectByidall(String id);
	
}
