package com.sundata.edu.controller;

import com.sundata.edu.domain.SysRole;
import com.sundata.edu.service.SysRoleService;
import com.sundata.edu.service.SysUserRoleService;
import com.sundata.edu.util.ShiroUtils;
import com.sundata.edu.vo.SysRoleVo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/***
 * 权限管理
 */
@Controller
@RequestMapping("/sysAuthority")
public class SysAuthorityController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "sysAuthority";

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping()
    public String sysAuthority(Model model) {
        model.addAttribute("orgId", ShiroUtils.getOrgId());
        return prefix + "/list";
    }

    @GetMapping("/{userId}")
    public String authorAction(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("userId", StringUtils.isBlank(userId) ? ShiroUtils.getUserId() : userId);

        //获取本机构的角色，如果登录用户有系统管理员角色，还要加上默认角色
        SysRoleVo srv = new SysRoleVo();
        srv.setStatus(1);
        srv.setOrgId(ShiroUtils.getOrgId());
        Map<String, Object> map = new HashMap<>(2);
        map.put("hasAdmin", ShiroUtils.getSubjct().hasRole("admin") ? "1" : "0");
        srv.setParams(map);
        model.addAttribute("roleList", sysRoleService.getRoleList(srv));

        //已选择角色
        List<SysRole> rolesByUserId = sysRoleService.getRolesByUserId(userId);
        List<Integer> roleIds = new ArrayList<>();
        for (SysRole sysRole : rolesByUserId) {
            roleIds.add(sysRole.getRoleId());
        }
        model.addAttribute("checkRoles", StringUtils.join(roleIds, ","));
        return prefix + "/auth";
    }

    /**
     * 批量修改角色权限
     *
     * @param id      用户id
     * @param roleIds 角色id(多个以逗号分开)
     */
    @PostMapping("/editUserRoles")
    @ResponseBody
    public AjaxResult editRoles(String id, String roleIds) {
        sysUserRoleService.editUserRoles(id, roleIds);
        return AjaxResult.success("修改成功");
    }

    @RequestMapping("/getRole")
    @ResponseBody
    public AjaxResult getRole(@RequestParam(required = false) String id) {
        List<SysRole> roleList = sysRoleService.getRoleList(new SysRoleVo());
        Set<String> checkRoles = sysRoleService.getRoleCodeByUserId(id);
        return AjaxResult.success("获取数据成功")
                .put("roleList", roleList)
                .put("checkRoles", checkRoles);
    }
}
