package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 课程视频信息表对象 psy_c_videosign
 * 
 * @author ljg
 * @date 2023-11-09
 */
public class PsyCVideosign
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long videoId;

    /** 视频名称 */
    @Excel(name = "视频名称")
    private String name;

    /** 0正常,-1不存在,-2未发布,2转码失败 */
    @Excel(name = "0正常,-1不存在,-2未发布,2转码失败")
    private Long status;

    /** 视频时长 */
    @Excel(name = "视频时长")
    private Long duration;

    /** 视频封面 */
    @Excel(name = "视频封面")
    private String videoImgUrl;

    /** 签名 */
    @Excel(name = "签名")
    private String signature;
    private Date createTime;
    private Date updateTime;
    private Long unitsId;

    public Long getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(Long unitsId) {
        this.unitsId = unitsId;
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
    public void setVideoId(Long videoId) 
    {
        this.videoId = videoId;
    }

    public Long getVideoId() 
    {
        return videoId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }
    public void setVideoImgUrl(String videoImgUrl) 
    {
        this.videoImgUrl = videoImgUrl;
    }

    public String getVideoImgUrl() 
    {
        return videoImgUrl;
    }
    public void setSignature(String signature) 
    {
        this.signature = signature;
    }

    public String getSignature() 
    {
        return signature;
    }

    @Override
    public String toString() {
        return "PsyCVideosign{" +
                "videoId=" + videoId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", duration=" + duration +
                ", videoImgUrl='" + videoImgUrl + '\'' +
                ", signature='" + signature + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", unitsId=" + unitsId +
                '}';
    }
}
