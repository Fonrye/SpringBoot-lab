package com.gxuwz.lab.entry;



/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/

public class Role  implements java.io.Serializable {


    // Fields    

     private Integer roleId;
     private String roleName;


    // Constructors

    /** default constructor */
    public Role() {
    }

	/** minimal constructor */
    public Role(Integer roleId) {
        this.roleId = roleId;
    }
    
    /** full constructor */
    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }


    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
   



}