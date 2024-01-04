package com.sundata.edu.controller;

import com.sundata.edu.domain.Orginfo;
import com.sundata.edu.service.OrginfoService;
import com.sundata.edu.util.ShiroUtils;
import com.sundata.edu.vo.*;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构 信息操作处理
 *
 * @author whj
 * @date 2019-04-19 16:35:58
 */
@Controller
@RequestMapping("/orginfo")
public class OrginfoController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "orginfo";

    @Autowired
    private OrginfoService orginfoService;

    //@RequiresPermissions("orginfo:view")
    @GetMapping()
    public String orginfo() {
        return prefix + "/list";
    }

    /**
     * 查询机构列表
     */
    //@RequiresPermissions("orginfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrginfoVo orginfoVo) {
        startPage();
        return getDataTable(orginfoService.selectList(orginfoVo));
    }

    /**
     * 新增机构
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存机构
     */
    //@RequiresPermissions("orginfo:add")
    //@Log(title = "机构", action = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(Orginfo orginfo) {
        return toAjax(orginfoService.insertSelective(orginfo));
    }

    /**
     * 查看机构详情数据
     *
     * @param orgId 机构id
     * @param model 传值数据
     * @return
     */
    @GetMapping("/detail/{orgId}")
    public String detail(@PathVariable("orgId") String orgId, ModelMap model) {
        model.put("orginfo", orginfoService.selectByPrimaryKey(orgId));
        return prefix + "/detail";
    }

    /**
     * 修改机构
     */
    @GetMapping("/edit/{orgId}")
    public String edit(@PathVariable("orgId") String orgId, ModelMap model) {
        model.put("orginfo", orginfoService.selectByPrimaryKey(orgId));
        return prefix + "/edit";
    }

    /**
     * 修改保存机构
     */
    //@RequiresPermissions("orginfo:edit")
    //@Log(title = "机构", action = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(Orginfo orginfo) {
        return toAjax(orginfoService.updateByPrimaryKeySelective(orginfo));
    }

    /**
     * 删除机构
     */
    //@RequiresPermissions("orginfo:remove")
    //@Log(title = "机构", action = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(orginfoService.removes(ids));
    }

    /**
     * 加载列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        return orginfoService.getOrgInfoTreeByParentOrgId(ShiroUtils.getOrgId());
    }

    @GetMapping("/selectOrgInfoTree/{orgId}")
    public String selectDeptTree(@PathVariable("orgId") String deptId, ModelMap mmap) {
        mmap.put("orgInfo", orginfoService.getOrgInfoByOrgId(deptId));
        return prefix + "/tree";
    }

    @GetMapping("/treeAllData")
    @ResponseBody
    public List<Ztree> treeAllData() {
        return orginfoService.getOrgInfoTree();
    }


    @GetMapping("/all")
    @ResponseBody
    public AjaxResult all() {
        return AjaxResult.success("查询成功").put("data", orginfoService.getOrgInfos());
    }



}
