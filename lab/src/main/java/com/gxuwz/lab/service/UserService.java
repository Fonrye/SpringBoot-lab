package com.gxuwz.lab.service;

import com.gxuwz.lab.entry.User;
import com.gxuwz.lab.mapper.UserMapper;
import com.gxuwz.lab.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 23:33
 **/
@Transactional
@Service("userService")
public class UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserRoleMapper userroleMapper;
//管理员管理所有用户
	public List<User> findAll(User user) {
		return userMapper.selectUserList(user);
	}

//	技术人员管理人员档案
	public List<User> findAll2(User user) {
	return userMapper.selectUserList2(user);
}
	/**
	 * 管理员添加用户
	 * @param user
	 * @return
	 */
	public String save(User user) {

		try{
			userroleMapper.addUserRole(user.getUserId(),user.getRoleId());
		    userMapper.addUser2(user);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用于学生注册
	 * @param user
	 * @return
	 */
	public User regStuUser(User user) {
		try{
			userroleMapper.addUserRole(user.getUserId(),user.getRoleId());
			userMapper.addUser(user);
			return user;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public void deleteById(String userId) {
		userMapper.deleteUserById(userId);

	}

	public String updateById(User user) {
		try{
			userroleMapper.updateUserRoleById(user.getRoleId(),user.getUserId());
			userMapper.updateById(user);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


}
