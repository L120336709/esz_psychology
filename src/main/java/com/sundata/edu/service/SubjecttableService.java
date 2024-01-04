package com.sundata.edu.service;

import com.sundata.edu.domain.Subjecttable;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.SubjecttableVo;

import java.util.List;

/**
 *  服务层
 *
 * @author whj
 * @date 2021-10-28 10:22:25
 */
public interface SubjecttableService extends Service<Subjecttable> {

    /**
    * 根据条件查询列表
    * @param subjecttableVo
    * @return
    */
    List<Subjecttable> getSubjecttables(SubjecttableVo subjecttableVo);
    List<Subjecttable> getSubjecttablesject(SubjecttableVo subjecttableVo);
    List<Subjecttable> getSubjecttablesclass(SubjecttableVo subjecttableVo);
    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

    List<Subjecttable> getSubjectertables(SubjecttableVo subjecttableVo);
}
