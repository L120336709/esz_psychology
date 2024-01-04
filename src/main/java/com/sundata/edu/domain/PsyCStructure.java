package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 课程结构表对象 psy_c_structure
 * 
 * @author ljg
 * @date 2023-11-09
 */
public class PsyCStructure
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 章节列表 */
    @Excel(name = "章节列表")
    private Long chaptersid;

    /** 学期考试 */
    @Excel(name = "学期考试")
    private Long examsid;
    private Date createTime;
    private Date updateTime;

    private Long courseId;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setChaptersid(Long chaptersid) 
    {
        this.chaptersid = chaptersid;
    }

    public Long getChaptersid() 
    {
        return chaptersid;
    }
    public void setExamsid(Long examsid) 
    {
        this.examsid = examsid;
    }

    public Long getExamsid() 
    {
        return examsid;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "PsyCStructure{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", chaptersid=" + chaptersid +
                ", examsid=" + examsid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", courseId=" + courseId +
                '}';
    }
}
