package com.sundata.edu.service.impl;


import com.sundata.edu.domain.Phaseofstudy;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.PhaseofstudyDao;
import com.sundata.edu.service.PhaseofstudyService;
import com.sundata.edu.vo.PhaseofstudyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 *  服务层实现
 *
 * @author whj
 * @date 2021-11-01 08:43:50
 */
@Service("phaseofstudyService")
//@Transactional
public class PhaseofstudyServiceImpl extends AbstractService<Phaseofstudy> implements PhaseofstudyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private PhaseofstudyDao phaseofstudyDao;

    @Override
    public List<Phaseofstudy> getPhaseofstudys(PhaseofstudyVo phaseofstudyVo) {
        Example example = new Example(Phaseofstudy.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("", "");
        return phaseofstudyDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(Phaseofstudy.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("phasestudyid", idArray);
        Phaseofstudy phaseofstudy = new Phaseofstudy();
        //phaseofstudy.setIsdelete(1);
        return phaseofstudyDao.updateByExampleSelective(phaseofstudy, example);
    }

    @Override
    public List<Phaseofstudy> getPhaseofstudystu(PhaseofstudyVo phaseofstudyVo) {
        Example example = new Example(Phaseofstudy.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phasestudyid",phaseofstudyVo.getPhasestudyid() );
        return phaseofstudyDao.selectByExample(example);
    }

}
