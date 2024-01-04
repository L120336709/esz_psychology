package com.sundata.edu.vo;
import java.util.Date;
/**
 * 表 exa_postgraduate
 * 
 * @author zz
 * @date 2021-11-30 10:36:39
 */
public class ExaPostgraduateVo extends BaseVo {
    /**
     * id
     */
	private String id;
    /**
     * 姓名
     */
	private String stuname;
    /**
     * 考生编号
     */
	private String candidateNumber;
    /**
     * 报考单位
     */
	private String applySchool;
    /**
     * 考场号
     */
	private String exaRoomNum;
    /**
     * 座位号
     */
	private String seatNumber;
    /**
     * 楼栋
     */
	private String building;
    /**
     * 教室
     */
	private String classroom;
    /**
     * 楼层
     */
	private String floor;
    /**
     * 考点
     */
	private String exaSite;

	public void setId(String id) {
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getStuname()
	{
		return stuname;
	}

	public void setCandidateNumber(String candidateNumber) {
		this.candidateNumber = candidateNumber;
	}

	public String getCandidateNumber()
	{
		return candidateNumber;
	}

	public void setApplySchool(String applySchool) {
		this.applySchool = applySchool;
	}

	public String getApplySchool()
	{
		return applySchool;
	}

	public void setExaRoomNum(String exaRoomNum) {
		this.exaRoomNum = exaRoomNum;
	}

	public String getExaRoomNum()
	{
		return exaRoomNum;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatNumber()
	{
		return seatNumber;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuilding()
	{
		return building;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public String getClassroom()
	{
		return classroom;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getFloor()
	{
		return floor;
	}

	public void setExaSite(String exaSite) {
		this.exaSite = exaSite;
	}

	public String getExaSite()
	{
		return exaSite;
	}

	@Override
    public String toString() {
        return "ExaPostgraduateVo{" +
        		"id=" + id +
        		"stuname=" + stuname +
        		"candidateNumber=" + candidateNumber +
        		"applySchool=" + applySchool +
        		"exaRoomNum=" + exaRoomNum +
        		"seatNumber=" + seatNumber +
        		"building=" + building +
        		"classroom=" + classroom +
        		"floor=" + floor +
        		"exaSite=" + exaSite +
				"}";
    }
}
