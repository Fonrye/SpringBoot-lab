package com.gxuwz.lab.mapper;

import com.gxuwz.lab.entry.Labs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;



@Mapper
@Component
public interface LabsMapper {

    List<Labs> getList(Labs labs);

    void save(Labs labs);

    void updateById(Labs labs);

    void deleteLabsById(int labId);

}
