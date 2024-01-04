package com.sundata.edu.controller;

import com.sundata.edu.domain.Subjecttable;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.vo.SubjecttableVo;
import com.sundata.edu.service.SubjecttableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 *  信息操作处理
 *
 * @author whj
 * @date 2021-10-28 10:22:25
 */
@Controller
@RequestMapping("/subjecttable")
public class SubjecttableController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "subjecttable";

	@Autowired
	private SubjecttableService subjecttableService;

	//@RequiresPermissions("subjecttable:view")
	@GetMapping()
	public String subjecttable()
	{
	    return prefix + "/list";
	}

	/**
	 * 查询列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SubjecttableVo subjecttableVo)
	{
        startPage();
        return getDataTable(subjecttableService.getSubjecttables(subjecttableVo));
	}

	/**
	 * 新增
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}

	/**
	 * 新增保存
	 */
	//@RequiresPermissions("subjecttable:add")
	//@Log(title = "", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(Subjecttable subjecttable)
	{
		return toAjax(subjecttableService.insertSelective(subjecttable));
	}

//	/**
//	 * 修改
//	 */
//	@GetMapping("/edit/{${primaryKey.attrVariableName}}")
//	public String edit(@PathVariable("${primaryKey.attrVariableName}") ${primaryKey.attrType} ${primaryKey.attrVariableName}, ModelMap model)
//	{
//        model.put("subjecttable", subjecttableService.selectByPrimaryKey(${primaryKey.attrVariableName}));
//	    return prefix + "/edit";
//	}

	/**
	 * 修改保存
	 */
	//@RequiresPermissions("subjecttable:edit")
	//@Log(title = "", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(Subjecttable subjecttable)
	{
		return toAjax(subjecttableService.updateByPrimaryKeySelective(subjecttable));
	}

	/**
	 * 删除
	 */
	//@RequiresPermissions("subjecttable:remove")
	//@Log(title = "", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(subjecttableService.removes(ids));
	}

}
