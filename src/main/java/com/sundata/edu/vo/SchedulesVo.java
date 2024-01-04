package com.sundata.edu.vo;

/**
 * 表 schedules
 *
 * @author whj
 * @date 2021-10-28 09:45:27
 */
public class SchedulesVo extends BaseVo {
    /**
     * 任课id
     */
	private Long teachid;
    /**
     * 学段
     */
	private String phasestudy;
    /**
     * 科目id
     */
	private String subjectid;
    /**
     * 老师id
     */
	private String userid;
    /**
     * 班级id
     */
	private String classid;

	/**
	 * 年级id
	 */
	private String gradeid;

	public Long getTeachid() {
		return teachid;
	}

	@Override
	public String toString() {
		return "SchedulesVo{" +
				"teachid=" + teachid +
				", phasestudy='" + phasestudy + '\'' +
				", subjectid='" + subjectid + '\'' +
				", userid='" + userid + '\'' +
				", classid='" + classid + '\'' +
				", gradeid='" + gradeid + '\'' +
				'}';
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
