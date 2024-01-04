package com.sundata.edu.service.impl;


import com.sundata.edu.domain.Mz;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.MzDao;
import com.sundata.edu.service.MzService;
import com.sundata.edu.vo.MzVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 *  服务层实现
 *
 * @author whj
 * @date 2020-05-12 16:08:47
 */
@Service("mzService")
//@Transactional
public class MzServiceImpl extends AbstractService<Mz> implements MzService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private MzDao mzDao;

    @Override
    public List<Mz> getMzs(MzVo mzVo) {
        Example example = new Example(Mz.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("", "");
        return mzDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(Mz.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("mzId", idArray);
        Mz mz = new Mz();
        //mz.setIsdelete(1);
        return mzDao.updateByExampleSelective(mz, example);
    }

}
