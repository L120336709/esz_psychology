package com.sundata.edu.domain;
import com.sundata.edu.annotation.Excel;
import com.sundata.edu.vo.BaseVo;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表 sapiential
 *
 * @author whj
 * @date 2021-10-20 11:38:07
 */
@Table(name = "`sapiential`")
public class Sapiential  implements Serializable {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`id`")
	@Excel(name = "主键")
	private Long id;
	/**
	 * 考生姓名
	 */
	@Column(name = "`examineename`")
	@Excel(name = "考生姓名")
	private String examineename;
	/**
	 * 身份证号
	 */
	@Column(name = "`idnumber`")
	@Excel(name = "身份证号")
	private String idnumber;
	/**
	 * 准考证号
	 */
	@Column(name = "`examregistrationnumber`")
	@Excel(name = "准考证号")
	private String examregistrationnumber;
	/**
	 * 考试时间
	 */
	@Column(name = "`examtime`")
	@Excel(name = "考试时间")
	private String examtime;
	/**
	 * 考场号
	 */
	@Column(name = "`delegationexploratorymission`")
	@Excel(name = "考场号")
	private String delegationexploratorymission;
	/**
	 * 考点代码
	 */
	@Column(name = "`thetestcode`")
	@Excel(name = "考点代码")
	private String thetestcode;
	/**
	 * 考点名称
	 */
	@Column(name = "`theexaminationsitename`")
	@Excel(name = "考点名称")
	private String theexaminationsitename;
	/**
	 * 考点地址
	 */
	@Column(name = "`theexaminationsiteaddress`")
	@Excel(name = "考点地址")
	private String theexaminationsiteaddress;
	/**
	 * 楼栋
	 */
	@Column(name = "`building`")
	@Excel(name = "楼栋")
	private String building;
	/**
	 * 楼层
	 */
	@Column(name = "`floorlevel`")
	@Excel(name = "楼层")
	private String floorlevel;
	/**
	 * 教室号
	 */
	@Column(name = "`theclassroomno`")
	@Excel(name = "教室号")
	private String theclassroomno;
	/**
	 * 座位号
	 */
	@Column(name = "`seatnumber`")
	@Excel(name = "座位号")
	private String seatnumber;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

	public void setExamineename(String examineename) {
		this.examineename = examineename;
	}

	public String getExamineename()
	{
		return examineename;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getIdnumber()
	{
		return idnumber;
	}

	public void setExamregistrationnumber(String examregistrationnumber) {
		this.examregistrationnumber = examregistrationnumber;
	}

	public String getExamregistrationnumber()
	{
		return examregistrationnumber;
	}

	public void setExamtime(String examtime) {
		this.examtime = examtime;
	}

	public String getExamtime()
	{
		return examtime;
	}

	public void setDelegationexploratorymission(String delegationexploratorymission) {
		this.delegationexploratorymission = delegationexploratorymission;
	}

	public String getDelegationexploratorymission()
	{
		return delegationexploratorymission;
	}

	public void setThetestcode(String thetestcode) {
		this.thetestcode = thetestcode;
	}

	public String getThetestcode()
	{
		return thetestcode;
	}

	public void setTheexaminationsitename(String theexaminationsitename) {
		this.theexaminationsitename = theexaminationsitename;
	}

	public String getTheexaminationsitename()
	{
		return theexaminationsitename;
	}

	public void setTheexaminationsiteaddress(String theexaminationsiteaddress) {
		this.theexaminationsiteaddress = theexaminationsiteaddress;
	}

	public String getTheexaminationsiteaddress()
	{
		return theexaminationsiteaddress;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuilding()
	{
		return building;
	}

	public void setFloorlevel(String floorlevel) {
		this.floorlevel = floorlevel;
	}

	public String getFloorlevel()
	{
		return floorlevel;
	}

	public void setTheclassroomno(String theclassroomno) {
		this.theclassroomno = theclassroomno;
	}

	public String getTheclassroomno()
	{
		return theclassroomno;
	}

	public void setSeatnumber(String seatnumber) {
		this.seatnumber = seatnumber;
	}

	public String getSeatnumber()
	{
		return seatnumber;
	}

	@Override
    public String toString() {
        return "SapientialVo{" +
        		"id=" + id +
        		"examineename=" + examineename +
        		"idnumber=" + idnumber +
        		"examregistrationnumber=" + examregistrationnumber +
        		"examtime=" + examtime +
        		"delegationexploratorymission=" + delegationexploratorymission +
        		"thetestcode=" + thetestcode +
        		"theexaminationsitename=" + theexaminationsitename +
        		"theexaminationsiteaddress=" + theexaminationsiteaddress +
        		"building=" + building +
        		"floorlevel=" + floorlevel +
        		"theclassroomno=" + theclassroomno +
        		"seatnumber=" + seatnumber +
				"}";
    }
}
