package com.gxuwz.lab.mapper;

import com.gxuwz.lab.entry.User;
import com.gxuwz.lab.entry.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 实验室技术人数据层
 */
@Mapper
@Component
public interface UserMapper {
    /**
     * 根据条件分页查询实验室技术人员列表
     *
     * @param artisan 实验室技术人员
     * @return 实验室技术人员信息集合信息
     */
    public List<User> selectUserList(User user);

    List<User> selectUserList2(User user);

    public void addUser2(User user);

    public void addUser(User user);


    public int deleteUserByid(int userid);


    public User updateUserByid(User user);


    public int getUserByid(int userid);

    public User checkLogin(String userId, String pwd);

    public UserRole checkUserRole(String userId);

    void deleteUserById(String userId);

    void updateById(User user);


}
