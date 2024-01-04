package com.sundata.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 学习记录表对象 psy_record
 * 
 * @author ljg
 * @date 2023-11-09
 */
public class PsyRecord
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

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Long startTime;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Long endTime;

    /** 本次学习时长（分钟） */
    @Excel(name = "本次学习时长", readConverterExp = "分=钟")
    private String getHour;

    /** 本次学习课时 */
    @Excel(name = "本次学习课时")
    private String getClasshour;

    /**  */
    @Excel(name = "")
    private String state;

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
    public void setStartTime(Long startTime)
    {
        this.startTime = startTime;
    }

    public Long getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Long endTime)
    {
        this.endTime = endTime;
    }

    public Long getEndTime()
    {
        return endTime;
    }
    public void setGetHour(String getHour) 
    {
        this.getHour = getHour;
    }

    public String getGetHour() 
    {
        return getHour;
    }
    public void setGetClasshour(String getClasshour) 
    {
        this.getClasshour = getClasshour;
    }

    public String getGetClasshour() 
    {
        return getClasshour;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("kcId", getKcId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("getHour", getGetHour())
            .append("getClasshour", getGetClasshour())
            .append("state", getState())
            .toString();
    }
}
