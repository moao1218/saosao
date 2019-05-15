package cn.saosao.mapper.Finance;

import java.util.List;
import java.util.Map;

import cn.saosao.pojo.Claim_List;

public interface DaiZhiFuMapper {

	//查询所有
	List<Map<String, Object> >  findall(Map<String, Object> map);
	
	
	int insertall(Map<String, Object>map);
	List<Map<String, Object> > selectByid(String id);
	
	//根据id查询
	List<Map<String, Object> > findByid(String id);
	
	//转账失败修改 原因
	int updateByidtoyuanying(String id,String yuanying);
	
	//转账成功修改自己的状态
	int updateByidtozhuangtai(String id,int zhuangtai);
}
