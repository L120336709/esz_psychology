package com.sundata.edu.service;

import com.sundata.edu.domain.Studentinfo;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.StudentinfoVo;

import java.util.List;

/**
 *  服务层
 *
 * @author whj
 * @date 2021-11-02 15:29:17
 */
public interface StudentinfoService extends Service<Studentinfo> {

    /**
    * 根据条件查询列表
    * @param studentinfoVo
    * @return
    */
    List<Studentinfo> getStudentinfos(StudentinfoVo studentinfoVo);

    List<Studentinfo> getStudentinfoslist(StudentinfoVo studentinfoVo);
    List<Studentinfo> getStudentinfosjoblist(StudentinfoVo studentinfoVo);
    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

     int addStudentinfo();


    int updateStudentinfoList(List<Studentinfo> studentinfos);
}
