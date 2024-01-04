package com.sundata.edu.vo;
import java.util.Date;
/**
 * 表 phaseofstudy
 * 
 * @author whj
 * @date 2021-11-01 08:43:50
 */
public class PhaseofstudyVo extends BaseVo {
    /**
     * 学段id
     */
	private Long phasestudyid;
    /**
     * 学段
     */
	private String phasestudy;

	public void setPhasestudyid(Long phasestudyid) {
		this.phasestudyid = phasestudyid;
	}

	public Long getPhasestudyid()
	{
		return phasestudyid;
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
        return "PhaseofstudyVo{" +
        		"phasestudyid=" + phasestudyid +
        		"phasestudy=" + phasestudy +
				"}";
    }
}
