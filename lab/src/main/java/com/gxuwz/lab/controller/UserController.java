package com.gxuwz.lab.controller;

import com.gxuwz.lab.api.CommonResult;
import com.gxuwz.lab.entry.User;
import com.gxuwz.lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/6 21:16
 **/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {
	@Autowired
    private UserService userService;
	

	/**
	 * 学生用户注册
	 * @param user
	 * @return
	 */

	@PostMapping("/reg")
	public CommonResult regStuUser(@RequestBody User user) {
		System.out.println("这里是学生注册的reg");
		user.setRoleId(1);//学生为1
		User user2  = userService.regStuUser(user);
		if (user2 != null)
			return CommonResult.success(user2);
		else
			return CommonResult.validateFailed();
	}

	@GetMapping("/getUserList")
	public List<User> findAll(User user){
		System.out.println("这里是管理员的用户列表findall");
		List<User> list = userService.findAll(user);
		return list;

	}

	@GetMapping("/getUserList2")
	public List<User> findAll2(User user){
		System.out.println("这里是技术人员管理档案");
		List<User> list = userService.findAll2(user);
		return list;

	}

	@PostMapping("/save")
	public String save(@RequestBody User user){
		String result = userService.save(user);
		if(result != null){
			return "success";
		}else{
			return "error";
		}
	}

	@DeleteMapping("/deleteById/{userId}")
	public void deleteById(@PathVariable("userId") String userId){
		userService.deleteById(userId);
	}

	@PostMapping("/update")
	public String update(@RequestBody User user){
		String result = userService.updateById(user);
		if(result != null){
			return "success";
		}else{
			return "error";
		}
	}
	 
}
