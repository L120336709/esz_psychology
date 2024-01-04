package com.sundata.edu.api.bean;

public class GradeRequest {

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

    @Override
    public String toString() {
        return "GradeRequest{" +
                "gradeId='" + gradeId + '\'' +
                ", gradeCode='" + gradeCode + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }
}
