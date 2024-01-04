package com.sundata.edu.vo;
import java.util.Date;
/**
 * 表 exa_postgraduate_title
 * 
 * @author zz
 * @date 2021-11-30 11:54:15
 */
public class ExaPostgraduateTitleVo extends BaseVo {
    /**
     * 
     */
	private Integer id;
    /**
     * 研考标题
     */
	private String title;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	@Override
    public String toString() {
        return "ExaPostgraduateTitleVo{" +
        		"id=" + id +
        		"title=" + title +
				"}";
    }
}
