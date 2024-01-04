package com.sundata.edu.dao;

import com.sundata.edu.domain.Gradeinfo;
import com.sundata.edu.framework.core.Mapper;

import java.util.List;
import java.util.Map;

/**
 *  数据层
 *
 * @author whj
 * @date 2019-07-19 14:33:47
 */
public interface GradeinfoDao extends Mapper<Gradeinfo> {

    List<Map<String,Object>> selectGradeInfos(Map<String, Object> params);
}
