package com.gxuwz.lab.entry;



/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/

public class Menu  implements java.io.Serializable {


    // Fields    

     private Integer menuId;
     private String menuName;
     private String menuUrl;
     private Integer roleId;


    // Constructors

    /** default constructor */
    public Menu() {
    }

    
    /** full constructor */
    public Menu(String menuName, String menuUrl, Integer roleId) {
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.roleId = roleId;
    }

   
    // Property accessors

    public Integer getMenuId() {
        return this.menuId;
    }
    
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return this.menuName;
    }
    
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return this.menuUrl;
    }
    
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
   








}