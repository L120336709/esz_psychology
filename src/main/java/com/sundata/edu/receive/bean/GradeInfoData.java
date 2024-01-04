package com.sundata.edu.receive.bean;

public class GradeInfoData {

    //id 主键自增长
    private Integer id;
    /***
     * 年级id
     */
    private String gradeId;

    /***
     * 年级编码
     */
    private String gradeCode;
    /***
     * 年级名称
     */
    private String gradeName;

    /**
     * 学校Id
     */
    private String schoolId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
