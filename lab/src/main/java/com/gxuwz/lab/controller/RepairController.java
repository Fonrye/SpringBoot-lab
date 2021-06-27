package com.gxuwz.lab.controller;

import com.gxuwz.lab.api.CommonResult;
import com.gxuwz.lab.entry.Dailyjob;
import com.gxuwz.lab.entry.Repair;
import com.gxuwz.lab.service.DailyService;
import com.gxuwz.lab.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/6 21:54
 **/
@RestController
@RequestMapping("/repair")
public class RepairController {
	
	@Autowired
	private RepairService repairService;
	SimpleDateFormat systime= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
	Date date = new Date(System.currentTimeMillis());


	@GetMapping("/list")
    public CommonResult getList() {

        List<Repair> list = repairService.getList();

	    if (list != null)
	        return CommonResult.success(list);
	    else
	        return CommonResult.validateFailed();

    }
	@GetMapping("/list/{userId}")
	public CommonResult getListByAssitantId(@PathVariable("userId") String userId) {

		List<Repair> list = repairService.getListByAssitantId(userId);

		if (list != null)
			return CommonResult.success(list);
		else
			return CommonResult.validateFailed();

	}

	@PostMapping("/add/{userId}")
	public String save(@PathVariable("userId") String userId ,@RequestBody Repair repair){


		repair.setUserId(userId);
		repair.setFixTime(systime.format(date));
		System.out.println("进入到报修单save");
		String result = repairService.save(repair);
		if(result != null){
			return "success";
		}else{
			return "error";
		}
	}
	@PostMapping("/update")
	public String update(@RequestBody Repair fix){

		String result = repairService.updateById(fix);
		System.out.println("进入到报修单update");
		if(result != null){
			return "success";
		}else{
			return "error";
		}
	}



}
