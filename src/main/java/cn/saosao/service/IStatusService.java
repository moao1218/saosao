package cn.saosao.service;

import java.util.List;

import cn.saosao.pojo.Status;

public interface IStatusService {
	/**
	 * 查询全部状态
	 * @return
	 */
	public List<Status> findAllStatus();
	/**根据id查状态
	 * 
	 * @param statusid
	 * @return
	 */
	public Status getStatusById(String statusid);
}
