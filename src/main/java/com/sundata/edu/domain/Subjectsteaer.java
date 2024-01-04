package com.sundata.edu.domain;

import javax.persistence.*;
import java.util.Date;
/**
 * 表 subjectsteaer
 * 
 * @author whj
 * @date 2021-10-28 14:34:38
 */
@Table(name = "`subjectsteaer`")
public class Subjectsteaer {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`id`")
	private Long id;
	/**
	 * 用户（教师）
	 */
	@Column(name = "`userid`")
	private String userid;
	/**
	 * 科目id
	 */
	@Column(name = "`subject`")
	private String subject;
	/**
	 * 学段
	 */
	@Column(name = "`phasestudy`")
	private String phasestudy;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setPhasestudy(String phasestudy) {
		this.phasestudy = phasestudy;
	}

	public String getPhasestudy() {
		return phasestudy;
	}

	@Override
    public String toString() {
        return "Subjectsteaer{" +
        		"id=" + id +
        		"userid=" + userid +
        		"subject=" + subject +
        		"phasestudy=" + phasestudy +
				"}";
    }
}
