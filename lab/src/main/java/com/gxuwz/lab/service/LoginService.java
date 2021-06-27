package com.gxuwz.lab.service;

import com.gxuwz.lab.entry.User;
import com.gxuwz.lab.entry.UserRole;
import com.gxuwz.lab.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/7 16:19
 *
 **/
@Transactional
@Service("loginService")
public class LoginService {
	@Autowired
	UserMapper userMapper;
	
	
	public void save(User artisan) {
		userMapper.addUser(artisan);
		
	}

	public void deleteById(Integer id) {
		userMapper.deleteUserByid(id);
		
	}

	public User checkLogin(String userId, String pwd) {

        User user = userMapper.checkLogin(userId,pwd);
		System.out.println("user++++:::"+user);

	    return user;
		
	}
}
