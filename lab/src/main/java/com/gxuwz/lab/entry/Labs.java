package com.gxuwz.lab.entry;


/**
 * @author Fonrye
 * @version 1.0
 * @creat by IntelliJ IDEA
 * @date 2021/6/4 21:16
 **/

public class Labs implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String labName;
     private String labType;
     private String labId;

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getLabType() {
        return labType;
    }

    public void setLabType(String labType) {
        this.labType = labType;
    }

    public Labs() {
    }

    public Labs(Integer id, String labName, String labType, String labId) {
        this.id = id;
        this.labName = labName;
        this.labType = labType;
        this.labId = labId;
    }

    @Override
    public String toString() {
        return "Labs{" +
                "id=" + id +
                ", labName='" + labName + '\'' +
                ", labType='" + labType + '\'' +
                ", labId='" + labId + '\'' +
                '}';
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }









}