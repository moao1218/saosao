package cn.saosao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.saosao.mapper.UsersMapper;
import cn.saosao.pojo.Users;
import cn.saosao.service.IUsersService;

public class UsersService implements IUsersService {
	@Autowired
	UsersMapper usersMapper;
	
	@Override
	public Users getUsersById(String usersid) {
		return usersMapper.getUsersById(usersid);
	}
	
	
}
