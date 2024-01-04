package com.sundata.edu.domain;

import javax.persistence.*;

/**
 * 表 gradeinfo
 *
 * @author whj
 * @date 2019-07-19 14:33:47
 */
@Table(name = "`gradeinfo`")
public class Gradeinfo {
	/**
	 *
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`id`")
	private Integer id;
	/**
	 *
	 */
	@Column(name = "`grade_id`")
	private String gradeId;
	/**
	 *
	 */
	@Column(name = "`grade_code`")
	private String gradeCode;
	/**
	 *
	 */
	@Column(name = "`grade_name`")
	private String gradeName;
	/**
	 * 学校ID
	 */
	@Column(name = "`school_id`")
	private String schoolId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolId() {
		return schoolId;
	}

	@Override
    public String toString() {
        return "Gradeinfo{" +
        		"id=" + id +
        		"gradeId=" + gradeId +
        		"gradeCode=" + gradeCode +
        		"gradeName=" + gradeName +
        		"schoolId=" + schoolId +
				"}";
    }
}
