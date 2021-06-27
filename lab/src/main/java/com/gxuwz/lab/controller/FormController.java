package com.gxuwz.lab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gxuwz.lab.api.CommonResult;
import com.gxuwz.lab.entry.Form;
import com.gxuwz.lab.service.FormService;
import java.util.List;
/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/9 15:16
 **/
@RestController
@RequestMapping("/form")
public class FormController {
	

	@Autowired
	private FormService formService;

	@GetMapping("/signUp/{id}/{userId}")
	public String signup(@PathVariable("id") Integer id,@PathVariable("userId") String userId){

		String jieguo = formService.signup(id,userId);

		if(jieguo == "success"){
			return "success";
		}else if(jieguo == "isExist"){
			return "isExist";
		}else{
			return "error";
		}
	}

	/**
	 * 查看某招聘计划中的面试者
	 * @param id
	 * @return
	 */
	@GetMapping("/review/{id}")
	public CommonResult review(@PathVariable("id") Integer id){

		List<Form> list = formService.getReviewByRecruitId(id);

		if (list != null)
			return CommonResult.success(list);
		else
			return CommonResult.validateFailed();


	}


	@GetMapping("/interview/{planId}/{userId}/{num}")
	public String interview(@PathVariable("userId") String userId,@PathVariable("planId") Integer planId,@PathVariable("num") Integer num){

		String result = formService.interview(planId,userId,num);

		if(result != null){
			return "success";
		}else{
			return "error";
		}

	}


}
