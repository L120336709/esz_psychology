package com.sundata.edu.dao;

import com.sundata.edu.domain.SysDictData;
import com.sundata.edu.framework.core.Mapper;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

/**
 * 字典数据 数据层
 *
 * @author whj
 * @date 2019-04-17 16:06:10
 */
public interface SysDictDataDao extends Mapper<SysDictData> {

    @MapKey("dataValue")
    Map<Integer, SysDictData> getDicDataKeyValue(String typeCode);

    @MapKey("dataLabel")
    Map<String, SysDictData> getDataLabelKeyValue(String typeCode);

}
