package com.sundata.edu.vo;

public class SchedulesappVo extends BaseVo{
    /**
     * 任课id
     */
    private Long teachid;
    /**
     * 学段
     */
    private String phasestudy;
    /**
     * 科目id
     */
    private String subjectid;
    /**
     * 科目
     */
    private String subject;
    /**
     * 老师id
     */
    private String userid;
    /**
     * 班级id
     */
    private String classid;

    /**
     * 班级id
     */
    private String classject;
    /**
     * 年级id
     */
    private String gradeid;

    public Long getTeachid() {
        return teachid;
    }

    public void setTeachid(Long teachid) {
        this.teachid = teachid;
    }

    public String getPhasestudy() {
        return phasestudy;
    }

    public void setPhasestudy(String phasestudy) {
        this.phasestudy = phasestudy;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getClassject() {
        return classject;
    }

    public void setClassject(String classject) {
        this.classject = classject;
    }

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid;
    }

    @Override
    public String toString() {
        return "SchedulesappVo{" +
                "teachid=" + teachid +
                ", phasestudy='" + phasestudy + '\'' +
                ", subjectid='" + subjectid + '\'' +
                ", subject='" + subject + '\'' +
                ", userid='" + userid + '\'' +
                ", classid='" + classid + '\'' +
                ", classject='" + classject + '\'' +
                ", gradeid='" + gradeid + '\'' +
                '}';
    }
}
