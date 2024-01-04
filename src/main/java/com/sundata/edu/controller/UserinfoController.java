package com.sundata.edu.controller;

import com.alibaba.excel.metadata.BaseRowModel;

import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.core.Resource;
import com.sundata.edu.framework.util.excel.ExcelUtil;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.UserinfoService;
import com.sundata.edu.util.Constant;
import com.sundata.edu.util.ShiroUtils;
import com.sundata.edu.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 用户 信息操作处理
 *
 * @author whj
 * @date 2019-04-19 14:15:43
 */
@Controller
@RequestMapping("/userinfo")
public class UserinfoController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String prefix = "userinfo";

    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private Resource resource;


    @GetMapping()
    public String userinfo(Model model) {
        model.addAttribute("orgId", ShiroUtils.getOrgId());
        return prefix + "/list";
    }



    /**
     * 查询用户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserinfoVo userinfoVo) {
        startPage();
        return getDataTable(userinfoService.selectList(userinfoVo));
    }
//    /**
//     * 查询用户列表
//     */
//    //@RequiresPermissions("userinfo:list")
//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(UserinfoVo userinfoVo) {
//        Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
//        String orgId = userinfo.getOrgId();
//        Integer identity = userinfo.getIdentity();
//
//        if (Constant.IDENTITY_STUDENT.equals(identity)) {
//            userinfoVo.setUserId(userinfo.getUserId());
//        }
//        userinfoVo.setIdentity(102);
//        startPage();
//        return getDataTable(userinfoService.selectList(userinfoVo));
//    }

    @PostMapping("/alllist")
    @ResponseBody
    public TableDataInfo alllist(UserinfoVo userinfoVo) {
        Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
        String orgId = userinfo.getOrgId();
        Integer identity = userinfo.getIdentity();

        if (Constant.IDENTITY_STUDENT.equals(identity)) {
            userinfoVo.setUserId(userinfo.getUserId());
        }
        startPage();
        return getDataTable(userinfoService.selectList(userinfoVo));
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    //@RequiresPermissions("userinfo:add")
    //@Log(title = "用户", action = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(Userinfo userinfo) {
        return toAjax(userinfoService.insertSelective(userinfo));
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") String userId, ModelMap model) {
        //model.put("userinfo", userinfoService.selectByPrimaryKey(userId));
        UserinfoVo userinfoVo = new UserinfoVo();
        userinfoVo.setUserId(userId);
        userinfoVo.setIdentity(102);
        StudentinfoVo studentinfoVo = userinfoService.selectOneStudent(userinfoVo);
        model.put("studentinfo", studentinfoVo);
        return prefix + "/edit";
    }



    @GetMapping("/detail/{userId}")
    public String detail(@PathVariable("userId") String userId, ModelMap model) {
        //model.put("userinfo", userinfoService.selectByPrimaryKey(userId));
        UserinfoVo userinfoVo = new UserinfoVo();
        userinfoVo.setUserId(userId);
        userinfoVo.setIdentity(102);
        StudentinfoVo studentinfoVo = userinfoService.selectOneStudent(userinfoVo);
        model.put("studentinfo", studentinfoVo);
        return prefix + "/detail";
    }

    /**
     * 修改保存用户
     */
    //@RequiresPermissions("userinfo:edit")
    //@Log(title = "用户", action = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(StudentinfoVo studentinfoVo) {

        return toAjax(userinfoService.saveStudentinfo(studentinfoVo));
    }

    /**
     * 删除用户
     */
    //@RequiresPermissions("userinfo:remove")
    //@Log(title = "用户", action = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(userinfoService.removes(ids));
    }

    /**
     * 获取学员用户信息
     */
    @PostMapping("/getUserByidentity")
    @ResponseBody
    public AjaxResult getAllUser(Integer identity, HttpServletRequest request){
        Userinfo userinfo =(Userinfo) SecurityUtils.getSubject().getPrincipal();
        UserinfoVo userinfoVo = new UserinfoVo();
        userinfoVo.setOrgId(userinfo.getOrgId());
        userinfoVo.setIdentity(identity);

        //如果是老师，只能查到老师所在的班级信息
        if (Constant.IDENTITY_TEACHER.equals(userinfo.getIdentity())){
            List<String> classIdList =  (List<String>) request.getSession(true).getAttribute("classIdList");
            if (CollectionUtils.isNotEmpty(classIdList)){
                userinfoVo.setClassIdList(classIdList);
            }
        }

        List<Map<String, String>> userList = userinfoService.getUserByidentity(userinfoVo, identity);

        return AjaxResult.success("获取成功").put("data",userList);
    }


    @PostMapping("/getStudentsByGradeClassId")
    @ResponseBody
    public List<Map<String, Object>> getStudentsByGradeClassId(String gradeId, String classId, HttpServletRequest request){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gradeId",gradeId);
        params.put("classId",classId);
        Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
        params.put("orgId", userinfo.getOrgId());
        //如果是老师，只能查到老师所在的班级信息
        if (Constant.IDENTITY_TEACHER.equals(userinfo.getIdentity())){
            List<String> classIdList =  (List<String>) request.getSession(true).getAttribute("classIdList");
            if (CollectionUtils.isNotEmpty(classIdList)){
                params.put("classIdList", classIdList);
            }
        }
        return userinfoService.getStudentsByGradeClassId(params);
    }


    @PostMapping("/import")
    @ResponseBody
    public AjaxResult importExcel(@RequestParam(value = "file", required = false) MultipartFile file,
                                  HttpServletRequest request) throws IOException, InterruptedException {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        OutputStream out = null;
        InputStream in = null;
        try {
            // String temp = filePath;
            in = file.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            String temp = resource.WEB_DOWNLOAD_PATH;
            out = new FileOutputStream(temp + fileName);
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

            FileItem fileItem = createFileItem(resource.WEB_DOWNLOAD_PATH + fileName);
            MultipartFile mfile = new CommonsMultipartFile(fileItem);
            BaseRowModel baseRowModel = new ExcelStudentVo();
            List<Object> objects = ExcelUtil.readExcel(mfile, baseRowModel, 1);
            //JSONObject jsonObject = PoiExcel.poiExcel(resource.WEB_DOWNLOAD_PATH + fileName);
            //System.out.println("jsonObject= " + jsonObject);
            logger.info("objects",objects);

            userinfoService.insertAndUpdate(objects);



            //achievementService.insertAchievenment(jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
            return AjaxResult.error(ex.getMessage());
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
        }

        return toAjax(1);
    }

    /***
     */
    @PostMapping("/export")
    public void exportData(HttpServletResponse response, HttpServletRequest request) {
        String fileName = "学生列表模版";
        String sheetName = "sheet1";
        List<ExcelStudentVo> excelStudentVos = new ArrayList<>();


        ExcelUtil.writeExcel(response,excelStudentVos,fileName,sheetName,new ExcelStudentVo());
    }

    @PostMapping("/exports")
    public void exports(HttpServletResponse response, HttpServletRequest request,UserinfoVo userinfoVo) {
        String fileName = "已完善过信息的学生列表"+ UUID.randomUUID().toString();;
        String sheetName = "sheet1";
        List<ExcelStudentVo> excelStudentVos = new ArrayList<>();

        Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
        String orgId = userinfo.getOrgId();
        Integer identity = userinfo.getIdentity();

        if (Constant.IDENTITY_STUDENT.equals(identity)) {
            userinfoVo.setUserId(userinfo.getUserId());
        }
        userinfoVo.setIdentity(102);
       // List<ExcelStudentVo> excelStudentVos1 = userinfoService.selectStudenList(userinfoVo);
      //  ExcelUtil.writeExcel(response,excelStudentVos1,fileName,sheetName,new ExcelStudentVo());
    }

    private static FileItem createFileItem(String filePath) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "textField";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        FileItem item = factory.createItem(textFieldName, "text/plain", true,
                "MyFileName" + extFile);
        File newfile = new File(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            FileInputStream fis = new FileInputStream(newfile);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, 8192))
                    != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }


    @RequestMapping("/getUserinfoKV")
    @ResponseBody
    public List<NameValueVo> getUserinfoKV(String keyword) {
        List<NameValueVo> list = new ArrayList<>();
        if(StringUtils.isEmpty(keyword)) {
            return list;
        }

        UserinfoVo userinfoVo = new UserinfoVo();
        userinfoVo.setRealName(keyword);
        userinfoVo.setStatus(1);

        //获取当前用户的机构id的所有下级机构id
        List<String> orgIds = getCurrOrgIdChild();
        userinfoVo.setOrgIds(orgIds);

        Userinfo userinfo = (Userinfo) SecurityUtils.getSubject().getPrincipal();
        if(userinfo.getIdentity()==102) {
            userinfoVo.setUserId(userinfo.getUserId());
        }

        list = userinfoService.searchUser(userinfoVo);
        return list;
    }

}
