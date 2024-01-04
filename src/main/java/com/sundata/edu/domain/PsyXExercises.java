package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 习题表对象 psy_x_exercises
 * 
 * @author ljg
 * @date 2023-11-24
 */
public class PsyXExercises
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 课程 */
    @Excel(name = "课程")
    private Long kcId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String kcName;

    /** 题型，1单选，2多选，3填空，4判断 */
    @Excel(name = "题型，1单选，2多选，3填空，4判断")
    private String type;

    /** 答案列表 */
    @Excel(name = "答案列表")
    private String optionDtos;

    /** 答案顺序 */
    @Excel(name = "答案顺序")
    private Long position;

    /** 分数 */
    @Excel(name = "分数")
    private Long score;

    /** 富文本题目 */
    @Excel(name = "富文本题目")
    private String title;

    /** 纯文本题干 */
    @Excel(name = "纯文本题干")
    private String plainTextTitle;

    /** 附件 */
    @Excel(name = "附件")
    private String titleAttachment;

    /** 无须 */
    @Excel(name = "无须")
    private String sampleAnswerJson;

    /** 填空题答案 */
    @Excel(name = "填空题答案")
    private String stdAnswer;

    /** 题目解析 */
    @Excel(name = "题目解析")
    private String analyse;

    /** 填空题专用字段，1数值精确，2文字精确，3数值模糊，4文字模糊 */
    @Excel(name = "填空题专用字段，1数值精确，2文字精确，3数值模糊，4文字模糊")
    private String fillblankType;

    /** 得分点，互评主观题专用，该题目学生能打分的区间，无须 */
    @Excel(name = "得分点，互评主观题专用，该题目学生能打分的区间，无须")
    private String judgerules;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setOptionDtos(String optionDtos) 
    {
        this.optionDtos = optionDtos;
    }

    public String getOptionDtos() 
    {
        return optionDtos;
    }
    public void setPosition(Long position) 
    {
        this.position = position;
    }

    public Long getPosition() 
    {
        return position;
    }
    public void setScore(Long score) 
    {
        this.score = score;
    }

    public Long getScore() 
    {
        return score;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPlainTextTitle(String plainTextTitle) 
    {
        this.plainTextTitle = plainTextTitle;
    }

    public String getPlainTextTitle() 
    {
        return plainTextTitle;
    }
    public void setTitleAttachment(String titleAttachment) 
    {
        this.titleAttachment = titleAttachment;
    }

    public String getTitleAttachment() 
    {
        return titleAttachment;
    }
    public void setSampleAnswerJson(String sampleAnswerJson) 
    {
        this.sampleAnswerJson = sampleAnswerJson;
    }

    public String getSampleAnswerJson() 
    {
        return sampleAnswerJson;
    }
    public void setStdAnswer(String stdAnswer) 
    {
        this.stdAnswer = stdAnswer;
    }

    public String getStdAnswer() 
    {
        return stdAnswer;
    }
    public void setAnalyse(String analyse) 
    {
        this.analyse = analyse;
    }

    public String getAnalyse() 
    {
        return analyse;
    }
    public void setFillblankType(String fillblankType) 
    {
        this.fillblankType = fillblankType;
    }

    public String getFillblankType() 
    {
        return fillblankType;
    }
    public void setJudgerules(String judgerules) 
    {
        this.judgerules = judgerules;
    }

    public String getJudgerules() 
    {
        return judgerules;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("kcId", getKcId())
            .append("kcName", getKcName())
            .append("type", getType())
            .append("optionDtos", getOptionDtos())
            .append("position", getPosition())
            .append("score", getScore())
            .append("title", getTitle())
            .append("plainTextTitle", getPlainTextTitle())
            .append("titleAttachment", getTitleAttachment())
            .append("sampleAnswerJson", getSampleAnswerJson())
            .append("stdAnswer", getStdAnswer())
            .append("analyse", getAnalyse())
            .append("fillblankType", getFillblankType())
            .append("judgerules", getJudgerules())
            .toString();
    }
}
