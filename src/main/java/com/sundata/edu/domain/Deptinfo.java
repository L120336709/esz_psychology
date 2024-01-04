package com.sundata.edu.domain;

import javax.persistence.*;

/**
 * 表 deptinfo
 *
 * @author whj
 * @date 2021-10-19 23:23:37
 */
@Table(name = "`deptinfo`")
public class Deptinfo {
	/**
	 *
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`id`")
	private Integer id;
	/**
	 * 部门ID
	 */
	@Column(name = "`deptId`")
	private String deptid;
	/**
	 * 部门名称
	 */
	@Column(name = "`deptName`")
	private String deptname;
	/**
	 * 排序序号
	 */
	@Column(name = "`sort`")
	private Integer sort;
	/**
	 * 上级部门ID
	 */
	@Column(name = "`parentId`")
	private String parentid;
	/**
	 * 所属机构（教育局或学校） Id
	 */
	@Column(name = "`orgId`")
	private String orgid;
	/**
	 * 部门负责人
	 */
	@Column(name = "`leader`")
	private String leader;
	/**
	 * 联系电话
	 */
	@Column(name = "`phone`")
	private String phone;
	/**
	 * 邮箱
	 */
	@Column(name = "`email`")
	private String email;
	/**
	 * 部门状态， 1 表示正常 0 表示已禁用
	 */
	@Column(name = "`status`")
	private String status;
	/**
	 * 0 : 正常 1 : 删除
	 */
	@Column(name = "`isDelete`")
	private Integer isdelete;
	/**
	 * 创建时间
	 */
	@Column(name = "`createTime`")
	private Integer createtime;
	/**
	 * 修改时间
	 */
	@Column(name = "`updateTime`")
	private Integer updatetime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return sort;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getLeader() {
		return leader;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}

	public Integer getCreatetime() {
		return createtime;
	}

	public void setUpdatetime(Integer updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getUpdatetime() {
		return updatetime;
	}

	@Override
    public String toString() {
        return "Deptinfo{" +
        		"id=" + id +
        		"deptid=" + deptid +
        		"deptname=" + deptname +
        		"sort=" + sort +
        		"parentid=" + parentid +
        		"orgid=" + orgid +
        		"leader=" + leader +
        		"phone=" + phone +
        		"email=" + email +
        		"status=" + status +
        		"isdelete=" + isdelete +
        		"createtime=" + createtime +
        		"updatetime=" + updatetime +
				"}";
    }
}
