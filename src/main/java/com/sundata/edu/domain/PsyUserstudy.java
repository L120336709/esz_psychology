package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 用户课程学习表对象 psy_userstudy
 * 
 * @author ljg
 * @date 2023-11-09
 */
public class PsyUserstudy
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 用户id */
//    @Excel(name = "用户id")
    private String userId;

    private String userName;
    /** 课程id */
//    @Excel(name = "课程id")
    private Long kcId;
    /**  */
    @Excel(name = "区域")
    private String state;

    /** 课程总学时 */
    @Excel(name = "单位")
    private String allClasshour;

    /** 课程名称 */
    @Excel(name = "姓名")
    private String kcName;


    /** 已累计学习课时 */
    @Excel(name = "学习课时")
    private String getClasshour;

    /** 已累计学习时长（课时） */
    @Excel(name = "学习时长（秒）")
    private String getHour;

    /** 学习进度百分比 */
//    @Excel(name = "学习进度百分比")
    private String schedule;

    /** 是否完成本课程的学习0/1 */
    @Excel(name = "完成课程")
    private String done;



    private Date createTime;
    private Date updateTime;

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
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setKcId(Long kcId)
    {
        this.kcId = kcId;
    }

    public Long getKcId() 
    {
        return kcId;
    }
    public void setKcName(String kcName) 
    {
        this.kcName = kcName;
    }

    public String getKcName() 
    {
        return kcName;
    }
    public void setAllClasshour(String allClasshour) 
    {
        this.allClasshour = allClasshour;
    }

    public String getAllClasshour() 
    {
        return allClasshour;
    }
    public void setGetClasshour(String getClasshour) 
    {
        this.getClasshour = getClasshour;
    }

    public String getGetClasshour() 
    {
        return getClasshour;
    }
    public void setGetHour(String getHour) 
    {
        this.getHour = getHour;
    }

    public String getGetHour() 
    {
        return getHour;
    }
    public void setSchedule(String schedule) 
    {
        this.schedule = schedule;
    }

    public String getSchedule() 
    {
        return schedule;
    }
    public void setDone(String done) 
    {
        this.done = done;
    }

    public String getDone() 
    {
        return done;
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
        return "PsyUserstudy{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", kcId=" + kcId +
                ", kcName='" + kcName + '\'' +
                ", allClasshour='" + allClasshour + '\'' +
                ", getClasshour='" + getClasshour + '\'' +
                ", getHour='" + getHour + '\'' +
                ", schedule='" + schedule + '\'' +
                ", done='" + done + '\'' +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
