package com.sundata.edu.service;

import com.sundata.edu.domain.Deptinfo;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.DeptinfoVo;

import java.util.List;

/**
 *  服务层
 * 
 * @author whj
 * @date 2021-10-19 23:23:37
 */
public interface DeptinfoService extends Service<Deptinfo> {

    /**
    * 根据条件查询列表
    * @param deptinfoVo
    * @return
    */
    List<Deptinfo> getDeptinfos(DeptinfoVo deptinfoVo);

    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

}
