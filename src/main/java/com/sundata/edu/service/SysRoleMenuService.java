package com.sundata.edu.service;

import com.sundata.edu.domain.SysRoleMenu;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.vo.SysRoleMenuVo;

import java.util.List;

/**
 * 角色菜单 服务层
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
public interface SysRoleMenuService extends Service<SysRoleMenu> {

    /**
    * 根据条件查询列表
    * @param sysRoleMenuVo
    * @return
    */
    List<SysRoleMenu> selectList(SysRoleMenuVo sysRoleMenuVo);

    /**
    * 逻辑删除 修改isdelete字段
    *
    * @param ids
    * @return
    */
    int removes(String ids);

}
