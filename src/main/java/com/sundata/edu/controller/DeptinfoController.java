package com.sundata.edu.controller;

import com.sundata.edu.domain.Deptinfo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.vo.DeptinfoVo;
import com.sundata.edu.service.DeptinfoService;
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
 * @date 2021-10-19 23:23:37
 */
@Controller
@RequestMapping("/deptinfo")
public class DeptinfoController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "deptinfo";
	
	@Autowired
	private DeptinfoService deptinfoService;
	
	//@RequiresPermissions("deptinfo:view")
	@GetMapping()
	public String deptinfo()
	{
	    return prefix + "/list";
	}
	
	/**
	 * 查询列表
	 */
	//@RequiresPermissions("deptinfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DeptinfoVo deptinfoVo)
	{
        startPage();
        return getDataTable(deptinfoService.getDeptinfos(deptinfoVo));
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
	//@RequiresPermissions("deptinfo:add")
	//@Log(title = "", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(Deptinfo deptinfo)
	{		
		return toAjax(deptinfoService.insertSelective(deptinfo));
	}

	/**
	 * 修改
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap model)
	{
        model.put("deptinfo", deptinfoService.selectByPrimaryKey(id));
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存
	 */
	//@RequiresPermissions("deptinfo:edit")
	//@Log(title = "", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(Deptinfo deptinfo)
	{		
		return toAjax(deptinfoService.updateByPrimaryKeySelective(deptinfo));
	}
	
	/**
	 * 删除
	 */
	//@RequiresPermissions("deptinfo:remove")
	//@Log(title = "", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deptinfoService.removes(ids));
	}
	
}
