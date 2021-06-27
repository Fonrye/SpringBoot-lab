package com.gxuwz.lab.entry;

/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/

public class Recruitment  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String planName;
     private String required;
     private Integer requireNum;
     private Integer sectionId;
     private String sectionName;
     private Integer labId;
     private String labName;
     private Integer status;



// Constructors


    /** default constructor */
    public Recruitment() {
    }

    public Recruitment(Integer id, String planName, String required, Integer requireNum, Integer sectionId, String sectionName, Integer labId, String labName) {
        this.id = id;
        this.planName = planName;
        this.required = required;
        this.requireNum = requireNum;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.labId = labId;
        this.labName = labName;
    }

    public Recruitment(Integer id, String planName, String required, Integer requireNum, Integer sectionId, String sectionName, Integer labId, String labName, Integer status) {
        this.id = id;
        this.planName = planName;
        this.required = required;
        this.requireNum = requireNum;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.labId = labId;
        this.labName = labName;
        this.status = status;
    }
// Property accessors


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public Integer getRequireNum() {
        return requireNum;
    }

    public void setRequireNum(Integer requireNum) {
        this.requireNum = requireNum;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}