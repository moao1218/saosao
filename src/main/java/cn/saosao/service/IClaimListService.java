package cn.saosao.service;

import java.util.List;
import java.util.Map;

import cn.saosao.pojo.Claim_List;

public interface IClaimListService {
	/**查询所有
	 * 
	 * @param map
	 * @return
	 */
	public List getAll(Map map);
	/**
	 * 修改
	 * @param claim
	 * @return
	 */
	public boolean updateClaim(Claim_List claim);
}
