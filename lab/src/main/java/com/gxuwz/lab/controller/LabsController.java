package com.gxuwz.lab.controller;

import com.gxuwz.lab.api.CommonResult;
import com.gxuwz.lab.entry.Labs;
import com.gxuwz.lab.entry.Repair;
import com.gxuwz.lab.service.LabsService;
import com.gxuwz.lab.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/labs")
public class LabsController {
	
	@Autowired
	private LabsService labsService;

	@GetMapping("/getList")
	public List<Labs> getList(Labs labs){
		System.out.println("这里是实验室列表的controller");
		List<Labs> list = labsService.getList(labs);
		return list;

	}
//	@GetMapping("/getList")
//		public CommonResult getList() {
//        List<Labs> list = labsService.getList();
//	    if (list != null)
//	        return CommonResult.success(list);
//	    else
//	        return CommonResult.validateFailed();
//    }

	@PostMapping("/save")
	public String save(@RequestBody Labs labs){
		String result = labsService.save(labs);
		if(result != null){
			return "success";
		}else{
			return "error";
		}
	}

	@PostMapping("/update")
	public String update(@RequestBody Labs labs){
		String result = labsService.updateById(labs);
		if(result != null){
			return "success";
		}else{
			return "error";
		}
	}


	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable int id){
		labsService.deleteById(id);
	}



}
