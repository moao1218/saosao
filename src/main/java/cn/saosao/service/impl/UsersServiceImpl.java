package cn.saosao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.saosao.mapper.UsersMapper;
import cn.saosao.pojo.Users;
import cn.saosao.service.IUsersService;
@Service
public class UsersServiceImpl implements IUsersService {
	@Autowired
	UsersMapper usersMapper;
	
	@Override
	public Users getUsersById(String usersid) {
		return usersMapper.getUsersById(usersid);
	}
	
	
}
