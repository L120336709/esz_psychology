package com.sundata.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 课程章节学习进度表对象 psy_userjd
 * 
 * @author ljg
 * @date 2023-11-14
 */
public class PsyUserjd
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 课程id */
    @Excel(name = "课程id")
    private Long kcId;

    /** 章节id */
    @Excel(name = "章节id")
    private Long chapterId;

    /** 节Id */
    @Excel(name = "节Id")
    private Long lessonsId;

    /** 课件id */
    @Excel(name = "课件id")
    private Long unitsId;

    /** 视频id或者课件id */
    @Excel(name = "视频id或者课件id")
    private Long videoId;

    /** 已观看时间秒 */
    @Excel(name = "已观看时间秒")
    private Long timlong;

    /** 是否完成本课时的学习 */
    @Excel(name = "是否完成本课时的学习")
    private String done;

    /** 完成学习时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成学习时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date doneTime;
    private Date createTime;



    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setKcId(Long kcId) 
    {
        this.kcId = kcId;
    }

    public Long getKcId() 
    {
        return kcId;
    }
    public void setChapterId(Long chapterId) 
    {
        this.chapterId = chapterId;
    }

    public Long getChapterId() 
    {
        return chapterId;
    }
    public void setLessonsId(Long lessonsId) 
    {
        this.lessonsId = lessonsId;
    }

    public Long getLessonsId() 
    {
        return lessonsId;
    }
    public void setUnitsId(Long unitsId) 
    {
        this.unitsId = unitsId;
    }

    public Long getUnitsId() 
    {
        return unitsId;
    }
    public void setVideoId(Long videoId) 
    {
        this.videoId = videoId;
    }

    public Long getVideoId() 
    {
        return videoId;
    }
    public void setTimlong(Long timlong) 
    {
        this.timlong = timlong;
    }

    public Long getTimlong() 
    {
        return timlong;
    }
    public void setDone(String done) 
    {
        this.done = done;
    }

    public String getDone() 
    {
        return done;
    }
    public void setDoneTime(Date doneTime) 
    {
        this.doneTime = doneTime;
    }

    public Date getDoneTime() 
    {
        return doneTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PsyUserjd{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", kcId=" + kcId +
                ", chapterId=" + chapterId +
                ", lessonsId=" + lessonsId +
                ", unitsId=" + unitsId +
                ", videoId=" + videoId +
                ", timlong=" + timlong +
                ", done='" + done + '\'' +
                ", doneTime=" + doneTime +
                ", createTime=" + createTime +
                '}';
    }
}
