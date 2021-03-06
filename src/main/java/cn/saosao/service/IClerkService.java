package cn.saosao.service;

import org.springframework.stereotype.Service;

import cn.saosao.pojo.Clerk;


public interface IClerkService {
	
	/**
	 * 查询加密之后的密码
	 * @param clerk
	 * @return
	 */
	public Clerk findUserPwd(Clerk clerk);
	/**
	 * 登录
	 * @return
	 */
	public Clerk checkLogin(Clerk clerk);
	/**根据用户id查用户
	 * 
	 * @param magid
	 * @return
	 */
	public Clerk getClerkById(String magid);
	
	
	
	
	
}




