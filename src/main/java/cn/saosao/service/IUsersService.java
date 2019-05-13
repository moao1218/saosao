package cn.saosao.service;

import cn.saosao.pojo.Users;

public interface IUsersService {
	/**根据id查用户
	 * 
	 * @param Usersid
	 * @return
	 */
	public Users getUsersById(String usersid);
}
