package com.sundata.edu.service;

import com.sundata.edu.domain.Subjectsteaer;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.SubjectsteaerVo;

import java.util.List;

/**
 *  服务层
 *
 * @author whj
 * @date 2021-10-28 14:34:38
 */
public interface SubjectsteaerService extends Service<Subjectsteaer> {

    /**
    * 根据条件查询列表
    * @param subjectsteaerVo
    * @return
    */
    List<Subjectsteaer> getSubjectsteaers(SubjectsteaerVo subjectsteaerVo);
    List<Subjectsteaer> getSubjectsteaersujet(SubjectsteaerVo subjectsteaerVo);
    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

}
