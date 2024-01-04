package com.sundata.edu.service.impl;


import com.sundata.edu.domain.Schedules;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.SchedulesDao;
import com.sundata.edu.service.SchedulesService;
import com.sundata.edu.vo.SchedulesVo;
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
 * @date 2021-10-27 10:03:25
 */
@Service("schedulesService")
//@Transactional
public class SchedulesServiceImpl extends AbstractService<Schedules> implements SchedulesService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SchedulesDao schedulesDao;

    @Override
    public List<Schedules> getSchedulessbtes(SchedulesVo schedulesVo) {
        Example example = new Example(Schedules.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",schedulesVo.getUserid() );
        return schedulesDao.selectByExample(example);
    }

    @Override
    public List<Schedules> getScheduless(SchedulesVo schedulesVo) {
        Example example = new Example(Schedules.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("", "");
        return schedulesDao.selectByExample(example);
    }

    @Override
    public List<Schedules> getSchedulesslist(SchedulesVo schedulesVo) {
        Example example = new Example(Schedules.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",schedulesVo.getUserid() );
        criteria.andEqualTo("gradeid",schedulesVo.getGradeid());
        criteria.andEqualTo("classid",schedulesVo.getClassid());
        criteria.andEqualTo("subjectid",schedulesVo.getSubjectid());
        return schedulesDao.selectByExample(example);
    }

    @Override
    public List<Schedules> getSchedulesstablelist(SchedulesVo schedulesVo) {
        Example example = new Example(Schedules.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("teachid",schedulesVo.getTeachid());
        return schedulesDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(Schedules.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("teachid", idArray);
        Schedules schedules = new Schedules();
        //schedules.setIsdelete(1);
        return schedulesDao.updateByExampleSelective(schedules, example);

    }

}
