package com.sundata.edu.service;

import com.sundata.edu.domain.Gradeinfo;
import com.sundata.edu.enums.OperationEnum;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.GradeinfoVo;

import java.util.List;
import java.util.Map;

/**
 *  服务层
 *
 * @author whj
 * @date 2019-07-19 14:33:47
 */
public interface GradeinfoService extends Service<Gradeinfo> {

    /**
    * 根据条件查询列表
    * @param gradeinfoVo
    * @return
    */
    List<Gradeinfo> selectList(GradeinfoVo gradeinfoVo);

    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

    /***
     * 根据gradeId查询年级信息
     * @param gradeId
     * @return
     */
    Gradeinfo getGradeinfoByGradeId(String gradeId);

    void saveGradeinfo(Gradeinfo gradeinfo, OperationEnum operationEnum);

    List<Map<String,Object>> selectGradeInfos(Map<String, Object> params);



}
