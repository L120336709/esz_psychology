package com.sundata.edu.service;

import com.sundata.edu.domain.SysDictType;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.SysDictTypeVo;

import java.util.List;

/**
 * 字段类型 服务层
 *
 * @author whj
 * @date 2019-04-17 16:06:10
 */
public interface SysDictTypeService extends Service<SysDictType> {

    /**
    * 根据条件查询列表
    * @param sysDictTypeVo
    * @return
    */
    List<SysDictType> selectList(SysDictTypeVo sysDictTypeVo);

    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

    /**
     * 根据dictType检查是否存在
     *
     * @param typeCode
     * @param typeId
     * @return
     */
    boolean checkExistSysDictType(String typeCode, Integer typeId);
}
