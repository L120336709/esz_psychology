package com.sundata.edu.vo;
import java.util.Date;
/**
 * 表 subjecttable
 * 
 * @author whj
 * @date 2021-10-28 10:22:25
 */
public class SubjecttableVo extends BaseVo {
    /**
     * 学科id
     */
	private Long subjectid;
    /**
     * 科目
     */
	private String subjectname;
    /**
     * 学段
     */
	private String phasestudy;
    /**
     * 学段id
     */
	private String phasestudyid;

	public void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}

	public Long getSubjectid()
	{
		return subjectid;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSubjectname()
	{
		return subjectname;
	}

	public void setPhasestudy(String phasestudy) {
		this.phasestudy = phasestudy;
	}

	public String getPhasestudy()
	{
		return phasestudy;
	}

	public void setPhasestudyid(String phasestudyid) {
		this.phasestudyid = phasestudyid;
	}

	public String getPhasestudyid()
	{
		return phasestudyid;
	}

	@Override
    public String toString() {
        return "SubjecttableVo{" +
        		"subjectid=" + subjectid +
        		"subjectname=" + subjectname +
        		"phasestudy=" + phasestudy +
        		"phasestudyid=" + phasestudyid +
				"}";
    }
}
