package cn.saosao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.saosao.mapper.StatusMapper;
import cn.saosao.pojo.Status;
import cn.saosao.service.IStatusService;

public class StatusService implements IStatusService {
	@Autowired
	StatusMapper statusMapper;
	
	@Override
	public Status getStatusById(String statusid) {
		return statusMapper.getStatusById(statusid);
	}

}
