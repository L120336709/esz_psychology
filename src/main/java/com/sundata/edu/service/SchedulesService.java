package com.sundata.edu.service;

import com.sundata.edu.domain.Schedules;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.SchedulesVo;

import java.util.List;

/**
 *  服务层
 *
 * @author whj
 * @date 2021-10-27 10:03:25
 */
public interface SchedulesService extends Service<Schedules> {

    /**
    * 根据条件查询列表
    * @param schedulesVo
    * @return
    */
    List<Schedules> getSchedulessbtes(SchedulesVo schedulesVo);
    List<Schedules> getScheduless(SchedulesVo schedulesVo);
    List<Schedules> getSchedulesslist(SchedulesVo schedulesVo);
    List<Schedules> getSchedulesstablelist(SchedulesVo schedulesVo);
    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

}
