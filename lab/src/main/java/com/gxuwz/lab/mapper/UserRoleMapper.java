package com.gxuwz.lab.mapper;

import com.gxuwz.lab.entry.Recruitment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 实验室技术人数据层
 * 
 * 
 */
@Mapper
@Component
public interface UserRoleMapper {

    public List<Recruitment> getList();

    public void updateById(Recruitment recruitment);

    public void save(Recruitment recruitment);


    public void addUserRole(String userId, Integer roleId);
    void toAssitant(String userId,Integer roleId);

    void updateUserRoleById(Integer roleId,String userId);
}
