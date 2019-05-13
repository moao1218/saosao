package cn.saosao.service;

import cn.saosao.pojo.Status;

public interface IStatusService {
	/**根据id查状态
	 * 
	 * @param statusid
	 * @return
	 */
	public Status getStatusById(String statusid);
}
