package com.sundata.edu.domain;

import javax.persistence.*;
import java.util.Date;
/**
 * 字段类型表 sys_dict_type
 *
 * @author whj
 * @date 2019-04-17 16:21:00
 */
@Table(name = "`sys_dict_type`")
public class SysDictType {
	/**
	 * 字典主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`type_id`")
	private Integer typeId;
	/**
	 * 字典名称
	 */
	@Column(name = "`type_name`")
	private String typeName;
	/**
	 * 字典类型
	 */
	@Column(name = "`type_code`")
	private String typeCode;
	/**
	 * 状态（1正常 0停用）
	 */
	@Column(name = "`status`")
	private Integer status;
	/**
	 * 备注
	 */
	@Column(name = "`remark`")
	private String remark;
	/**
	 * 创建时间
	 */
	@Column(name = "`created`")
	private Date created;
	/**
	 * 修改时间
	 */
	@Column(name = "`updated`")
	private Date updated;

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getUpdated() {
		return updated;
	}

	@Override
    public String toString() {
        return "SysDictType{" +
        		"typeId=" + typeId +
        		"typeName=" + typeName +
        		"typeCode=" + typeCode +
        		"status=" + status +
        		"remark=" + remark +
        		"created=" + created +
        		"updated=" + updated +
				"}";
    }
}
