package com.sundata.edu.controller;

import com.sundata.edu.domain.Gradeinfo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.GradeinfoService;
import com.sundata.edu.vo.GradeinfoVo;
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
 * @date 2019-07-19 14:33:47
 */
@Controller
@RequestMapping("/gradeinfo")
public class GradeinfoController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "gradeinfo";

	@Autowired
	private GradeinfoService gradeinfoService;

	//@RequiresPermissions("gradeinfo:view")
	@GetMapping()
	public String gradeinfo()
	{
	    return prefix + "/list";
	}

	/**
	 * 查询列表
	 */
	//@RequiresPermissions("gradeinfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(GradeinfoVo gradeinfoVo)
	{
        startPage();
        return getDataTable(gradeinfoService.selectList(gradeinfoVo));
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
	//@RequiresPermissions("gradeinfo:add")
	//@Log(title = "", action = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(Gradeinfo gradeinfo)
	{
		return toAjax(gradeinfoService.insertSelective(gradeinfo));
	}

	/**
	 * 修改
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap model)
	{
        model.put("gradeinfo", gradeinfoService.selectByPrimaryKey(id));
	    return prefix + "/edit";
	}

	/**
	 * 修改保存
	 */
	//@RequiresPermissions("gradeinfo:edit")
	//@Log(title = "", action = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(Gradeinfo gradeinfo)
	{
		return toAjax(gradeinfoService.updateByPrimaryKeySelective(gradeinfo));
	}

	/**
	 * 删除
	 */
	//@RequiresPermissions("gradeinfo:remove")
	//@Log(title = "", action = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(gradeinfoService.removes(ids));
	}


	@PostMapping( "/selectGradeInfos")
	@ResponseBody
	public List<Map<String, Object>> selectGradeInfos(HttpServletRequest request){

//		Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> params = new HashMap<>();
//		//如果是老师，只能查到老师所在的班级信息
//		if (Constant.IDENTITY_TEACHER == userinfo.getIdentity()){
//			List<String> classIdList =  (List<String>) request.getSession(true).getAttribute("classIdList");
//			if (CollectionUtils.isNotEmpty(classIdList)){
//				params.put("classIdList", classIdList);
//			}
//		}
		return gradeinfoService.selectGradeInfos(params);
	}

}
