package com.gxuwz.lab.entry;



/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/

public class Section  implements java.io.Serializable {


    // Fields    

     private Integer sectionId;
     private String sectionName;
     private Integer laboratoryId;


    // Constructors

    /** default constructor */
    public Section() {
    }

    
    /** full constructor */
    public Section(String sectionName, Integer laboratoryId) {
        this.sectionName = sectionName;
        this.laboratoryId = laboratoryId;
    }

   
    // Property accessors

    public Integer getSectionId() {
        return this.sectionId;
    }
    
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return this.sectionName;
    }
    
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getLaboratoryId() {
        return this.laboratoryId;
    }
    
    public void setLaboratoryId(Integer laboratoryId) {
        this.laboratoryId = laboratoryId;
    }
   








}