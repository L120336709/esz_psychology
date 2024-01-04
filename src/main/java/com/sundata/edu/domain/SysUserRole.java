package com.sundata.edu.domain;

import javax.persistence.*;
import java.util.Date;
/**
 * 表 sys_user_role
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
@Table(name = "`sys_user_role`")
public class SysUserRole {
	/**
	 * 用户角色关系id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`user_role_id`")
	private Integer userRoleId;
	/**
	 * 用户id
	 */
	@Column(name = "`user_id`")
	private String userId;
	/**
	 * 角色id
	 */
	@Column(name = "`role_id`")
	private Integer roleId;
	/**
	 * 创建时间
	 */
	@Column(name = "`create_time`")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@Column(name = "`update_time`")
	private Date updateTime;

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	@Override
    public String toString() {
        return "SysUserRole{" +
        		"userRoleId=" + userRoleId +
        		"userId=" + userId +
        		"roleId=" + roleId +
        		"createTime=" + createTime +
        		"updateTime=" + updateTime +
				"}";
    }
}
