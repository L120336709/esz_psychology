package com.sundata.edu.vo;

/**
 * 查询条件
 */
public class UserClassInfoVo extends BaseVo {

    /**
     * 老师名字
     */
    private String teacherName;

    /**
     * 班级名字
     */
    private String className;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
