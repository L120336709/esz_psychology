package com.sundata.edu.vo;

public class SubjectsteaerappVo extends BaseVo{
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户（教师）
     */
    private String userid;
    /**
     * 科目
     */
    private String subject;
    /**
     * 科目id
     */
    private String subjectid;
    /**
     * 学段
     */
    private String phasestudy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getPhasestudy() {
        return phasestudy;
    }

    public void setPhasestudy(String phasestudy) {
        this.phasestudy = phasestudy;
    }

    @Override
    public String toString() {
        return "SubjectsteaerappVo{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", subject='" + subject + '\'' +
                ", subjectid='" + subjectid + '\'' +
                ", phasestudy='" + phasestudy + '\'' +
                '}';
    }
}
