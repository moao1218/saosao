package cn.saosao.mapper.Finance;

import java.util.List;
import java.util.Map;

import cn.saosao.pojo.Account;

/**
 * 财务支出信息查询
 * @author lenovo
 *
 */

public interface ExpenditureMapper {

	//查询所有的支出账单信息
	public List<Map<String, Object>> finAll(Map<String, Object> m);
	
	//查询总数
		public int findcount() ;
	
	
	
	
	
	
}
