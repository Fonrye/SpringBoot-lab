package com.gxuwz.lab.service;

import com.gxuwz.lab.entry.Dailyjob;
import com.gxuwz.lab.entry.Form;
import com.gxuwz.lab.entry.Recruitment;
import com.gxuwz.lab.mapper.DailyMapper;
import com.gxuwz.lab.mapper.FormMapper;
import com.gxuwz.lab.mapper.RecruitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/26 23:16
 **/
@Transactional
@Service("dailyService")
public class DailyService {

    @Autowired
    DailyMapper dailyMapper;

    public List<Dailyjob> getListByAssitantId(String userId) {
       return dailyMapper.getListByAssitantId(userId);
    }


    public List<Dailyjob> getList() {
        return dailyMapper.getList();
    }

    public String save(Dailyjob dailyjob) {
        try{
            dailyMapper.save(dailyjob);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public String updateById(Dailyjob dailyjob) {
        try{
            dailyMapper.updateById(dailyjob);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Dailyjob> getLogByDatetime(String time) {
        return dailyMapper.getLogByDatetime(time);
    }
}
