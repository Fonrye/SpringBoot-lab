package com.gxuwz.lab.mapper;

import com.gxuwz.lab.entry.Form;
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
public interface FormMapper {

    /**
     * 获取全部招聘列表
     */


    public void signup(Integer planId, String userId);

    public Form isExist(Integer planId, String userId);

    List<Form> getReviewByRecruitId(Integer planId);

    void interview(Integer planId, String userId, Integer status);
}
