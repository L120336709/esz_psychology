package com.sundata.edu.service.impl;


import com.alibaba.fastjson.JSON;
import com.sundata.edu.dao.SysRoleMenuDao;
import com.sundata.edu.domain.SysRole;
import com.sundata.edu.domain.SysRoleMenu;
import com.sundata.edu.enums.StatusEnum;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.SysRoleDao;
import com.sundata.edu.service.SysRoleService;
import com.sundata.edu.vo.SysRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 角色 服务层实现
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
@Service("sysRoleService")
//@Transactional
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    /**
     * 根据条件查询列表
     *
     * @param sysRoleVo
     * @return
     */
    @Override
    public List<SysRole> getRoleList(SysRoleVo sysRoleVo) {
        return sysRoleDao.getRoleList(sysRoleVo);
    }

    @Override
    public int removes(String ids) {
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("roleId", idArray);
        SysRole sysRole = new SysRole();
        sysRole.setStatus(StatusEnum.DISABLED.code());
        return sysRoleDao.updateByExampleSelective(sysRole, example);
    }

    /**
     * 根据角色code查询角色信息
     *
     * @param roleCode
     * @return
     */
    @Override
    public SysRole getSysRoleByRoleCode(String roleCode) {
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleCode", roleCode);
        criteria.andEqualTo("status", StatusEnum.AVAILABLE.code());
        List<SysRole> sysRoles = sysRoleDao.selectByExample(example);
        return sysRoles.size() > 0 ? sysRoles.get(0) : null;
    }

    /**
     * 根据用户id获取角色code列表
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getRoleCodeByUserId(String userId) {
        List<SysRole> sysRoles = sysRoleDao.getRolesByUserId(userId);
        Set<String> roleCodes = new TreeSet<>();
        for (SysRole sysRole : sysRoles) {
            if(!StringUtils.isBlank(sysRole.getRoleCode())) {
                roleCodes.add(sysRole.getRoleCode());
            }
        }
        return roleCodes;
    }

    /**
     * 根据用户id获取角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRole> getRolesByUserId(String userId) {
        return sysRoleDao.getRolesByUserId(userId);
    }

    /**
     * 检查角色编码是否存在
     *
     * @param roleCode
     * @param roleId
     * @return
     */
    @Override
    public boolean checkExistRoleCode(String roleCode, Integer roleId) {
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleCode", roleCode);
        if (roleId != null) {
            criteria.andNotEqualTo("roleId", roleId);
        }
        List<SysRole> sysDictTypes = sysRoleDao.selectByExample(example);
        return sysDictTypes.size() > 0;
    }

    /**
     * 保存角色信息
     *
     * @param sysRoleVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addRole(SysRoleVo sysRoleVo) {
        SysRole sysRole = JSON.parseObject(JSON.toJSONString(sysRoleVo), SysRole.class);
        if (sysRoleDao.insertSelective(sysRole) > 0) {
            List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
            for (Integer menuId : sysRoleVo.getMenuIds()) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenus.add(sysRoleMenu);
            }
            if (sysRoleMenus.size() > 0) {
                sysRoleMenuDao.insertList(sysRoleMenus);
            }
            return 1;
        }
        return 0;
    }

    /**
     * 编辑角色信息
     *
     * @param sysRoleVo
     * @return
     */
    @Override
    public int editRole(SysRoleVo sysRoleVo) {
        SysRole sysRole = JSON.parseObject(JSON.toJSONString(sysRoleVo), SysRole.class);
        if (sysRoleDao.updateByPrimaryKeySelective(sysRole) > 0) {
            List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
            for (Integer menuId : sysRoleVo.getMenuIds()) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenus.add(sysRoleMenu);
            }
            Example example = new Example(SysRoleMenu.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId", sysRole.getRoleId());
            sysRoleMenuDao.deleteByExample(example);
            if (sysRoleMenus.size() > 0) {
                sysRoleMenuDao.insertList(sysRoleMenus);
            }
            return 1;
        }
        return 0;
    }

    @Override
    public List<SysRole> getRoleListByLevel(Integer minLevel) {
        return  sysRoleDao.getRoleListByLevel(minLevel);
    }


}
