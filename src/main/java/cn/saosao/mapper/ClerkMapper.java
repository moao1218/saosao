package cn.saosao.mapper;

import cn.saosao.pojo.Clerk;

public interface ClerkMapper {
	
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
	/**
	 * 根据id查用户
	 * @return
	 */
	public Clerk getClerkById(String magid);
	
	
}
