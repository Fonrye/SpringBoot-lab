package com.gxuwz.lab.mapper;

import com.gxuwz.lab.entry.Dailyjob;
import com.gxuwz.lab.entry.Repair;
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
public interface RepairMapper {

    /**
     * 获取全部招聘列表
     */


    List<Repair> getListByAssitantId(String userId);

    List<Repair> getList();

    void save(Repair repair);

    void updateById(Repair repair);
}
