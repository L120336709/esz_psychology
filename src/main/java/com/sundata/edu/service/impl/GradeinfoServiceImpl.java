package com.sundata.edu.service.impl;


import com.sundata.edu.domain.Gradeinfo;
import com.sundata.edu.enums.OperationEnum;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.GradeinfoDao;
import com.sundata.edu.service.GradeinfoService;
import com.sundata.edu.vo.GradeinfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务层实现
 *
 * @author whj
 * @date 2019-07-19 14:33:47
 */
@Service("gradeinfoService")
//@Transactional
public class GradeinfoServiceImpl extends AbstractService<Gradeinfo> implements GradeinfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GradeinfoDao gradeinfoDao;

    @Override
    public List<Gradeinfo> selectList(GradeinfoVo gradeinfoVo) {
        Example example = new Example(Gradeinfo.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("", "");
        return gradeinfoDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(Gradeinfo.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("id", idArray);
        Gradeinfo gradeinfo = new Gradeinfo();
        //gradeinfo.setIsdelete(1);
        return gradeinfoDao.updateByExampleSelective(gradeinfo, example);
    }

    @Override
    public Gradeinfo getGradeinfoByGradeId(String gradeId) {
        Example example = new Example(Gradeinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("gradeId", gradeId);
        List<Gradeinfo> gradeinfos = gradeinfoDao.selectByExample(example);
        if (gradeinfos != null && gradeinfos.size() > 0) {
            return gradeinfos.get(0);
        }
        return null;
    }

    @Override
    public void saveGradeinfo(Gradeinfo gradeinfo, OperationEnum operationEnum) {
        if (operationEnum == OperationEnum.INSERT) {
            gradeinfoDao.insertSelective(gradeinfo);
        } else {
            Example example = new Example(Gradeinfo.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("gradeId", gradeinfo.getGradeId());
            gradeinfoDao.updateByExampleSelective(gradeinfo, example);
        }
    }

    @Override
    public List<Map<String, Object>> selectGradeInfos(Map<String, Object> params) {
        return gradeinfoDao.selectGradeInfos(params);
    }

}
