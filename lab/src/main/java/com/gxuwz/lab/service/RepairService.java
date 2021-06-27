package com.gxuwz.lab.service;

import com.gxuwz.lab.entry.Repair;
import com.gxuwz.lab.mapper.RepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/20 22:54
 **/
@Transactional
@Service("repairService")
public class RepairService {

    @Autowired
    RepairMapper repairMapper;

    public List<Repair> getListByAssitantId(String userId) {
       return repairMapper.getListByAssitantId(userId);
    }


    public List<Repair> getList() {
        return repairMapper.getList();
    }

    public String updateById(Repair repair) {
        try{
            repairMapper.updateById(repair);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public String save(Repair repair) {
        try{
            repairMapper.save(repair);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
