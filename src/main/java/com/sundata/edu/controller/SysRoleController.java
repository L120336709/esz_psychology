package com.sundata.edu.controller;

import com.sundata.edu.domain.SysRole;
import com.sundata.edu.domain.SysUserRole;
import com.sundata.edu.service.*;
import com.sundata.edu.util.ShiroUtils;
import com.sundata.edu.vo.SysRoleVo;
import com.sundata.edu.vo.UserinfoVo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色 信息操作处理
 *
 * @author whj
 * @date 2018-12-20 20:33:29
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "sysRole";

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private OrginfoService orginfoService;

    @RequiresPermissions("sysRole:view")
    @GetMapping()
    public String sysRole() {
        return prefix + "/list";
    }

    /**
     * 查询角色列表
     */
    @RequiresPermissions("sysRole:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysRoleVo sysRoleVo) {
        //获取本机构的角色，如果登录用户有系统管理员角色，还要加上默认角色
        sysRoleVo.setOrgId(ShiroUtils.getOrgId());

        Map<String, Object> map = new HashMap<>(2);
        map.put("hasAdmin", ShiroUtils.getSubjct().hasRole("admin") ? "1" : "0");
        sysRoleVo.setParams(map);

        startPage();
        return getDataTable(sysRoleService.getRoleList(sysRoleVo));
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存角色
     */
    @RequiresPermissions("sysRole:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(SysRoleVo sysRoleVo) {
        sysRoleVo.setOrgId(ShiroUtils.getOrgId());
        sysRoleVo.setUserId(ShiroUtils.getUserId());
        sysRoleVo.setCreated(new Date());
        sysRoleVo.setUpdated(new Date());
        return toAjax(sysRoleService.addRole(sysRoleVo));
    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Integer roleId, ModelMap model) {
        model.put("sysRole", sysRoleService.selectByPrimaryKey(roleId));
        return prefix + "/edit";
    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("sysRole:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(SysRoleVo sysRoleVo) {
        sysRoleVo.setUpdated(new Date());
        return toAjax(sysRoleService.editRole(sysRoleVo));
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("sysRole:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysRoleService.removes(ids));
    }

    /**
     * 校验角色编码是否存在
     */
    @PostMapping("/checkExistRoleCode")
    @ResponseBody
    public boolean checkExistRoleCode(String roleCode, Integer roleId) {
        return sysRoleService.checkExistRoleCode(roleCode, roleId);
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Map<String, Object>> roleMenuTreeData(SysRole role) {
        return sysMenuService.getRoleMenuTreeData(role);
    }

    @GetMapping("/authUser/{roleId}")
    public String authUser(@PathVariable("roleId") Integer roleId, ModelMap model) {
        model.put("role", sysRoleService.selectByPrimaryKey(roleId));
        model.put("orginfos", orginfoService.getOrgInfosByParentOrgId(ShiroUtils.getOrgId()));
        return prefix + "/authUser";
    }

    @PostMapping("/authUser/{roleId}/users")
    @ResponseBody
    public TableDataInfo getRoleUsers(@PathVariable(value = "roleId") Integer roleId, UserinfoVo userinfoVo) {
        if (StringUtils.isBlank(userinfoVo.getOrgId())) {
            userinfoVo.setOrgId(ShiroUtils.getOrgId());
        }
        startPage();
        return getDataTable(userinfoService.getRoleUsers(roleId, userinfoVo));
    }

    @GetMapping("/authUser/{roleId}/selectUser")
    public String selectUser(@PathVariable("roleId") Integer roleId, ModelMap model) {
        model.put("role", sysRoleService.selectByPrimaryKey(roleId));
        //有系统管理员角色的用户可以查本机构及下级机构，其他用户只能查本机机构
        model.put("hasAdmin", ShiroUtils.getSubjct().hasRole("admin") ? 1 : 0);
        model.put("orginfos", orginfoService.getOrgInfosByParentOrgId(ShiroUtils.getOrgId()));
        return prefix + "/selectUser";
    }

    @PostMapping("/authUser/{roleId}/unuser")
    @ResponseBody
    public TableDataInfo getRoleUnUsers(@PathVariable(value = "roleId") Integer roleId, UserinfoVo userinfoVo) {
        if(ShiroUtils.getSubjct().hasRole("admin")) {
            //管理员角色查询本机构和下级机构
            Map<String, Object> map = new HashMap<>(2);
            map.put("hasAdmin", '1');
            map.put("orgId", ShiroUtils.getOrgId());
            userinfoVo.setParams(map);
        } else {
            //查询本机构
            if(StringUtils.isBlank(userinfoVo.getOrgId())) {
                userinfoVo.setOrgId(ShiroUtils.getOrgId());
            }
        }
        startPage();
        return getDataTable(userinfoService.getRoleUnUsers(roleId, userinfoVo));
    }

    @PostMapping("/authUser/{roleId}/set")
    @ResponseBody
    public AjaxResult authUserAll(@PathVariable(value = "roleId") Integer roleId, String userIds) {
        return toAjax(sysUserRoleService.insertAuthUsers(roleId, userIds));
    }

    @PostMapping("/authUser/{roleId}/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(@PathVariable(value = "roleId") Integer roleId, String userId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        return toAjax(sysUserRoleService.deleteAuthUser(sysUserRole));
    }

}
