package com.sundata.edu.domain;

import javax.persistence.*;
import java.util.Date;
/**
 * 表 phaseofstudy
 * 
 * @author whj
 * @date 2021-11-01 08:43:50
 */
@Table(name = "`phaseofstudy`")
public class Phaseofstudy {
	/**
	 * 学段id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`phasestudyid`")
	private Long phasestudyid;
	/**
	 * 学段
	 */
	@Column(name = "`phasestudy`")
	private String phasestudy;

	public void setPhasestudyid(Long phasestudyid) {
		this.phasestudyid = phasestudyid;
	}

	public Long getPhasestudyid() {
		return phasestudyid;
	}

	public void setPhasestudy(String phasestudy) {
		this.phasestudy = phasestudy;
	}

	public String getPhasestudy() {
		return phasestudy;
	}

	@Override
    public String toString() {
        return "Phaseofstudy{" +
        		"phasestudyid=" + phasestudyid +
        		"phasestudy=" + phasestudy +
				"}";
    }
}
