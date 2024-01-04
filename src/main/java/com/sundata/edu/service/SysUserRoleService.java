package com.sundata.edu.service;

import com.sundata.edu.domain.SysUserRole;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.SysUserRoleVo;

import java.util.List;

/**
 * 服务层
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
public interface SysUserRoleService extends Service<SysUserRole> {

    /**
     * 根据条件查询列表
     *
     * @param sysUserRoleVo
     * @return
     */
    List<SysUserRole> selectList(SysUserRoleVo sysUserRoleVo);

    /**
     * 逻辑删除 修改isdelete字段
     *
     * @param ids
     * @return
     */
    int removes(String ids);

    void editUserRoles(String userId, String roleIds);

    int insertAuthUsers(Integer roleId, String userIds);

    int deleteAuthUser(SysUserRole sysUserRole);

}
