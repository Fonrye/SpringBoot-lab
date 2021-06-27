package com.gxuwz.lab.service;

import com.gxuwz.lab.entry.Form;
import com.gxuwz.lab.entry.Recruitment;
import com.gxuwz.lab.entry.User;
import com.gxuwz.lab.mapper.FormMapper;
import com.gxuwz.lab.mapper.RecruitMapper;
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
 * @date 2021/6/15 11:53
 **/
@Transactional
@Service("recruitService")
public class RecruitService {
	@Autowired
	RecruitMapper recruitMapper;
    @Autowired
    FormMapper formMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    public List<Recruitment> getList() {
       return recruitMapper.getList();
    }
    public List<Recruitment> stuGetList() {
        return recruitMapper.stuGetList();
    }
    public void deleteById(Integer id) {
        recruitMapper.deleteById(id);
    }
    
    public String updateById(Recruitment recruitment) {
        try{
            recruitMapper.updateById(recruitment);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
        }

       return null;
    }

    public String save(Recruitment recruitment) {
        try{
            recruitMapper.save(recruitment);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }



    public String check(Integer num,Integer id) {
        try{
            recruitMapper.checkupdateById(num,id);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
