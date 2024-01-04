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
import com.sundata.edu.domain.PsyXExercises;
import com.sundata.edu.service.IPsyXExercisesService;

/**
 * 习题表Controller
 * 
 * @author ljg
 * @date 2023-11-24
 */
@Controller
@RequestMapping("/psychology/exercises")
public class PsyXExercisesController extends BaseController
{
    private String prefix = "psychology/exercises";

    @Autowired
    private IPsyXExercisesService psyXExercisesService;

    @RequiresPermissions("psychology:exercises:view")
    @GetMapping()
    public String exercises()
    {
        return prefix + "/exercises";
    }

    /**
     * 查询习题表列表
     */
    @RequiresPermissions("psychology:exercises:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PsyXExercises psyXExercises)
    {
        startPage();
        List<PsyXExercises> list = psyXExercisesService.selectPsyXExercisesList(psyXExercises);
        return getDataTable(list);
    }

    /**
     * 导出习题表列表
     */
    @RequiresPermissions("psychology:exercises:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PsyXExercises psyXExercises)
    {
        List<PsyXExercises> list = psyXExercisesService.selectPsyXExercisesList(psyXExercises);
        ExcelUtil<PsyXExercises> util = new ExcelUtil<PsyXExercises>(PsyXExercises.class);
        return util.exportExcel(list, "习题表数据");
    }

    /**
     * 新增习题表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存习题表
     */
    @RequiresPermissions("psychology:exercises:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PsyXExercises psyXExercises)
    {
        return toAjax(psyXExercisesService.insertPsyXExercises(psyXExercises));
    }

    /**
     * 修改习题表
     */
    @RequiresPermissions("psychology:exercises:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PsyXExercises psyXExercises = psyXExercisesService.selectPsyXExercisesById(id);
        mmap.put("psyXExercises", psyXExercises);
        return prefix + "/edit";
    }

    /**
     * 修改保存习题表
     */
    @RequiresPermissions("psychology:exercises:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PsyXExercises psyXExercises)
    {
        return toAjax(psyXExercisesService.updatePsyXExercises(psyXExercises));
    }

    /**
     * 删除习题表
     */
    @RequiresPermissions("psychology:exercises:remove")
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(psyXExercisesService.deletePsyXExercisesByIds(ids));
    }
}
