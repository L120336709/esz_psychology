package com.sundata.edu.vo;

/**
 * 查询条件
 */
public class OverallVo extends BaseVo {

    /**
     * 学生名字
     */
    private String studentName;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 班级名字
     */
    private String className;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * id
     */
    private String studentId;

    /**
     * 年级id
     */
    private String gradeId;

    /**
     * 班级id
     */
    private String classId;
    /**
     * 是否包含子集(1包含子集 2不包含子集，只有自己)
     */
    private Integer includeSubset;

    private String orgId;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public Integer getIncludeSubset() {
        return includeSubset;
    }

    public void setIncludeSubset(Integer includeSubset) {
        this.includeSubset = includeSubset;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
