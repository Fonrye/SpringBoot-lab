package com.gxuwz.lab.mapper;

import com.gxuwz.lab.entry.Dailyjob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 实验室技术人数据层
 */
@Mapper
@Component
public interface DailyMapper {

    /**
     * 获取全部招聘列表
     */


    List<Dailyjob> getListByAssitantId(String userId);

    List<Dailyjob> getList();

    void save(Dailyjob dailyjob);

    void updateById(Dailyjob dailyjob);

    //    查询日期
    List<Dailyjob> getLogByDatetime(String time);

}
