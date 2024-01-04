package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 课程课节列表对象 psy_c_lessons
 * 
 * @author ljg
 * @date 2023-11-09
 */
public class PsyCLessons
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 节名称 */
    @Excel(name = "节名称")
    private String name;

    /** 位置 */
    @Excel(name = "位置")
    private Long position;

    /** 课件列表 */
    @Excel(name = "课件列表")
    private Long unitsid;

    /** 修改时间 */
    @Excel(name = "修改时间")
    private Long gmtModified;

    /** 发布时间 */
    @Excel(name = "发布时间")
    private Long releaseTime;
    private Date createTime;
    private Date updateTime;
    private Long chapersId;

    private String done;

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public Long getChapersId() {
        return chapersId;
    }

    public void setChapersId(Long chapersId) {
        this.chapersId = chapersId;
    }

    @Override
    public String toString() {
        return "PsyCLessons{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", unitsid=" + unitsid +
                ", gmtModified=" + gmtModified +
                ", releaseTime=" + releaseTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", chapersId=" + chapersId +
                ", done='" + done + '\'' +
                '}';
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
    public void setUnitsid(Long unitsid) 
    {
        this.unitsid = unitsid;
    }

    public Long getUnitsid() 
    {
        return unitsid;
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

}
