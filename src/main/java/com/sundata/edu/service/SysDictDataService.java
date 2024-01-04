package com.sundata.edu.service;

import com.sundata.edu.domain.SysDictData;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.SysDictDataVo;

import java.util.List;

/**
 * 字典数据 服务层
 *
 * @author whj
 * @date 2019-04-17 16:06:10
 */
public interface SysDictDataService extends Service<SysDictData> {

    /**
    * 根据条件查询列表
    * @param sysDictDataVo
    * @return
    */
    List<SysDictData> selectList(SysDictDataVo sysDictDataVo);

    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);


    /**
     * 根据类型获取字典数据列表
     *
     * @param typeCode
     * @return
     */
    List<SysDictData> getDictDataByTypeCode(String typeCode);

    /**
     * 根据字段类型和字段值获取字典标题
     *
     * @param typeCode
     * @param dataValue
     * @return
     */
    String getDataLabel(String typeCode, String dataValue);
}
