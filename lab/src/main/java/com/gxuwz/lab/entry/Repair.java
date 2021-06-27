package com.gxuwz.lab.entry;


/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/

public class Repair  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String eName;
     private String descrip;
     private String fixTime;
     private String userId;
     private String userName;

    // Constructors

    /** default constructor */
    public Repair() {
    }

    public Repair(Integer id, String eName, String descrip, String fixTime, String userId, String userName) {
        this.id = id;
        this.eName = eName;
        this.descrip = descrip;
        this.fixTime = fixTime;
        this.userId = userId;
        this.userName = userName;
    }



// Property accessors

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

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getFixTime() {
        return fixTime;
    }

    public void setFixTime(String fixTime) {
        this.fixTime = fixTime;
    }
}