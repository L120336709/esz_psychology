package com.sundata.edu.service.impl;


import com.sundata.edu.dao.ClassinfoDao;
import com.sundata.edu.dao.GradeinfoDao;
import com.sundata.edu.dao.UserinfoDao;
import com.sundata.edu.domain.Classinfo;
import com.sundata.edu.domain.Gradeinfo;
import com.sundata.edu.enums.OperationEnum;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.service.ClassinfoService;
import com.sundata.edu.vo.ClassinfoVo;
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
 * @date 2019-07-19 14:33:10
 */
@Service("classinfoService")
//@Transactional
public class ClassinfoServiceImpl extends AbstractService<Classinfo> implements ClassinfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassinfoDao classinfoDao;

    @Autowired
    private UserinfoDao userinfoDao;

    @Autowired
    private GradeinfoDao gradeinfoDao;

    @Override
    public List<Classinfo> selectList(ClassinfoVo classinfoVo) {
        Example example = new Example(Classinfo.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("", "");
        return classinfoDao.selectByExample(example);
    }

    @Override
    public List<Classinfo> selectListbtapp(ClassinfoVo classinfoVo) {
        Example example = new Example(Classinfo.class);
        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("schoolId", classinfoVo.getSchoolId());
        criteria.andEqualTo("classId", classinfoVo.getClassId());
//        criteria.andEqualTo("gradeId", classinfoVo.getGradeId());
        return classinfoDao.selectByExample(example);
    }

    @Override
    public List<Classinfo> selectListapp(ClassinfoVo classinfoVo) {
        Example example = new Example(Classinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("schoolId", classinfoVo.getSchoolId());
        example.setOrderByClause("grade_code desc");
        return classinfoDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(Classinfo.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("id", idArray);
        Classinfo classinfo = new Classinfo();
        //classinfo.setIsdelete(1);
        return classinfoDao.updateByExampleSelective(classinfo, example);
    }

    @Override
    public Classinfo getClassinfoByClassId(String classId) {
        Example example = new Example(Classinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("classId", classId);
        List<Classinfo> classinfos = classinfoDao.selectByExample(example);
        if (classinfos != null && classinfos.size() > 0) {
            return classinfos.get(0);
        }
        return null;
    }

    @Override
    public void saveClassinfo(Classinfo classinfo, OperationEnum operationEnum) {
        //判断gradeinfo是否有数据
        Gradeinfo gradeinfo = new Gradeinfo();
        gradeinfo.setGradeId(classinfo.getGradeId());
        Gradeinfo gradeinfoData = gradeinfoDao.selectOne(gradeinfo);
        if(gradeinfoData==null){
            Gradeinfo newGradeInfo = new Gradeinfo();
            newGradeInfo.setGradeId(classinfo.getGradeId());
            newGradeInfo.setSchoolId(classinfo.getSchoolId());
            newGradeInfo.setGradeCode(classinfo.getGradeCode());
            newGradeInfo.setGradeName(classinfo.getGradeName());
            gradeinfoDao.insertSelective(newGradeInfo);
        }
        if (operationEnum == OperationEnum.INSERT) {
            classinfoDao.insertSelective(classinfo);
        } else {
            Example example = new Example(Classinfo.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("classId", classinfo.getClassId());
            classinfoDao.updateByExampleSelective(classinfo, example);
        }


    }

    @Override
    public List<Map<String, Object>> selectClassInfoByGradeId(Map<String, Object> params) {
        return classinfoDao.selectClassInfoByGradeId(params);
    }

    @Override
    public List<Classinfo> selectListclass(ClassinfoVo classinfoVo) {
        Example example = new Example(Classinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("schoolId", classinfoVo.getSchoolId());
        criteria.andEqualTo("gradeId", classinfoVo.getGradeId());
        return classinfoDao.selectByExample(example);
    }

}
