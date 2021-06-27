package com.gxuwz.lab.entry;

/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/

public class Department  implements java.io.Serializable {


    // Fields    

     private String depId;
     private String depName;


    // Constructors

    /** default constructor */
    public Department() {
    }

	/** minimal constructor */
    public Department(String depId) {
        this.depId = depId;
    }
    
    /** full constructor */
    public Department(String depId, String depName) {
        this.depId = depId;
        this.depName = depName;
    }

   
    // Property accessors

    public String getDepId() {
        return this.depId;
    }
    
    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return this.depName;
    }
    
    public void setDepName(String depName) {
        this.depName = depName;
    }
   








}