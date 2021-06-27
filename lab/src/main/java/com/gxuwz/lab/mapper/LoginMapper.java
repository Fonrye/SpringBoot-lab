package com.gxuwz.lab.mapper;

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
public interface LoginMapper{
    /**
     * 根据条件分页查询实验室技术人员列表
     * 
     * @param artisan 实验室技术人员
     * @return 实验室技术人员信息集合信息
     */
    public List<User> selectUserList(User user);

    public User addUser(User artisan);
    
    public int deleteUserByid(int userid);
    
    public User updateUserByid(User user);
    
    public int getUserByid(int userid);
}
