package com.sundata.edu.api.bean;

public class ClassRequest {
    /**
     * 班级id
     */
    private String classId;
    /**
     * 班级code
     */
    private String classCode;
    /**
     * 班级名
     */
    private String className;

    /***
     * 2019级
     */
    private String classLevel;
    /**
     * 班级别名
     */
    private String classNickname;

    /**
     * 年级id
     */
    private String gradeId;

    /**
     * 年级code
     */
    private String gradeCode;

    /**
     * 年级名
     */
    private String gradeName;

    /**
     * 学校id
     */
    private Integer schoolId;

    /***
     * 状态 1=可用 0不可用
     */
    private String status;

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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
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

    public String getClassNickname() {
        return classNickname;
    }

    public void setClassNickname(String classNickname) {
        this.classNickname = classNickname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    @Override
    public String toString() {
        return "ClassRequest{" +
                "classId='" + classId + '\'' +
                ", classCode='" + classCode + '\'' +
                ", className='" + className + '\'' +
                ", classNickname='" + classNickname + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", gradeCode='" + gradeCode + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", schoolId=" + schoolId +
                ", status='" + status + '\'' +
                '}';
    }
}
