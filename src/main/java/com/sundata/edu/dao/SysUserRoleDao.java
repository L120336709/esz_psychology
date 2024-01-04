package com.sundata.edu.dao;

import com.sundata.edu.domain.SysUserRole;
import com.sundata.edu.framework.core.Mapper;

import java.util.List;

/**
 *  数据层
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
public interface SysUserRoleDao extends Mapper<SysUserRole> {

    int batchUserRole(List<SysUserRole> list);
    int batchDelUserRole(List<SysUserRole> list);

    int deleteAuthUser(SysUserRole sysUserRole);

}
