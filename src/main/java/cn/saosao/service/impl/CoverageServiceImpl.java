package cn.saosao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.CoverageMapper;
import cn.saosao.pojo.Coverage;
import cn.saosao.service.ICoverageService;
@Service
public class CoverageServiceImpl implements ICoverageService {
	@Autowired
	CoverageMapper coverageMapper;
	@Override
	public Coverage getCoverageById(String coverageid) {
		// TODO Auto-generated method stub
		return coverageMapper.getCoverageById(coverageid);
	}

}
