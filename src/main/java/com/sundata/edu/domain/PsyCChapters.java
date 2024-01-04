package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;

import java.util.Date;

/**
 * 课程章节列表对象 psy_c_chapters
 * 
 * @author ljg
 * @date 2023-11-09
 */
public class PsyCChapters
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 章名称 */
    @Excel(name = "章名称")
    private String name;

    /** 位置 */
    @Excel(name = "位置")
    private Long position;

    /** 节列表id */
    @Excel(name = "节列表id")
    private Long lessonsid;

    /** 随堂测验 */
    @Excel(name = "随堂测验")
    private Long quizid;

    /** 课堂作业 */
    @Excel(name = "课堂作业")
    private Long homeworksid;

    /** 修改时间 */
    @Excel(name = "修改时间")
    private Long gmtModified;

    /** 发布时间 */
    @Excel(name = "发布时间")
    private Long releaseTime;


    private Date createTime;
    private Date updateTime;


    private Long structureId;

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPosition(Long position) 
    {
        this.position = position;
    }

    public Long getPosition() 
    {
        return position;
    }
    public void setLessonsid(Long lessonsid) 
    {
        this.lessonsid = lessonsid;
    }

    public Long getLessonsid() 
    {
        return lessonsid;
    }
    public void setQuizid(Long quizid) 
    {
        this.quizid = quizid;
    }

    public Long getQuizid() 
    {
        return quizid;
    }
    public void setHomeworksid(Long homeworksid) 
    {
        this.homeworksid = homeworksid;
    }

    public Long getHomeworksid() 
    {
        return homeworksid;
    }
    public void setGmtModified(Long gmtModified) 
    {
        this.gmtModified = gmtModified;
    }

    public Long getGmtModified() 
    {
        return gmtModified;
    }
    public void setReleaseTime(Long releaseTime) 
    {
        this.releaseTime = releaseTime;
    }

    public Long getReleaseTime() 
    {
        return releaseTime;
    }

    @Override
    public String toString() {
        return "PsyCChapters{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", lessonsid=" + lessonsid +
                ", quizid=" + quizid +
                ", homeworksid=" + homeworksid +
                ", gmtModified=" + gmtModified +
                ", releaseTime=" + releaseTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", structureid=" + structureId +
                '}';
    }
}
