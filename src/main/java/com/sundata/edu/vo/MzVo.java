package com.sundata.edu.vo;

/**
 * 表 mz
 * 
 * @author 周天宇
 * @date 2020-05-12 16:08:47
 */
public class MzVo extends BaseVo {
    /**
     * 
     */
	private String mzId;
    /**
     * 
     */
	private String mzname;

	public void setMzId(String mzId) {
		this.mzId = mzId;
	}

	public String getMzId()
	{
		return mzId;
	}

	public void setMzname(String mzname) {
		this.mzname = mzname;
	}

	public String getMzname()
	{
		return mzname;
	}

	@Override
    public String toString() {
        return "MzVo{" +
        		"mzId=" + mzId +
        		"mzname=" + mzname +
				"}";
    }
}
