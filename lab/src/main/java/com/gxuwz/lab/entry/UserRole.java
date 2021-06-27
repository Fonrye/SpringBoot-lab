package com.gxuwz.lab.entry;
/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/
public class UserRole {
    private Integer id;
    private String userId;
    private Integer roleId;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public UserRole() {
    }

    public UserRole(Integer id, String userId, Integer roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
