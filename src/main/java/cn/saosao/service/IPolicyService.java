package cn.saosao.service;

import cn.saosao.pojo.Policy;

public interface IPolicyService {
	/**根据id查保单
	 * 
	 * @param policyid
	 * @return
	 */
	public Policy getPolicyById(String policyid);
}
