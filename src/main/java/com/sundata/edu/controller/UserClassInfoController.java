package com.sundata.edu.controller;

import com.sundata.edu.domain.UserClassInfo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.UserClassInfoService;
import com.sundata.edu.vo.UserClassInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 班主任控制器
 */
@Controller
@RequestMapping("/userClassInfo")
public class UserClassInfoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "userClassInfo";

    @Autowired
    private UserClassInfoService userClassInfoService;

    @RequiresPermissions("userClassInfo:view")
    @GetMapping()
    public String userClassInfo() {
        return prefix + "/list";
    }

    /**
     * 查询列表
     */
    @RequiresPermissions("userClassInfo:add")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserClassInfoVo userClassInfoVo) {
        startPage();

        List<UserClassInfo> userClassInfos = userClassInfoService.selectUserClassInfoList(userClassInfoVo);

        return getDataTable(userClassInfos);
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleClassTreeData")
    @ResponseBody
    public List<Map<String, Object>>  roleClassTreeData(String teacherId) {
        return userClassInfoService.getGradeClassTreeData(teacherId);
    }

    /**
     * 新增
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequiresPermissions("userClassInfo:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(String teacherId, String ids) {
        if (StringUtils.isEmpty(ids)){
            return toAjax(1);
        }

        int i = userClassInfoService.addUserClassInfos(teacherId, ids);

        return toAjax(i);
    }

    /**
     * 修改活动
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String teacherId, ModelMap model) {

        System.out.println(teacherId);
        UserClassInfo info = new UserClassInfo();
        info.setTeacherId(teacherId);
        model.put("userClassInfo",info);
        return prefix + "/edit";
    }

    /**
     * 修改保存
     */
    @RequiresPermissions("userClassInfo:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(String teacherId, String ids) {
        //删除老师与班级的关联关系
        userClassInfoService.deleteUserClassInfos(teacherId);
        //新增新的老师与班级的关联关系
        int i = userClassInfoService.addUserClassInfos(teacherId, ids);
        return toAjax(i);
    }

    @RequiresPermissions("userClassInfo:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(userClassInfoService.deleteUserClassInfos(ids));
    }

    @PostMapping("/checkUserClassInfo")
    @ResponseBody
    public boolean checkUserClassInfo(String teacherId){
        return userClassInfoService.checkUserClassInfo(teacherId);
    }

}
