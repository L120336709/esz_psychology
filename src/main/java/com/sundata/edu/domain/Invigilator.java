package com.sundata.edu.domain;
import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表 invigilator
 * 
 * @author shunguo
 * @date 2021-12-10
 */
@Table(name = "`invigilator`")
public class Invigilator
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id
	@Column(name = "`id`")
	private String teachid;
	/** 姓名 */
	@Excel(name = "姓名")
	private String teachname;
	/** 身份证号 */
	@Excel(name = "身份证号")
	private String idnumber;
	/** 性别 */
	@Excel(name = "性别")
	private String gender;
	/** 学段类别 */
	@Excel(name = "学段类别")
	private String academic;
	/** 学科代码 */
	@Excel(name = "学科代码")
	private String subject;
	/** 单位名称 */
	@Excel(name = "单位名称")
	private String unitname;
	/** 职称职务 */
	@Excel(name = "职称职务")
	private String officialcapacity;
	/** 手机号码 */
	@Excel(name = "手机号码")
	private String phonenumber;
	/** 银行账号 */
	@Excel(name = "银行账号")
	private String bankaccount;
	/** 开户行 */
	@Excel(name = "开户行")
	private String bankdeposit;
	/** 区域代码 */
	@Excel(name = "区域代码")
	private String positionarea;
	/** 考点代码 */
	@Excel(name = "考点代码")
	private String positionsite;
	/** 考场代码 */
	@Excel(name = "考场代码")
	private String positionroom;
	/** 考务组代码 */
	@Excel(name = "考务组代码")
	private String positiongroup;
	/** 人员组别代码 */
	@Excel(name = "人员组别代码")
	private String positionpeoplegroup;
	/** 人员编号代码 */
	@Excel(name = "人员编号代码")
	private String positionpeoplecode;

	public void setTeachid(String teachid) 
	{
		this.teachid = teachid;
	}

	public String getTeachid() 
	{
		return teachid;
	}
	public void setTeachname(String teachname) 
	{
		this.teachname = teachname;
	}

	public String getTeachname() 
	{
		return teachname;
	}
	public void setIdnumber(String idnumber) 
	{
		this.idnumber = idnumber;
	}

	public String getIdnumber() 
	{
		return idnumber;
	}
	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	public String getGender() 
	{
		return gender;
	}
	public void setAcademic(String academic) 
	{
		this.academic = academic;
	}

	public String getAcademic() 
	{
		return academic;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}

	public String getSubject() 
	{
		return subject;
	}
	public void setUnitname(String unitname) 
	{
		this.unitname = unitname;
	}

	public String getUnitname() 
	{
		return unitname;
	}
	public void setOfficialcapacity(String officialcapacity) 
	{
		this.officialcapacity = officialcapacity;
	}

	public String getOfficialcapacity() 
	{
		return officialcapacity;
	}
	public void setPhonenumber(String phonenumber) 
	{
		this.phonenumber = phonenumber;
	}

	public String getPhonenumber() 
	{
		return phonenumber;
	}
	public void setBankaccount(String bankaccount) 
	{
		this.bankaccount = bankaccount;
	}

	public String getBankaccount() 
	{
		return bankaccount;
	}
	public void setBankdeposit(String bankdeposit) 
	{
		this.bankdeposit = bankdeposit;
	}

	public String getBankdeposit() 
	{
		return bankdeposit;
	}
	public void setPositionarea(String positionarea) 
	{
		this.positionarea = positionarea;
	}

	public String getPositionarea() 
	{
		return positionarea;
	}
	public void setPositionsite(String positionsite) 
	{
		this.positionsite = positionsite;
	}

	public String getPositionsite() 
	{
		return positionsite;
	}
	public void setPositionroom(String positionroom) 
	{
		this.positionroom = positionroom;
	}

	public String getPositionroom() 
	{
		return positionroom;
	}
	public void setPositiongroup(String positiongroup) 
	{
		this.positiongroup = positiongroup;
	}

	public String getPositiongroup() 
	{
		return positiongroup;
	}
	public void setPositionpeoplegroup(String positionpeoplegroup) 
	{
		this.positionpeoplegroup = positionpeoplegroup;
	}

	public String getPositionpeoplegroup() 
	{
		return positionpeoplegroup;
	}
	public void setPositionpeoplecode(String positionpeoplecode) 
	{
		this.positionpeoplecode = positionpeoplecode;
	}

	public String getPositionpeoplecode() 
	{
		return positionpeoplecode;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teachid", getTeachid())
            .append("teachname", getTeachname())
            .append("idnumber", getIdnumber())
            .append("gender", getGender())
            .append("academic", getAcademic())
            .append("subject", getSubject())
            .append("unitname", getUnitname())
            .append("officialcapacity", getOfficialcapacity())
            .append("phonenumber", getPhonenumber())
            .append("bankaccount", getBankaccount())
            .append("bankdeposit", getBankdeposit())
            .append("positionarea", getPositionarea())
            .append("positionsite", getPositionsite())
            .append("positionroom", getPositionroom())
            .append("positiongroup", getPositiongroup())
            .append("positionpeoplegroup", getPositionpeoplegroup())
            .append("positionpeoplecode", getPositionpeoplecode())
            .toString();
    }
}
