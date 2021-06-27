package com.gxuwz.lab.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxuwz.lab.api.CommonResult;
import com.gxuwz.lab.entry.Dailyjob;
import com.gxuwz.lab.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/15 11:42
 **/
@RestController
@RequestMapping("/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    //根据日期查询
    @GetMapping("/timeSearchLog/{time}")
    public CommonResult getLogByTime(Model model,
                               @PathVariable("time") String time,
                               @RequestParam(value = "pn", defaultValue = "1") int pn,
                               @RequestParam(value = "size", defaultValue = "8") int size) {
        PageHelper.startPage(pn,size);
        List<Dailyjob> systemLogList = dailyService.getLogByDatetime(time);
        PageInfo<Dailyjob> page = new PageInfo<>(systemLogList);
        model.addAttribute("page", page);
        return CommonResult.success(systemLogList);
//		return "systemlog/systemlog-list";
    }

    @GetMapping("/list")
    public CommonResult getList() {

        List<Dailyjob> list = dailyService.getList();

        if (list != null)
            return CommonResult.success(list);
        else
            return CommonResult.validateFailed();

    }

    @GetMapping("/list/{userId}")
    public CommonResult getListByAssitantId(@PathVariable("userId") String userId) {

        List<Dailyjob> list = dailyService.getListByAssitantId(userId);

        if (list != null)
            return CommonResult.success(list);
        else
            return CommonResult.validateFailed();

    }

    @PostMapping("/add/{userId}")
    public String save(@RequestBody Dailyjob daily, @PathVariable("userId") String userId) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat systime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());

        daily.setTime(time.format(date));
        daily.setSystemTime(systime.format(date));
        daily.setUserId(userId);
        String result = dailyService.save(daily);
        if (result != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @PostMapping("/update")
    public String update(@RequestBody Dailyjob dailyjob) {

        System.out.println(dailyjob);
        String result = dailyService.updateById(dailyjob);
        if (result != null) {
            return "success";
        } else {
            return "error";
        }
    }

}
