package com.gxuwz.lab.mapper;

import com.gxuwz.lab.entry.Recruitment;
import com.gxuwz.lab.entry.User;
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
public interface RecruitMapper {

    /**
     * 获取全部招聘列表
     */
    public List<Recruitment> getList();

    public void deleteById(Integer id);

    public void updateById(Recruitment recruitment);

    public void save(Recruitment recruitment);

    Recruitment getRecruitById(Integer recruitmentId);

    void requestNum(int requireNum, Integer id);

    List<Recruitment> stuGetList();

    void checkupdateById(Integer status, Integer id);
}
