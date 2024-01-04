package com.sundata.edu.dao;

import com.sundata.edu.domain.Studentinfo;
import com.sundata.edu.framework.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  数据层
 *
 * @author whj
 * @date 2019-12-17 16:47:41
 */
public interface StudentinfoDao extends Mapper<Studentinfo> {


    /**
     * 批量更新数据
     * @param Studentinfo
     * @return
     */
    int updateStudentinfoList(@Param("list") List<Studentinfo> Studentinfo);
}
