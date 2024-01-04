package com.sundata.edu.vo;

/**
 * 表 classinfo
 * 
 * @author GhostKang
 * @date 2019-07-19 14:33:10
 */
public class ClassinfoVo extends BaseVo {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 班级id
     */
	private String classId;
    /**
     * 班级代码 ，如1班 对应01
     */
	private String classCode;
    /**
     * 班级名称，内置班级名称 
     */
	private String className;
    /**
     * 班级别名，如火箭班
     */
	private String classNickname;
    /**
     * 年级（比如2018学年）
     */
	private String classLevel;
    /**
     * 所属年级Id
     */
	private String gradeId;
    /**
     * 所属年级代码
     */
	private String gradeCode;
    /**
     * 所属年级名称
     */
	private String gradeName;
    /**
     * 学校ID
     */
	private String schoolId;
    /**
     * 状态 1=可用 0不可用
     */
	private String status;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassId()
	{
		return classId;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassCode()
	{
		return classCode;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassNickname(String classNickname) {
		this.classNickname = classNickname;
	}

	public String getClassNickname()
	{
		return classNickname;
	}

	public void setClassLevel(String classLevel) {
		this.classLevel = classLevel;
	}

	public String getClassLevel()
	{
		return classLevel;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus()
	{
		return status;
	}

	@Override
    public String toString() {
        return "ClassinfoVo{" +
        		"id=" + id +
        		"classId=" + classId +
        		"classCode=" + classCode +
        		"className=" + className +
        		"classNickname=" + classNickname +
        		"classLevel=" + classLevel +
        		"gradeId=" + gradeId +
        		"gradeCode=" + gradeCode +
        		"gradeName=" + gradeName +
        		"schoolId=" + schoolId +
        		"status=" + status +
				"}";
    }
}
