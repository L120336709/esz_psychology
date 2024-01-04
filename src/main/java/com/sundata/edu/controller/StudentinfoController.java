package com.sundata.edu.controller;

import com.sundata.edu.dao.StudentinfoDao;
import com.sundata.edu.domain.Studentinfo;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.UserinfoService;
import com.sundata.edu.vo.StudentinfoVo;
import com.sundata.edu.service.StudentinfoService;
import com.sundata.edu.vo.UserinfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *  信息操作处理
 *
 * @author whj
 * @date 2021-11-02 15:29:17
 */
@Controller
@RequestMapping("/studentinfo")
public class StudentinfoController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "studentinfo";

	@Autowired
	private StudentinfoService studentinfoService;


	@Autowired
	private UserinfoService userinfoService;
	@GetMapping()
	public String studentinfo()
	{
	    return prefix + "/list";
	}

	/**
	 * 查询列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(StudentinfoVo studentinfoVo)
	{
        startPage();
        return getDataTable(studentinfoService.getStudentinfos(studentinfoVo));
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
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(Studentinfo studentinfo)
	{
		return toAjax(studentinfoService.insertSelective(studentinfo));
	}

	/**
	 * 修改
	 */
	@GetMapping("/edit/{userId}")
	public String edit(@PathVariable("userId") String userId, ModelMap model)
	{
        model.put("studentinfo", studentinfoService.selectByPrimaryKey(userId));
	    return prefix + "/edit";
	}

	/**
	 * 修改保存
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(Studentinfo studentinfo)
	{
		return toAjax(studentinfoService.updateByPrimaryKeySelective(studentinfo));
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(studentinfoService.removes(ids));
	}

}
