package com.sundata.edu.vo;

/**
 * 表 gradeinfo
 * 
 * @author GhostKang
 * @date 2019-07-19 14:33:47
 */
public class GradeinfoVo extends BaseVo {
    /**
     * 
     */
	private Integer id;
    /**
     * 
     */
	private String gradeId;
    /**
     * 
     */
	private String gradeCode;
    /**
     * 
     */
	private String gradeName;
    /**
     * 学校ID
     */
	private String schoolId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeId()
	{
		return gradeId;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public String getGradeCode()
	{
		return gradeCode;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeName()
	{
		return gradeName;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolId()
	{
		return schoolId;
	}

	@Override
    public String toString() {
        return "GradeinfoVo{" +
        		"id=" + id +
        		"gradeId=" + gradeId +
        		"gradeCode=" + gradeCode +
        		"gradeName=" + gradeName +
        		"schoolId=" + schoolId +
				"}";
    }
}
