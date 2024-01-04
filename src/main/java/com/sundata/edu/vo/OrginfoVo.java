package com.sundata.edu.vo;

/**
 * 机构表 orginfo
 * 
 * @author 侯鹏
 * @date 2019-04-17 10:41:06
 */
public class OrginfoVo extends BaseVo {
    /**
     * 组织机构id
     */
	private String orgId;
    /**
     * 组织机构名称
     */
	private String orgName;
    /**
     * 负责人
     */
	private String leaders;
    /**
     * 联系电话
     */
	private String telephone;
    /**
     * 现有人数
     */
	private Integer peopleNum;
    /**
     * 领导人数
     */
	private Integer leadersNum;
    /**
     * 专任教师数
     */
	private Integer teacherNum;
    /**
     * 工勤人数
     */
	private Integer logisticsNum;
    /**
     * 学生人数
     */
	private Integer studentNum;
    /**
     * 班级数
     */
	private Integer classNum;
    /**
     * 专业技术岗位核定数
     */
	private Integer technologySanctionedNum;
    /**
     * 专业技术岗位实际聘任数
     */
	private Integer technologyActualNum;
    /**
     * 超（空）情况
     */
	private String deviationInfo;
    /**
     * 状态 1=正常 0=禁用
     */
	private Integer status;

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgId()
	{
		return orgId;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgName()
	{
		return orgName;
	}

	public void setLeaders(String leaders) {
		this.leaders = leaders;
	}

	public String getLeaders()
	{
		return leaders;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	public Integer getPeopleNum()
	{
		return peopleNum;
	}

	public void setLeadersNum(Integer leadersNum) {
		this.leadersNum = leadersNum;
	}

	public Integer getLeadersNum()
	{
		return leadersNum;
	}

	public void setTeacherNum(Integer teacherNum) {
		this.teacherNum = teacherNum;
	}

	public Integer getTeacherNum()
	{
		return teacherNum;
	}

	public void setLogisticsNum(Integer logisticsNum) {
		this.logisticsNum = logisticsNum;
	}

	public Integer getLogisticsNum()
	{
		return logisticsNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

	public Integer getStudentNum()
	{
		return studentNum;
	}

	public void setClassNum(Integer classNum) {
		this.classNum = classNum;
	}

	public Integer getClassNum()
	{
		return classNum;
	}

	public void setTechnologySanctionedNum(Integer technologySanctionedNum) {
		this.technologySanctionedNum = technologySanctionedNum;
	}

	public Integer getTechnologySanctionedNum()
	{
		return technologySanctionedNum;
	}

	public void setTechnologyActualNum(Integer technologyActualNum) {
		this.technologyActualNum = technologyActualNum;
	}

	public Integer getTechnologyActualNum()
	{
		return technologyActualNum;
	}

	public void setDeviationInfo(String deviationInfo) {
		this.deviationInfo = deviationInfo;
	}

	public String getDeviationInfo()
	{
		return deviationInfo;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	@Override
    public String toString() {
        return "OrginfoVo{" +
        		"orgId=" + orgId +
        		"orgName=" + orgName +
        		"leaders=" + leaders +
        		"telephone=" + telephone +
        		"peopleNum=" + peopleNum +
        		"leadersNum=" + leadersNum +
        		"teacherNum=" + teacherNum +
        		"logisticsNum=" + logisticsNum +
        		"studentNum=" + studentNum +
        		"classNum=" + classNum +
        		"technologySanctionedNum=" + technologySanctionedNum +
        		"technologyActualNum=" + technologyActualNum +
        		"deviationInfo=" + deviationInfo +
        		"status=" + status +
				"}";
    }
}
