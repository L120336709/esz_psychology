package com.sundata.edu.domain;

import com.sundata.edu.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


/**
 * 成人高考标题对象 adulttitletable
 *
 * @author whj
 * @date 2021-10-25
 */
public class Adulttitletable implements Serializable
{

    /** 主键 */
    private Long id;

    /** 成人标题 */
    @Excel(name = "成人标题")
    private String adulttitle;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAdulttitle(String adulttitle)
    {
        this.adulttitle = adulttitle;
    }

    public String getAdulttitle()
    {
        return adulttitle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("adulttitle", getAdulttitle())
            .toString();
    }
}
