package com.sundata.edu.domain;

import javax.persistence.*;
import java.util.List;

/**
 * 用户班级信息
 */
public class UserClassInfo {
    /**
     * 序列
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private String teacherId;

    /**
     * 年级id
     */
    private String gradeId;

    /**
     * 班级id
     */
    private String classId;

    /**
     * 创建人id
     */
    private String createdId;

    /**
     * 创建时间
     */
    private String created;

    /**
     * 老师名字
     */
   private String teacherName;

    /**
     * 年级名字
     */
   private String gradeName;

    /**
     * 班级名字
     */
   private String className;

    /**
     * 年级班级信息
     */
   @Transient
   private List<String> classInfoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getClassInfoList() {
        return classInfoList;
    }

    public void setClassInfoList(List<String> classInfoList) {
        this.classInfoList = classInfoList;
    }
}
