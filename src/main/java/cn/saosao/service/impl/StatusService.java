package cn.saosao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.StatusMapper;
import cn.saosao.pojo.Status;
import cn.saosao.service.IStatusService;
@Service
public class StatusService implements IStatusService {
	@Autowired
	StatusMapper statusMapper;
	
	@Override
	public Status getStatusById(String statusid) {
		return statusMapper.getStatusById(statusid);
	}

	@Override
	public List<Status> findAllStatus() {
		return statusMapper.findAllStatus();
	}

}
