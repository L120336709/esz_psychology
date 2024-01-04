package com.sundata.edu.service;

import com.sundata.edu.domain.Phaseofstudy;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.PhaseofstudyVo;

import java.util.List;

/**
 *  服务层
 * 
 * @author whj
 * @date 2021-11-01 08:43:50
 */
public interface PhaseofstudyService extends Service<Phaseofstudy> {

    /**
    * 根据条件查询列表
    * @param phaseofstudyVo
    * @return
    */
    List<Phaseofstudy> getPhaseofstudys(PhaseofstudyVo phaseofstudyVo);

    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

    List<Phaseofstudy> getPhaseofstudystu(PhaseofstudyVo phaseofstudyVo);
}
