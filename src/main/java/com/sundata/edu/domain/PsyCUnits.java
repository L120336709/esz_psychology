package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 课程课件列表对象 psy_c_units
 * 
 * @author ljg
 * @date 2023-11-09
 */
public class PsyCUnits
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 课件名称 */
    @Excel(name = "课件名称")
    private String name;

    /** 位置 */
    @Excel(name = "位置")
    private Long position;

    /** 课件类型: 1视频、3文档（PDF）、4富文本、5随堂测验、6讨论、7直播 */
    @Excel(name = "课件类型: 1视频、3文档", readConverterExp = "P=DF")
    private Long contentType;

    /** 视频、PDF:支持后台设置权限，默认不允许下载，是返回播放地址；也可以设置允许下载，返回下载地址。0其它类型是访问链接 */
    @Excel(name = "视频、PDF:支持后台设置权限，默认不允许下载，是返回播放地址；也可以设置允许下载，返回下载地址。0其它类型是访问链接")
    private String contentUrl;

    /** 视频信息 */
    @Excel(name = "视频信息")
    private Long videoSignId;

    /** 修改时间 */
    @Excel(name = "修改时间")
    private Long gmtModified;
    private Date createTime;
    private Date updateTime;

    private Long lessonsId;

    public Long getLessonsId() {
        return lessonsId;
    }

    public void setLessonsId(Long lessonsId) {
        this.lessonsId = lessonsId;
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
    public void setContentType(Long contentType) 
    {
        this.contentType = contentType;
    }

    public Long getContentType() 
    {
        return contentType;
    }
    public void setContentUrl(String contentUrl) 
    {
        this.contentUrl = contentUrl;
    }

    public String getContentUrl() 
    {
        return contentUrl;
    }
    public void setVideoSignId(Long videoSignId) 
    {
        this.videoSignId = videoSignId;
    }

    public Long getVideoSignId() 
    {
        return videoSignId;
    }
    public void setGmtModified(Long gmtModified) 
    {
        this.gmtModified = gmtModified;
    }

    public Long getGmtModified() 
    {
        return gmtModified;
    }

    @Override
    public String toString() {
        return "PsyCUnits{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", contentType=" + contentType +
                ", contentUrl='" + contentUrl + '\'' +
                ", videoSignId=" + videoSignId +
                ", gmtModified=" + gmtModified +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", lessonsId=" + lessonsId +
                '}';
    }
}
