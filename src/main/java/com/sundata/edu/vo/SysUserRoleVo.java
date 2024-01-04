package com.sundata.edu.vo;
import java.util.Date;
/**
 * 表 sys_user_role
 * 
 * @author 侯鹏
 * @date 2019-04-17 15:22:45
 */
public class SysUserRoleVo extends BaseVo {
    /**
     * 用户角色关系id
     */
	private Integer userRoleId;
    /**
     * 用户id
     */
	private String userId;
    /**
     * 角色id
     */
	private Integer roleId;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 修改时间
     */
	private Date updateTime;

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Integer getUserRoleId()
	{
		return userRoleId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId()
	{
		return roleId;
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

	@Override
    public String toString() {
        return "SysUserRoleVo{" +
        		"userRoleId=" + userRoleId +
        		"userId=" + userId +
        		"roleId=" + roleId +
        		"createTime=" + createTime +
        		"updateTime=" + updateTime +
				"}";
    }
}
