package com.sundata.edu.receive.bean;

public class ClassInfoData {
    private Integer id;
    private String classId;//班级Id，唯一标志
    private String classCode;//班级代码 ，如1班 对应01
    private String className;//班级名称，内置班级名称
    private String classNickName;//班级别名，如火箭班
    private String classLevel;//届，比如2018级
    private String gradeId;//所属年级Id
    private String gradeCode;//所属年级代码
    private String gradeName;//所属年级名称
    private String schoolId;//学校id
    private String status;//状态 1=可用 0=不可用
    private String studyYear;//学年 2018-2019

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNickName() {
        return classNickName;
    }

    public void setClassNickName(String classNickName) {
        this.classNickName = classNickName;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    @Override
    public String toString() {
        return "ClassInfoData{" +
                "id=" + id +
                ", classId='" + classId + '\'' +
                ", classCode='" + classCode + '\'' +
                ", className='" + className + '\'' +
                ", classNickName='" + classNickName + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", gradeCode='" + gradeCode + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", status=" + status +
                '}';
    }
}
