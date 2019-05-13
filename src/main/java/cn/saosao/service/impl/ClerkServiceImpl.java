package cn.saosao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.ClerkMapper;
import cn.saosao.pojo.Clerk;
import cn.saosao.service.IClerkService;
@Service
public class ClerkServiceImpl implements IClerkService {
	
	@Autowired
	ClerkMapper clerkMapper;
	
	
	@Override
	public Clerk findUserPwd(Clerk clerk) {
		return clerkMapper.findUserPwd(clerk);
	}
	@Override
	public Clerk checkLogin(Clerk clerk) {
		return clerkMapper.checkLogin(clerk);
	}
	@Override
	public Clerk getClerkById(String magid) {
		return clerkMapper.getClerkById(magid);
	}

	

}
