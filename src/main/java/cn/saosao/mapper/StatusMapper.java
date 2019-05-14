package cn.saosao.mapper;

import java.util.List;

import cn.saosao.pojo.Status;

public interface StatusMapper {
	/**
	 * 查询全部状态
	 * @return
	 */
	public List<Status> findAllStatus();
	public Status getStatusById(String statusid);
}
