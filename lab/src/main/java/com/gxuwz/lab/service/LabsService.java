package com.gxuwz.lab.service;

import com.gxuwz.lab.entry.Labs;
import com.gxuwz.lab.entry.Repair;
import com.gxuwz.lab.mapper.LabsMapper;
import com.gxuwz.lab.mapper.RepairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/8 11:16
 **/
@Transactional
@Service("labsService")
public class LabsService {

    @Autowired
    LabsMapper labsMapper;

    public List<Labs> getList(Labs labs) {
        return labsMapper.getList(labs);
    }

    public String updateById(Labs labs) {
        try{
            labsMapper.updateById(labs);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String save(Labs labs) {
        try{
            labsMapper.save(labs);
            System.out.println("进入到保存实验室方法");
            return "success";
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public void deleteById(int id) {
        labsMapper.deleteLabsById(id);
    }
}
