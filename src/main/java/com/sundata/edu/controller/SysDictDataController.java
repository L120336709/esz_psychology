package com.sundata.edu.controller;

import com.sundata.edu.domain.SysDictData;
import com.sundata.edu.enums.StatusEnum;
import com.sundata.edu.service.SysDictDataService;
import com.sundata.edu.service.SysDictTypeService;
import com.sundata.edu.vo.SysDictDataVo;
import com.sundata.edu.vo.SysDictTypeVo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 字典数据 信息操作处理
 *
 * @author whj
 * @date 2018-12-22 17:52:50
 */
@Controller
@RequestMapping("/sysDictData")
public class SysDictDataController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "sysDictData";

    @Autowired
    private SysDictTypeService sysDictTypeService;

    @Autowired
    private SysDictDataService sysDictDataService;

    @RequiresPermissions("sysDictType:view")
    @GetMapping("/list/{typeId}")
    public String sysDictData(@PathVariable("typeId") Integer typeId, ModelMap modelMap) {
        modelMap.put("dict", sysDictTypeService.selectByPrimaryKey(typeId));
        SysDictTypeVo sysDictTypeVo = new SysDictTypeVo();
        sysDictTypeVo.setStatus(StatusEnum.AVAILABLE.code());
        modelMap.put("dictList", sysDictTypeService.selectList(sysDictTypeVo));
        return prefix + "/list";
    }

    /**
     * 查询字典数据列表
     */
    @RequiresPermissions("sysDictType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysDictDataVo sysDictDataVo) {
        startPage();
        return getDataTable(sysDictDataService.selectList(sysDictDataVo));
    }

    /**
     * 新增字典数据
     */
    @GetMapping("/add/{typeCode}")
    public String add(@PathVariable("typeCode") String typeCode, ModelMap modelMap) {
        modelMap.put("typeCode", typeCode);
        return prefix + "/add";
    }

    /**
     * 新增保存字典数据
     */
    @RequiresPermissions("sysDictType:add")
    //@Log(title = "字典数据", action = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(SysDictData sysDictData) {
        return toAjax(sysDictDataService.insertSelective(sysDictData));
    }

    /**
     * 修改字典数据
     */
    @GetMapping("/edit/{dictDataId}")
    public String edit(@PathVariable("dictDataId") Integer dictDataId, ModelMap model) {
        model.put("sysDictData", sysDictDataService.selectByPrimaryKey(dictDataId));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典数据
     */
    @RequiresPermissions("sysDictType:edit")
    //@Log(title = "字典数据", action = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(SysDictData sysDictData) {
        return toAjax(sysDictDataService.updateByPrimaryKeySelective(sysDictData));
    }

    /**
     * 删除字典数据
     */
    @RequiresPermissions("sysDictType:remove")
    //@Log(title = "字典数据", action = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysDictDataService.removes(ids));
    }

}
