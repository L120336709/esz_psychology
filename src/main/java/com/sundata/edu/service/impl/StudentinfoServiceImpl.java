package com.sundata.edu.service.impl;


import com.sundata.edu.domain.Studentinfo;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.StudentinfoDao;
import com.sundata.edu.service.StudentinfoService;
import com.sundata.edu.service.UserinfoService;
import com.sundata.edu.vo.StudentinfoVo;
import com.sundata.edu.vo.UserinfoVo;
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
 * @date 2021-11-02 15:29:17
 */
@Service("studentinfoService")
//@Transactional
public class StudentinfoServiceImpl extends AbstractService<Studentinfo> implements StudentinfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private StudentinfoDao studentinfoDao;

    @Autowired
    private StudentinfoService studentinfoService;

    @Autowired
    private UserinfoService userinfoService;

    @Override
    public List<Studentinfo> getStudentinfos(StudentinfoVo studentinfoVo) {
        Example example = new Example(Studentinfo.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("", "");
        return studentinfoDao.selectByExample(example);
    }

    @Override
    public List<Studentinfo> getStudentinfoslist(StudentinfoVo studentinfoVo) {
        Example example = new Example(Studentinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("schoolcode", studentinfoVo.getSchoolcode());
        criteria.andEqualTo("gradeCode", studentinfoVo.getGradeCode());
        criteria.andEqualTo("classCode", studentinfoVo.getClassCode());
        return studentinfoDao.selectByExample(example);
    }

    @Override
    public List<Studentinfo> getStudentinfosjoblist(StudentinfoVo studentinfoVo) {
        Example example = new Example(Studentinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("schoolcode", studentinfoVo.getSchoolcode());
        criteria.andEqualTo("gradeCode", studentinfoVo.getGradeCode());
        criteria.andEqualTo("classCode", studentinfoVo.getClassCode());
        criteria.andEqualTo("jobnumber", studentinfoVo.getJobnumber());
        return studentinfoDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(Studentinfo.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("userId", idArray);
        Studentinfo studentinfo = new Studentinfo();
        //studentinfo.setIsdelete(1);
        return studentinfoDao.updateByExampleSelective(studentinfo, example);
    }

    @Override
    public int addStudentinfo()
    {
        UserinfoVo userinfovo=new UserinfoVo();
        userinfovo.setIdentity(102);
        List<UserinfoVo> userinfovos=userinfoService.selectList(userinfovo);
        List<Studentinfo> studentinfos = new ArrayList<>();
        if(userinfovos.size()>0) {
            for (UserinfoVo userinfo : userinfovos) {
                //添加学生信息
                if (userinfo.getIdentity() == 102) {
                    Studentinfo studentinfo = new Studentinfo();
                    studentinfo.setUserId(userinfo.getUserId());
                    studentinfo.setStudentName(userinfo.getRealName());
                    studentinfo.setSchoolcode(userinfo.getOrgId());
                    studentinfo.setGradeCode(userinfo.getGradeId());
                    studentinfo.setClassCode(userinfo.getClassId());
                    if(studentinfo.getUserId()!=null){
                        studentinfos.add(studentinfo);
                    }
                }
            }
            for (Studentinfo s:studentinfos
            ) {
                studentinfoDao.insertSelective(s);
            }
            //studentinfoService.insertList(studentinfos);
            //studentinfoDao.insertList(studentinfos);
        }
        return 1;
    }

    @Override
    public int updateStudentinfoList(List<Studentinfo> studentinfos){
        return  studentinfoDao.updateStudentinfoList(studentinfos);
    }
}
