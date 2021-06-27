package com.gxuwz.lab.entry;

/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/
public class Form  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer planId;
     private String planName;
     private String userId;
     private String userName;
     private Integer status;


    // Constructors

    /** default constructor */
    public Form() {
    }

    

    public Form(Integer planId, String userId, Integer status) {
        this.planId = planId;
        this.userId = userId;
        this.status = status;
    }
    /** full constructor */
    public Form(Integer id, Integer planId, String planName, String userId, String userName, Integer status) {
        this.id = id;
        this.planId = planId;
        this.planName = planName;
        this.userId = userId;
        this.userName = userName;
        this.status = status;
    }
// Property accessors


    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }



    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   








}