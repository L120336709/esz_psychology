package com.sundata.edu.service;

import com.sundata.edu.domain.UserClassInfo;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.UserClassInfoVo;

import java.util.List;
import java.util.Map;

public interface UserClassInfoService extends Service<UserClassInfo>{

    /**
     * 查询分配了班级的老师
     * @return
     */
    List<UserClassInfo> selectUserClassInfoList(UserClassInfoVo userClassInfoVo);


    List<Map<String, Object>> getGradeClassTreeData(String teacherId);

    int addUserClassInfos(String teacherId, String ids);

    int deleteUserClassInfos(String teacherId);

    boolean checkUserClassInfo(String teacherId);

    List<String> queryClassIdsByUserId(Map<String, String> params);
}
