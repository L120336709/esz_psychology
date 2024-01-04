package com.sundata.edu.domain;

import javax.persistence.*;
import java.util.Date;
/**
 * 表 schedules
 *
 * @author whj
 * @date 2021-10-28 09:45:27
 */
@Table(name = "`schedules`")
public class Schedules {
	/**
	 * 任课id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`teachid`")
	private Long teachid;
	/**
	 * 学段
	 */
	@Column(name = "`phasestudy`")
	private String phasestudy;
	/**
	 * 科目id
	 */
	@Column(name = "`subjectid`")
	private String subjectid;
	/**
	 * 老师id
	 */
	@Column(name = "`userid`")
	private String userid;
	/**
	 * 班级id
	 */
	@Column(name = "`classid`")
	private String classid;
	/**
	 * 班级id
	 */
	@Column(name = "`gradeid`")
	private String gradeid;

	@Override
	public String toString() {
		return "Schedules{" +
				"teachid=" + teachid +
				", phasestudy='" + phasestudy + '\'' +
				", subjectid='" + subjectid + '\'' +
				", userid='" + userid + '\'' +
				", classid='" + classid + '\'' +
				", gradeid='" + gradeid + '\'' +
				'}';
	}

	public Long getTeachid() {
		return teachid;
	}

	public void setTeachid(Long teachid) {
		this.teachid = teachid;
	}

	public String getPhasestudy() {
		return phasestudy;
	}

	public void setPhasestudy(String phasestudy) {
		this.phasestudy = phasestudy;
	}

	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getGradeid() {
		return gradeid;
	}

	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
}
