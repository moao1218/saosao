package cn.saosao.service.Finance;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

public interface ExpenditureServer {
	
	// 查询所有的支出账单信息
	public List<Map<String, Object>> finAll(Map<String, Object> m);

	//查询总数
	public int findcount() ;
	
}
