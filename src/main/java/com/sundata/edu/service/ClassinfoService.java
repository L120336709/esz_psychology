package com.sundata.edu.service;

import com.sundata.edu.domain.Classinfo;
import com.sundata.edu.enums.OperationEnum;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.ClassinfoVo;

import java.util.List;
import java.util.Map;

/**
 *  服务层
 *
 * @author whj
 * @date 2019-07-19 14:33:10
 */
public interface ClassinfoService extends Service<Classinfo> {

    /**
    * 根据条件查询列表
    * @param classinfoVo
    * @return
    */
    List<Classinfo> selectList(ClassinfoVo classinfoVo);

    List<Classinfo> selectListbtapp(ClassinfoVo classinfoVo);

    List<Classinfo> selectListapp(ClassinfoVo classinfoVo);
    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

    /***
     * 根据classId查询年级信息
     * @param classId
     * @return
     */
    Classinfo getClassinfoByClassId(String classId);

    void saveClassinfo(Classinfo classinfo, OperationEnum operationEnum);

    List<Map<String, Object>> selectClassInfoByGradeId(Map<String, Object> params);

    List<Classinfo> selectListclass(ClassinfoVo classinfoVo);
}
