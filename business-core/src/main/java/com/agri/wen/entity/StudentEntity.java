package com.agri.wen.entity;


import javax.persistence.Table;
import java.io.Serializable;

@Table(name="student_score")
public class StudentEntity implements Serializable {

    /***
     *注解id
     */
    private int id;

    /***
     * 数学成绩
     */
    private  String math;

    /***
     * 英语成绩
     */
    private String english;

    /***
     * 学生姓名
     */
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
