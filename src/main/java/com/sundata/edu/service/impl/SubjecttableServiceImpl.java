package com.sundata.edu.service.impl;


import com.sundata.edu.domain.Subjecttable;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.SubjecttableDao;
import com.sundata.edu.service.SubjecttableService;
import com.sundata.edu.vo.SubjecttableVo;
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
 * @date 2021-10-28 10:22:25
 */
@Service("subjecttableService")
//@Transactional
public class SubjecttableServiceImpl extends AbstractService<Subjecttable> implements SubjecttableService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SubjecttableDao subjecttableDao;

    @Override
    public List<Subjecttable> getSubjecttables(SubjecttableVo subjecttableVo) {
        Example example = new Example(Subjecttable.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("subjectid", subjecttableVo.getSubjectid());
        return subjecttableDao.selectByExample(example);
    }

    @Override
    public List<Subjecttable> getSubjecttablesject(SubjecttableVo subjecttableVo) {
        Example example = new Example(Subjecttable.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("subjectid", subjecttableVo.getSubjectid());
        criteria.andEqualTo("phasestudy", subjecttableVo.getPhasestudy());
        return subjecttableDao.selectByExample(example);
    }

    @Override
    public List<Subjecttable> getSubjecttablesclass(SubjecttableVo subjecttableVo) {
        Example example = new Example(Subjecttable.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("subjectid", subjecttableVo.getSubjectid());
        return subjecttableDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(Subjecttable.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("${primaryKey.attrVariableName}", idArray);
        Subjecttable subjecttable = new Subjecttable();
        //subjecttable.setIsdelete(1);
        return subjecttableDao.updateByExampleSelective(subjecttable, example);
    }

    @Override
    public List<Subjecttable> getSubjectertables(SubjecttableVo subjecttableVo) {
        Example example = new Example(Subjecttable.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phasestudy", subjecttableVo.getPhasestudy());
        return subjecttableDao.selectByExample(example);
    }

}
