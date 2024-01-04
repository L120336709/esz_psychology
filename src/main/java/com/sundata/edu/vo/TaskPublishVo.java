package com.sundata.edu.vo;
import java.util.Date;
/**
 * 表 task_publish
 * 
 * @author TAOTAO
 * @date 2020-10-13 18:21:44
 */
public class TaskPublishVo extends BaseVo {
    /**
     * 采集任务发布(州级管理员)
     */
	private Integer id;
    /**
     * 机构ID
     */
	private String orgId;
	/**
	 * 标题
	 */
	private String title;
    /**
     * 开始时间
     */
	private Date startTime;
    /**
     * 结束时间
     */
	private Date endTime;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 修改时间
     */
	private Date updateTime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgId()
	{
		return orgId;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime()
	{
		return updateTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
