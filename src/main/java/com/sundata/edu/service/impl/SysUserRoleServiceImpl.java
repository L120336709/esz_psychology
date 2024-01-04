package com.sundata.edu.service.impl;


import com.sundata.edu.domain.SysUserRole;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.SysUserRoleDao;
import com.sundata.edu.service.SysUserRoleService;
import com.sundata.edu.vo.SysUserRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务层实现
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
@Service("sysUserRoleService")
//@Transactional
public class SysUserRoleServiceImpl extends AbstractService<SysUserRole> implements SysUserRoleService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public List<SysUserRole> selectList(SysUserRoleVo sysUserRoleVo) {
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("", "");
        return sysUserRoleDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("userRoleId", idArray);
        SysUserRole sysUserRole = new SysUserRole();
        //sysUserRole.setIsdelete(1);
        return sysUserRoleDao.updateByExampleSelective(sysUserRole, example);
    }

    @Override
    @Transactional
    public void editUserRoles(String userId, String roleIds) {
        //先删除这个用户的所有角色，然后再添加
        if (StringUtils.isEmpty(roleIds)) {
            return;
        }
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        sysUserRoleDao.deleteByExample(example);

        String[] roleIdStrs = roleIds.split(",");
        for (String roleId : roleIdStrs) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setCreateTime(new Date());
            sysUserRole.setUpdateTime(new Date());
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(Integer.valueOf(roleId));
            sysUserRoleDao.insertSelective(sysUserRole);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAuthUsers(Integer roleId, String userIds) {
        String[] users = userIds.split(",");
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (String userId : users) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            ur.setCreateTime(new Date());
            ur.setUpdateTime(new Date());
            list.add(ur);
        }
        sysUserRoleDao.batchDelUserRole(list);
        return sysUserRoleDao.batchUserRole(list);
    }

    @Override
    public int deleteAuthUser(SysUserRole sysUserRole) {
        return sysUserRoleDao.deleteAuthUser(sysUserRole);
    }

}
