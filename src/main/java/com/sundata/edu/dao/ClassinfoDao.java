package com.sundata.edu.dao;

import com.sundata.edu.domain.Classinfo;
import com.sundata.edu.framework.core.Mapper;

import java.util.List;
import java.util.Map;

/**
 *  数据层
 *
 * @author whj
 * @date 2019-07-19 14:33:10
 */
public interface ClassinfoDao extends Mapper<Classinfo> {
    List<Map<String, Object>> selectClassInfoByGradeId(Map<String, Object> params);
}
