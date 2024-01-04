package com.sundata.edu.domain;

import javax.persistence.*;
import java.util.Date;
/**
 * 表 subjecttable
 *
 * @author whj
 * @date 2021-10-28 10:22:25
 */
@Table(name = "`subjecttable`")
public class Subjecttable {
	/**
	 * 学科id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`subjectid`")
	private Long subjectid;
	/**
	 * 科目
	 */
	@Column(name = "`subjectname`")
	private String subjectname;
	/**
	 * 学段
	 */
	@Column(name = "`phasestudy`")
	private String phasestudy;
	/**
	 * 学段id
	 */
	@Column(name = "`phasestudyid`")
	private String phasestudyid;

	public void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}

	public Long getSubjectid() {
		return subjectid;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setPhasestudy(String phasestudy) {
		this.phasestudy = phasestudy;
	}

	public String getPhasestudy() {
		return phasestudy;
	}

	public void setPhasestudyid(String phasestudyid) {
		this.phasestudyid = phasestudyid;
	}

	public String getPhasestudyid() {
		return phasestudyid;
	}

	@Override
    public String toString() {
        return "Subjecttable{" +
        		"subjectid=" + subjectid +
        		"subjectname=" + subjectname +
        		"phasestudy=" + phasestudy +
        		"phasestudyid=" + phasestudyid +
				"}";
    }
}
