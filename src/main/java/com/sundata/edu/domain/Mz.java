package com.sundata.edu.domain;

import javax.persistence.*;

/**
 * 表 mz
 *
 * @author whj
 * @date 2020-05-12 16:08:47
 */
@Table(name = "`mz`")
public class Mz {
	/**
	 *
	 */
	@Id
	@Column(name = "`mz_id`")
	private String mzId;
	/**
	 *
	 */
	@Column(name = "`mzname`")
	private String mzname;

	public void setMzId(String mzId) {
		this.mzId = mzId;
	}

	public String getMzId() {
		return mzId;
	}

	public void setMzname(String mzname) {
		this.mzname = mzname;
	}

	public String getMzname() {
		return mzname;
	}

	@Override
    public String toString() {
        return "Mz{" +
        		"mzId=" + mzId +
        		"mzname=" + mzname +
				"}";
    }
}
