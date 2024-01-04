package com.sundata.edu.controller;

import com.sundata.edu.domain.SysRole;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.core.Resource;
import com.sundata.edu.service.SysMenuService;
import com.sundata.edu.service.SysRoleService;
import com.sundata.edu.service.UserClassInfoService;
import com.sundata.edu.util.Constant;
import com.sundata.edu.util.ShiroUtils;
import com.sundata.edu.vo.SysMenuVo;
import com.sundata.edu.vo.UserinfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private UserClassInfoService userClassInfoService;

    @Autowired
    private Resource resource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        List<SysMenuVo> sysMenuVos = sysMenuService.getMenusByUserId(ShiroUtils.getUserId());
        modelMap.put("menus", sysMenuVos);
        modelMap.put("user", ShiroUtils.getUserInfo());
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(ModelMap modelMap, HttpServletRequest request) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            list.add(i + "");
        }
        modelMap.put("list", list);

        //Userinfo userinfo =(Userinfo)SecurityUtils.getSubject().getPrincipal();
        UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();


        List<SysRole> rolesByUserId = sysRoleService.getRolesByUserId(userInfoVO.getUserId());
        List<Integer> roleids = new ArrayList<>();
        for (SysRole sysRole : rolesByUserId) {
            roleids.add(sysRole.getRoleId());
        }
        // 如果不是管理员,才需要判断是不是老师
        //首页进入报错
//        if (!roleids.contains(1) && !roleids.contains(2)){
//            // 101代表老师
//            /*
//            //通过查rest接口获取班主任对应的班级信息
//            if (Constant.IDENTITY_TEACHER == userinfo.getIdentity()){
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("accessToken","1");
//                jsonObject.put("appKey","1");
//                jsonObject.put("sign","1");
//                jsonObject.put("teacherId",userinfo.getUserId());
//                jsonObject.put("timestamp",System.currentTimeMillis());
//                try {
//                    String result = RestUtil.postMethod(resource.TEACHER_CLASSINFO_URL, jsonObject.toString());
//                    JSONObject resultJson = JSONObject.parseObject(result);
//                    if (null != resultJson && 200 == resultJson.getInteger("code")){
//                        List<String> classIdList = (List<String>)resultJson.get("data");
//                        request.getSession(true).setAttribute("classIdList",classIdList);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }*/
//            //通过查询本地库中设置的老师和班级的对应关系库获取老师和班级的对应信息
//            if (Constant.IDENTITY_TEACHER.equals(userinfo.getIdentity())){
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("userId",userinfo.getUserId());
//                List<String> classIdList = userClassInfoService.queryClassIdsByUserId(params);
//                //如果老师的班级信息为空，则传入一个不存在的班级id，查询数据时获取不到班级数据
//                if (CollectionUtils.isEmpty(classIdList)){
//                    classIdList = new ArrayList<>();
////                    classIdList.add("1");
//                }
//                request.getSession(true).setAttribute("classIdList",classIdList);
//            }
//
//        }

        return "main";
    }

}

