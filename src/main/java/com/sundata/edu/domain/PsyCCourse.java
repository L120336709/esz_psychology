package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 课程表对象 psy_c_course
 * 
 * @author ljg
 * @date 2023-11-09
 */
public class PsyCCourse
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String name;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private Long ctStartTime;

    /** 结束时间 */
    @Excel(name = "结束时间")
    private Long ctEndTime;

    /** 课程封面 */
    @Excel(name = "课程封面")
    private String ctImgUrl;

    /** 教师图片 */
    @Excel(name = "教师图片")
    private String adolargeFaceUrl;

    /** 教师介绍 */
    @Excel(name = "教师介绍")
    private String adolectorDesc;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String adorealName;

    /** 当前学期id */
    @Excel(name = "当前学期id")
    private Long ctId;

    /** 教师昵称 */
    @Excel(name = "教师昵称")
    private String adoname;

    /** 老师类型 （0：学期主讲师 1：学期讲师 2：助教 */
    @Excel(name = "老师类型 ", readConverterExp = "老师类型 （0：学期主讲师 1：学期讲师 2：助教")
    private String adotype;

    /** 教师openid */
    @Excel(name = "教师openid")
    private String adoopenUid;
    private Date createTime;
    private Date updateTime;

    private String  allClasshour;

    private String  jsonContent;
    private String  description;


    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAllClasshour() {
        return allClasshour;
    }

    public void setAllClasshour(String allClasshour) {
        this.allClasshour = allClasshour;
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
    public void setCtStartTime(Long ctStartTime)
    {
        this.ctStartTime = ctStartTime;
    }

    public Long getCtStartTime()
    {
        return ctStartTime;
    }
    public void setCtEndTime(Long ctEndTime)
    {
        this.ctEndTime = ctEndTime;
    }

    public Long getCtEndTime()
    {
        return ctEndTime;
    }
    public void setCtImgUrl(String ctImgUrl)
    {
        this.ctImgUrl = ctImgUrl;
    }

    public String getCtImgUrl()
    {
        return ctImgUrl;
    }
    public void setAdolargeFaceUrl(String AdolargeFaceUrl)
    {
        this.adolargeFaceUrl = AdolargeFaceUrl;
    }

    public String getAdolargeFaceUrl()
    {
        return adolargeFaceUrl;
    }
    public void setAdolectorDesc(String AdolectorDesc)
    {
        this.adolectorDesc = AdolectorDesc;
    }

    public String getAdolectorDesc()
    {
        return adolectorDesc;
    }
    public void setAdorealName(String AdorealName)
    {
        this.adorealName = AdorealName;
    }

    public String getAdorealName()
    {
        return adorealName;
    }
    public void setCtId(Long ctId)
    {
        this.ctId = ctId;
    }

    public Long getCtId()
    {
        return ctId;
    }
    public void setAdoname(String Adoname)
    {
        this.adoname = Adoname;
    }

    public String getAdoname()
    {
        return adoname;
    }
    public void setAdotype(String Adotype)
    {
        this.adotype = Adotype;
    }

    public String getAdotype()
    {
        return adotype;
    }
    public void setAdoopenUid(String AdoopenUid)
    {
        this.adoopenUid = AdoopenUid;
    }

    public String getAdoopenUid()
    {
        return adoopenUid;
    }

    @Override
    public String toString() {
        return "PsyCCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ctStartTime=" + ctStartTime +
                ", ctEndTime=" + ctEndTime +
                ", ctImgUrl='" + ctImgUrl + '\'' +
                ", adolargeFaceUrl='" + adolargeFaceUrl + '\'' +
                ", adolectorDesc='" + adolectorDesc + '\'' +
                ", adorealName='" + adorealName + '\'' +
                ", ctId=" + ctId +
                ", adoname='" + adoname + '\'' +
                ", adotype='" + adotype + '\'' +
                ", adoopenUid='" + adoopenUid + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", allClasshour='" + allClasshour + '\'' +
                ", jsonContent='" + jsonContent + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
