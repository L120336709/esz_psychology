package com.sundata.edu.service;

import com.sundata.edu.domain.Mz;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.MzVo;

import java.util.List;

/**
 *  服务层
 *
 * @author whj
 * @date 2020-05-12 16:08:47
 */
public interface MzService extends Service<Mz> {

    /**
    * 根据条件查询列表
    * @param mzVo
    * @return
    */
    List<Mz> getMzs(MzVo mzVo);

    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

}
