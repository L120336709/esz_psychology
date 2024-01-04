package com.sundata.edu.controller.psychology;

import java.util.List;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.util.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.sundata.edu.domain.PsyCCourse;
import com.sundata.edu.service.IPsyCCourseService;


/**
 * 课程表Controller
 * 
 * @author ljg
 * @date 2023-11-09
 */
@Controller
@RequestMapping("/psychology/course")
public class PsyCCourseController extends BaseController
{
    private String prefix = "psychology/course";

    @Autowired
    private IPsyCCourseService psyCCourseService;

    @RequiresPermissions("psychology:course:view")
    @GetMapping()
    public String course()
    {
        return prefix + "/course";
    }

    /**
     * 查询课程表列表
     */
    @RequiresPermissions("psychology:course:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyCCourse psyCCourse)
    {
        startPage();
        List<PsyCCourse> list = psyCCourseService.selectPsyCCourseList(psyCCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程表列表
     */
    @RequiresPermissions("psychology:course:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyCCourse psyCCourse)
    {
        List<PsyCCourse> list = psyCCourseService.selectPsyCCourseList(psyCCourse);
        ExcelUtil<PsyCCourse> util = new ExcelUtil<PsyCCourse>(PsyCCourse.class);
        return util.exportExcel(list, "课程表数据");
    }

    /**
     * 新增课程表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课程表
     */
    @RequiresPermissions("psychology:course:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyCCourse psyCCourse)
    {
        return toAjax(psyCCourseService.insertPsyCCourse(psyCCourse));
    }

    /**
     * 修改课程表
     */
    @RequiresPermissions("psychology:course:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyCCourse psyCCourse = psyCCourseService.selectPsyCCourseById(id);
        mmap.put("psyCCourse", psyCCourse);
        return prefix + "/edit";
    }

    /**
     * 修改保存课程表
     */
    @RequiresPermissions("psychology:course:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyCCourse psyCCourse)
    {
        return toAjax(psyCCourseService.updatePsyCCourse(psyCCourse));
    }

    /**
     * 删除课程表
     */
    @RequiresPermissions("psychology:course:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyCCourseService.deletePsyCCourseByIds(ids));
    }
}
