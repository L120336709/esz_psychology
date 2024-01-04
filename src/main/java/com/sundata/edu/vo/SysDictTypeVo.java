package com.sundata.edu.vo;
import java.util.Date;
/**
 * 字段类型表 sys_dict_type
 * 
 * @author 侯鹏
 * @date 2019-04-17 16:21:00
 */
public class SysDictTypeVo extends BaseVo {
    /**
     * 字典主键
     */
	private Integer typeId;
    /**
     * 字典名称
     */
	private String typeName;
    /**
     * 字典类型
     */
	private String typeCode;
    /**
     * 状态（1正常 0停用）
     */
	private Integer status;
    /**
     * 备注
     */
	private String remark;
    /**
     * 创建时间
     */
	private Date created;
    /**
     * 修改时间
     */
	private Date updated;

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeId()
	{
		return typeId;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName()
	{
		return typeName;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeCode()
	{
		return typeCode;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getUpdated()
	{
		return updated;
	}

	@Override
    public String toString() {
        return "SysDictTypeVo{" +
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
