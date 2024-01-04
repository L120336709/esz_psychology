package com.sundata.edu.service.impl;


import com.sundata.edu.domain.Deptinfo;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.DeptinfoDao;
import com.sundata.edu.service.DeptinfoService;
import com.sundata.edu.vo.DeptinfoVo;
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
 * @date 2021-10-19 23:23:37
 */
@Service("deptinfoService")
//@Transactional
public class DeptinfoServiceImpl extends AbstractService<Deptinfo> implements DeptinfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private DeptinfoDao deptinfoDao;

    @Override
    public List<Deptinfo> getDeptinfos(DeptinfoVo deptinfoVo) {
        Example example = new Example(Deptinfo.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("", "");
        return deptinfoDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(Deptinfo.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("id", idArray);
        Deptinfo deptinfo = new Deptinfo();
        //deptinfo.setIsdelete(1);
        return deptinfoDao.updateByExampleSelective(deptinfo, example);
    }

}
