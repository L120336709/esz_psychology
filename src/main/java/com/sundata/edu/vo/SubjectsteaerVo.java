package com.sundata.edu.vo;
import java.util.Date;
/**
 * 表 subjectsteaer
 * 
 * @author whj
 * @date 2021-10-28 14:34:38
 */
public class SubjectsteaerVo extends BaseVo {
    /**
     * 主键
     */
	private Long id;
    /**
     * 用户（教师）
     */
	private String userid;
    /**
     * 科目id
     */
	private String subject;
    /**
     * 学段
     */
	private String phasestudy;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setPhasestudy(String phasestudy) {
		this.phasestudy = phasestudy;
	}

	public String getPhasestudy()
	{
		return phasestudy;
	}

	@Override
    public String toString() {
        return "SubjectsteaerVo{" +
        		"id=" + id +
        		"userid=" + userid +
        		"subject=" + subject +
        		"phasestudy=" + phasestudy +
				"}";
    }
}
