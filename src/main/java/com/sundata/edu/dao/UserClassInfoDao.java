package com.sundata.edu.dao;

import com.sundata.edu.domain.UserClassInfo;
import com.sundata.edu.framework.core.Mapper;

import java.util.List;
import java.util.Map;

public interface UserClassInfoDao extends Mapper<UserClassInfo> {

    /**
     * 查询分配了班级的老师
     * @return
     */
    List<Map<String, String>> selectUsersList();

    /**
     * 查询老师管理的班级
     * @param params
     * @return
     */
    List<Map<String, String>> getUsersClasses(Map<String, String> params);

    /**
     * 新增老师班级信息
     * @param userClassInfos
     * @return
     */
    int addUserClassInfos(List<UserClassInfo> userClassInfos);

    /**
     * 删除老师与班级的关联关系
     * @param params
     * @return
     */
    int deleteUserClassInfos(Map<String, String> params);

    /**
     * 判断是否存在
     * @param params
     * @return
     */
    int checkUserClassInfo(Map<String, String> params);

    /**
     *  根据用户id查询用户班级
     * @param params
     * @return
     */
    List<String> queryClassIdsByUserId(Map<String, String> params);
}
