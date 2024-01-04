package com.sundata.edu.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 考试表对象 psy_x_examination
 * 
 * @author ljg
 * @date 2023-11-24
 */
public class PsyXExamination
{
    private static final long serialVersionUID = 1L;

    /** 考试id */
    private Long id;

    private String userId;

    /** 课程id */
    @Excel(name = "课程id")
    private Long kcId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String kcName;

    /** 题目总数量 */
    @Excel(name = "题目总数量")
    private Integer quesTotalnum;

    /** 正确题目数量 */
    @Excel(name = "正确题目数量")
    private Integer quesTruenum;

    /** 错误题目数量 */
    @Excel(name = "错误题目数量")
    private Integer quesFalsenum;

    /** 未做题目数量 */
    @Excel(name = "未做题目数量")
    private Integer quesNullnum;

    /** 是否通过本次测试 */
    @Excel(name = "是否通过本次测试")
    private String passed;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30)
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30)
    private Date endTime;

    /** 题目列表 */
    @Excel(name = "题目列表")
    private String quesList;

    /** 答案列表 */
    @Excel(name = "答案列表")
    private String anwserList;

    private Double score;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setKcId(Long kcId)
    {
        this.kcId = kcId;
    }

    public Long getKcId() 
    {
        return kcId;
    }
    public void setKcName(String kcName) 
    {
        this.kcName = kcName;
    }

    public String getKcName() 
    {
        return kcName;
    }
    public void setQuesTotalnum(Integer quesTotalnum)
    {
        this.quesTotalnum = quesTotalnum;
    }

    public Integer getQuesTotalnum()
    {
        return quesTotalnum;
    }
    public void setQuesTruenum(Integer quesTruenum)
    {
        this.quesTruenum = quesTruenum;
    }

    public Integer getQuesTruenum()
    {
        return quesTruenum;
    }
    public void setQuesFalsenum(Integer quesFalsenum)
    {
        this.quesFalsenum = quesFalsenum;
    }

    public Integer getQuesFalsenum()
    {
        return quesFalsenum;
    }
    public void setQuesNullnum(Integer quesNullnum)
    {
        this.quesNullnum = quesNullnum;
    }

    public Integer getQuesNullnum()
    {
        return quesNullnum;
    }
    public void setPassed(String passed) 
    {
        this.passed = passed;
    }

    public String getPassed() 
    {
        return passed;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setQuesList(String quesList) 
    {
        this.quesList = quesList;
    }

    public String getQuesList() 
    {
        return quesList;
    }
    public void setAnwserList(String anwserList) 
    {
        this.anwserList = anwserList;
    }

    public String getAnwserList() 
    {
        return anwserList;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PsyXExamination{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", kcId=" + kcId +
                ", kcName='" + kcName + '\'' +
                ", quesTotalnum=" + quesTotalnum +
                ", quesTruenum=" + quesTruenum +
                ", quesFalsenum=" + quesFalsenum +
                ", quesNullnum=" + quesNullnum +
                ", passed='" + passed + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", quesList='" + quesList + '\'' +
                ", anwserList='" + anwserList + '\'' +
                ", score=" + score +
                '}';
    }
}
