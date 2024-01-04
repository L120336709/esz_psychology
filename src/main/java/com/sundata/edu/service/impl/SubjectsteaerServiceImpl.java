package com.sundata.edu.service.impl;


import com.sundata.edu.domain.Subjectsteaer;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.SubjectsteaerDao;
import com.sundata.edu.service.SubjectsteaerService;
import com.sundata.edu.vo.SubjectsteaerVo;
import org.apache.shiro.SecurityUtils;
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
 * @date 2021-10-28 14:34:38
 */
@Service("subjectsteaerService")
//@Transactional
public class SubjectsteaerServiceImpl extends AbstractService<Subjectsteaer> implements SubjectsteaerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SubjectsteaerDao subjectsteaerDao;

    @Override
    public List<Subjectsteaer> getSubjectsteaers(SubjectsteaerVo subjectsteaerVo) {
        Example example = new Example(Subjectsteaer.class);
        Example.Criteria criteria = example.createCriteria();
       //Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
       // String userid = userinfo.getUserId();
      // criteria.andEqualTo(userid, userid);
        String userid =  subjectsteaerVo.getUserid();
        criteria.andEqualTo("userid", userid);
        return subjectsteaerDao.selectByExample(example);
    }

    @Override
    public List<Subjectsteaer> getSubjectsteaersujet(SubjectsteaerVo subjectsteaerVo) {
        Example example = new Example(Subjectsteaer.class);
        Example.Criteria criteria = example.createCriteria();
        String userid =  subjectsteaerVo.getUserid();
        criteria.andEqualTo("userid", userid);
        criteria.andEqualTo("subject", subjectsteaerVo.getSubject());
        criteria.andEqualTo("phasestudy", subjectsteaerVo.getPhasestudy());
        return subjectsteaerDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(Subjectsteaer.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }

        criteria.andIn("id", idArray);
        Subjectsteaer subjectsteaer = new Subjectsteaer();
        //subjectsteaer.setIsdelete(1);
        return subjectsteaerDao.updateByExampleSelective(subjectsteaer, example);
    }

}
