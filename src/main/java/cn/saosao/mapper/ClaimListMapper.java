package cn.saosao.mapper;

import java.util.List;
import java.util.Map;

import cn.saosao.pojo.Claim_List;

public interface ClaimListMapper {
	
	public List findAll(Map map);
	
	
	public boolean updateClaim(Claim_List claim);
}
