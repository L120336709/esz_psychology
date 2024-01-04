package com.sundata.edu.controller;

import com.sundata.edu.domain.Phaseofstudy;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.vo.PhaseofstudyVo;
import com.sundata.edu.service.PhaseofstudyService;
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
 * @date 2021-11-01 08:43:50
 */
@Controller
@RequestMapping("/phaseofstudy")
public class PhaseofstudyController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "phaseofstudy";

	@Autowired
	private PhaseofstudyService phaseofstudyService;

	//@RequiresPermissions("phaseofstudy:view")
	@GetMapping()
	public String phaseofstudy()
	{
	    return prefix + "/list";
	}

	/**
	 * 查询列表
	 */
	//@RequiresPermissions("phaseofstudy:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PhaseofstudyVo phaseofstudyVo)
	{
        startPage();
        return getDataTable(phaseofstudyService.getPhaseofstudys(phaseofstudyVo));
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
	//@RequiresPermissions("phaseofstudy:add")
	//@Log(title = "", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(Phaseofstudy phaseofstudy)
	{
		return toAjax(phaseofstudyService.insertSelective(phaseofstudy));
	}

	/**
	 * 修改
	 */
	@GetMapping("/edit/{phasestudyid}")
	public String edit(@PathVariable("phasestudyid") Long phasestudyid, ModelMap model)
	{
        model.put("phaseofstudy", phaseofstudyService.selectByPrimaryKey(phasestudyid));
	    return prefix + "/edit";
	}

	/**
	 * 修改保存
	 */
	//@RequiresPermissions("phaseofstudy:edit")
	//@Log(title = "", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(Phaseofstudy phaseofstudy)
	{
		return toAjax(phaseofstudyService.updateByPrimaryKeySelective(phaseofstudy));
	}

	/**
	 * 删除
	 */
	//@RequiresPermissions("phaseofstudy:remove")
	//@Log(title = "", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(phaseofstudyService.removes(ids));
	}

}
