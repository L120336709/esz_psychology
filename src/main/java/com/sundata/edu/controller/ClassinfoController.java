package com.sundata.edu.controller;

import com.sundata.edu.domain.Classinfo;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.util.Constant;
import com.sundata.edu.vo.ClassinfoVo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.ClassinfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  信息操作处理
 *
 * @author whj
 * @date 2019-07-19 14:33:10
 */
@Controller
@RequestMapping("/classinfo")
public class ClassinfoController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "classinfo";

	@Autowired
	private ClassinfoService classinfoService;

	//@RequiresPermissions("classinfo:view")
	@GetMapping()
	public String classinfo()
	{

		return prefix + "/list";
	}

	/**
	 * 查询列表
	 */
	//@RequiresPermissions("classinfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ClassinfoVo classinfoVo)
	{
        startPage();
        return getDataTable(classinfoService.selectList(classinfoVo));
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
	//@RequiresPermissions("classinfo:add")
	//@Log(title = "", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(Classinfo classinfo)
	{

		return toAjax(classinfoService.insertSelective(classinfo));
	}

	/**
	 * 修改
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap model)
	{
        model.put("classinfo", classinfoService.selectByPrimaryKey(id));
	    return prefix + "/edit";
	}

	/**
	 * 修改保存
	 */
	//@RequiresPermissions("classinfo:edit")
	//@Log(title = "", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(Classinfo classinfo)
	{
		return toAjax(classinfoService.updateByPrimaryKeySelective(classinfo));
	}

	/**
	 * 删除
	 */
	//@RequiresPermissions("classinfo:remove")
	//@Log(title = "", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(classinfoService.removes(ids));
	}


	@PostMapping( "/selectClassInfoByGradeId")
	@ResponseBody
	public List<Map<String, Object>> selectClassInfoByGradeId(String gradeId, HttpServletRequest request)
	{
		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> params = new HashMap<String, Object>();
		//如果是老师，只能查到老师所在的班级信息
		if (Constant.IDENTITY_TEACHER == userinfo.getIdentity()){
			List<String> classIdList =  (List<String>) request.getSession(true).getAttribute("classIdList");
			if (CollectionUtils.isNotEmpty(classIdList)){
				params.put("classIdList", classIdList);
			}
		}
		params.put("gradeId",gradeId);
		return classinfoService.selectClassInfoByGradeId(params);
	}
}
