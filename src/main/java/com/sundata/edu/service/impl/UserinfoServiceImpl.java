package com.sundata.edu.service.impl;


import com.sundata.edu.api.bean.StudentRequest;
import com.sundata.edu.api.bean.UserRequest;
import com.sundata.edu.dao.ClassinfoDao;
import com.sundata.edu.dao.OrginfoDao;
import com.sundata.edu.dao.StudentinfoDao;
import com.sundata.edu.dao.UserinfoDao;
import com.sundata.edu.domain.*;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.service.DictService;
import com.sundata.edu.service.StudentinfoService;
import com.sundata.edu.service.SysRoleService;
import com.sundata.edu.service.SysUserRoleService;
import com.sundata.edu.service.UserinfoService;
import com.sundata.edu.util.Constant;
import com.sundata.edu.util.DateUtils;
import com.sundata.edu.util.IdcardValidator;
import com.sundata.edu.util.Utils;
import com.sundata.edu.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 用户 服务层实现
 *
 * @author whj
 * @date 2019-04-17 10:25:26
 */
@Service("userinfoService")
//@Transactional
public class UserinfoServiceImpl extends AbstractService<Userinfo> implements UserinfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserinfoDao userinfoDao;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private ClassinfoDao classinfoDao;

    @Autowired
    private StudentinfoDao studentinfoDao;

    @Autowired
    private StudentinfoService studentinfoService;

    @Autowired
    private DictService dictService;

    @Autowired
    private OrginfoDao orginfoDao;

    private IdcardValidator idcardValidator = new IdcardValidator();



    @Override
    public List<UserinfoVo> selectList(UserinfoVo userinfoVo) {
        return userinfoDao.selectList(userinfoVo);
    }



    @Override
    public int removes(String ids) {
        Example example = new Example(Userinfo.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("userId", idArray);
        Userinfo userinfo = new Userinfo();
        //userinfo.setIsdelete(1);
        return userinfoDao.updateByExampleSelective(userinfo, example);
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public Userinfo getUserInfoByUserId(String userId) {
        Example example = new Example(Userinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<Userinfo> userinfos = userinfoDao.selectByExample(example);
        if (userinfos != null && userinfos.size() > 0) {
            return userinfos.get(0);
        }
        return null;
    }

    /**
     * 同步用户信息
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int synUserInfo(UserRequest request) {
        Userinfo userinfo = getUserInfoByUserId(request.getUserId());
        if (userinfo == null) {
            //添加用户
            userinfo = new Userinfo();
            BeanUtils.copyProperties(request, userinfo);
            userinfo.setStatus(request.getStatus());
            userinfoDao.insertSelective(userinfo);

            if (userinfo.getIdentity() == 102) {
                Studentinfo studentinfo = new Studentinfo();
                studentinfo.setUserId(userinfo.getUserId());
                studentinfo.setStudentName(userinfo.getRealName());
                studentinfo.setSchoolcode(userinfo.getOrgId());
                studentinfo.setGradeCode(userinfo.getGradeId());
                studentinfo.setClassCode(userinfo.getClassId());
                studentinfo.setGender(userinfo.getGender());
                studentinfo.setIdcard(userinfo.getIdCard());
                studentinfo.setNation(userinfo.getNational());
                studentinfo.setNativeplace(userinfo.getNativePlace());
                studentinfoDao.insertSelective(studentinfo);
            }


            //添加角色
            List<SysRole> roles = sysRoleService.getRolesByUserId(userinfo.getUserId());
            if (roles == null || roles.size() == 0) {
                List<Integer> sysRoleIds = getRoleIdByIdentity(userinfo);
                List<SysUserRole> sysUserRoleList = new ArrayList<>();
                for (Integer roleId : sysRoleIds) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userinfo.getUserId());
                    sysUserRole.setRoleId(roleId);
                    sysUserRole.setCreateTime(new Date());
                    sysUserRole.setUpdateTime(new Date());
                    sysUserRoleList.add(sysUserRole);
                }
                if (sysUserRoleList.size() > 0) {
                    sysUserRoleService.insertList(sysUserRoleList);
                }
            }
        } else {
            //更新用户
            userinfo.setRealName(request.getRealName());
            userinfo.setIdentity(request.getIdentity());
            userinfo.setOrgId(request.getOrgId());
            userinfo.setStatus(request.getStatus());
            userinfo.setStatus(request.getStatus());
            userinfo.setIdCard(request.getIdCard());
            userinfo.setGradeId(request.getGradeId());
            userinfo.setClassId(request.getClassId());
            userinfoDao.updateByPrimaryKeySelective(userinfo);

            //添加角色
            List<SysRole> roles = sysRoleService.getRolesByUserId(userinfo.getUserId());
            if (roles == null || roles.size() == 0) {
                List<Integer> sysRoleIds = getRoleIdByIdentity(userinfo);
                List<SysUserRole> sysUserRoleList = new ArrayList<>();
                for (Integer roleId : sysRoleIds) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userinfo.getUserId());
                    sysUserRole.setRoleId(roleId);
                    sysUserRole.setCreateTime(new Date());
                    sysUserRole.setUpdateTime(new Date());
                    sysUserRoleList.add(sysUserRole);
                }
                if (sysUserRoleList.size() > 0) {
                    sysUserRoleService.insertList(sysUserRoleList);
                }
            }
        }
        return 1;
    }


    @Override
    public List<UserinfoVo> getRoleUsers(Integer roleId, UserinfoVo userinfoVo) {
        return userinfoDao.getRoleUsers(roleId, userinfoVo);
    }

    @Override
    public List<UserinfoVo> getRoleUnUsers(Integer roleId, UserinfoVo userinfoVo) {
        return userinfoDao.getRoleUnUsers(roleId, userinfoVo);
    }

    private List<Integer> getRoleIdByIdentity(Userinfo userinfo) {
        //用户身份:101 教师 102 学生 103家长  104 学校管理员  105 教育局管理员 106 教育局普通用户 107 学校普通用户
        List<Integer> roleIds = new ArrayList<>();

        //获取系统中所有角色
        List<SysRole> sysRoles = sysRoleService.selectByExample(new Example(SysRole.class));
        Map<String, Integer> roleCodeIdKV = sysRoles.stream().collect(Collectors.toMap(SysRole::getRoleCode, SysRole::getRoleId));

        Orginfo orginfo = new Orginfo();
        orginfo.setOrgId(userinfo.getOrgId());
        Orginfo orginfoData = orginfoDao.selectOne(orginfo);
        //1教育局 2中心校 3学校
        //根据平台身份分配角色
        //级别 1省级教育厅 2市级教育局 3区县教育局 4乡镇中心校 5 学校
        if (userinfo.getIdentity() == 105) {
            if(orginfoData.getElevel() == 2){
                //州级教育局管理员
                if (roleCodeIdKV.containsKey("admin")) {
                    roleIds.add(roleCodeIdKV.get("admin"));
                }
                if (roleCodeIdKV.containsKey("city_admin")) {
                    roleIds.add(roleCodeIdKV.get("city_admin"));
                }
            }
            if(orginfoData.getElevel() == 3){
                //区县级教育局管理员
                if (roleCodeIdKV.containsKey("area_admin")) {
                    roleIds.add(roleCodeIdKV.get("area_admin"));
                }
            }
        }
        if (userinfo.getIdentity() == 104) {
            //学校管理员
            if (roleCodeIdKV.containsKey("school_admin")) {
                roleIds.add(roleCodeIdKV.get("school_admin"));
            }
        }
        if(userinfo.getIdentity() == 101 || userinfo.getIdentity() == 106 || userinfo.getIdentity() == 107) {
            //教职工
            if (roleCodeIdKV.containsKey("staff")) {
                roleIds.add(roleCodeIdKV.get("staff"));
            }
        }
        if (userinfo.getIdentity() == 102) {
            //学校管理员
            if (roleCodeIdKV.containsKey("student")) {
                roleIds.add(roleCodeIdKV.get("student"));
            }
        }
        return roleIds;
    }

    @Override
    public Userinfo getUserinfoByUserID(String userid){
        return userinfoDao.getUserinfoByUserID(userid);
    }


    @Override
    public List<Map<String, String>> getUserByidentity(UserinfoVo userinfoVo, Integer identity) {
        List<UserinfoVo> userByidentity = userinfoDao.getUserByidentity(userinfoVo);
        List<Map<String, String>> list = new ArrayList<>();
        for (UserinfoVo userinfoVo1 : userByidentity) {
            Map<String, String> map = new HashMap<>();
            if (identity.equals(Constant.IDENTITY_TEACHER)) {
                map.put("value", userinfoVo1.getRealName());
                map.put("label", userinfoVo1.getRealName());

            } else if (identity.equals(Constant.IDENTITY_STUDENT)) {
                //map.put("value", userinfoVo1.getRealName() + "-" + userinfoVo1.getGradeName() + "" + userinfoVo1.getClassName());
                map.put("value", userinfoVo1.getRealName());

                map.put("label", userinfoVo1.getRealName());
            }
            map.put("userId", userinfoVo1.getUserId());
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String,String> getUserByidentityActivity(Integer identity) {
        UserinfoVo userinfoVo = new UserinfoVo();
        userinfoVo.setIdentity(identity);
        //Userinfo userinfo =(Userinfo)SecurityUtils.getSubject().getPrincipal();
        UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
        userinfoVo.setOrgId(userInfoVO.getOrgId());
        List<UserinfoVo> userByidentity = userinfoDao.getUserByidentity(userinfoVo);
        Map<String,String> map = new HashMap<>();
        for(UserinfoVo userinfoVo1:userByidentity){
            if(identity.equals(Constant.IDENTITY_TEACHER)){
                map.put(userinfoVo1.getUserId(), userinfoVo1.getRealName());
            }else if(identity.equals(Constant.IDENTITY_STUDENT)) {
                map.put(userinfoVo1.getUserId(), userinfoVo1.getRealName()+"-"+userinfoVo1.getGradeName()+""+userinfoVo1.getClassName());
            }
        }
        return map;
    }

    @Override
    public List<OverallBean> selectOverallList(OverallVo overallVo) {
        return userinfoDao.selectOverallList(overallVo);
    }

    @Override
    public OverallBean selectOneOverallInfo(OverallVo overallVo) {
        return userinfoDao.selectOneOverallInfo(overallVo);
    }

    @Override
    public List<Map<String, Object>> getStudentsByGradeClassId(Map<String, Object> params) {
        return userinfoDao.getStudentsByGradeClassId(params);
    }

    @Override
    public Map<String, String> getTeacherMapExcept(Integer identity) {
        UserinfoVo userinfoVo = new UserinfoVo();
        userinfoVo.setIdentity(identity);
        //Userinfo userinfo =(Userinfo)SecurityUtils.getSubject().getPrincipal();
        UserinfoVo userInfoVO = (UserinfoVo) SecurityUtils.getSubject().getPrincipal();
        userinfoVo.setOrgId(userInfoVO.getOrgId());
        List<UserinfoVo> userByidentity = userinfoDao.getTeacherMapExcept(userinfoVo);
        Map<String,String> map = new HashMap<>();
        for(UserinfoVo userinfoVo1:userByidentity){
            map.put(userinfoVo1.getUserId(), userinfoVo1.getRealName());
        }
        return map;
    }


    @Override
    public StudentinfoVo selectOneStudent(UserinfoVo userinfoVo) {
        StudentinfoVo studentinfoVo = userinfoDao.selectOneStudent(userinfoVo);
        return studentinfoVo;
    }

    //保存学生数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveStudentinfo(StudentinfoVo studentinfoVo) {
        //通过userid查询数据存在不存在
        Studentinfo studentinfo = new Studentinfo();
        studentinfo.setUserId(studentinfoVo.getUserId());
        Studentinfo studentinfo1 = studentinfoDao.selectOne(studentinfo);
        Studentinfo studentinfoData = new Studentinfo();
        BeanUtils.copyProperties(studentinfoVo,studentinfoData);
        if(studentinfo1==null){
            //新增
            logger.info("studentinfoData->{}",studentinfoData);
            studentinfoDao.insert(studentinfoData);
        }else{
            //修改
            Example example = new Example(Studentinfo.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId",studentinfoVo.getUserId());
            studentinfoDao.updateByExample(studentinfoData,example);
        }


        return 1;
    }

    public static void main(String[] args) {
        String s = "1-";
        String[] split = s.split("-");
        System.out.println(split.length);

    }

    @Override
    public int insertAndUpdate(List<Object> objects) throws Exception {

        String msgStr = "";
        for (Object oneObj:objects){
            List<String> one = (List<String>) oneObj;
            for (int i=one.size();i<73;i++){
                one.add("");
            }
            //查询学号是否存在
            Studentinfo studentinfo = new Studentinfo();
            Userinfo userinfo = new Userinfo();
            String idCard = replaceBlank(one.get(9));
            String realName = replaceBlank(one.get(1));
            userinfo.setIdCard(idCard);
            userinfo.setRealName(realName);
            userinfo.setStatus(1);
            //通过身份证和姓名匹配
            List<Userinfo> select = userinfoDao.select(userinfo);
            if(select.size()>1){
                msgStr+=realName+idCard;
                continue;
            }
            Userinfo userinfo1 = userinfoDao.selectOne(userinfo);
            if(userinfo1==null){
                msgStr+=realName+idCard+"姓名和身份证不匹配</br>";
            }else{
                studentinfo.setUserId(userinfo1.getUserId());
                Studentinfo studentinfo1 = studentinfoDao.selectOne(studentinfo);
                Studentinfo studentinfoData = new Studentinfo();
                if(studentinfo1==null){
                    //增加
                    checkData(one,studentinfoData);
                    studentinfoData.setUserId(userinfo1.getUserId());
                    studentinfoDao.insert(studentinfoData);
                }else{
                    //studentinfoData.setUserId(userinfo1.getUserId());
                    //修改
                    checkData(one,studentinfoData);
                    Example example = new Example(Studentinfo.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("userId",userinfo1.getUserId());
                    studentinfoDao.updateByExample(studentinfoData,example);
                }

            }


        }
        if(msgStr.length()>1){
            throw new Exception(msgStr+"导入失败噢");
        }

        return 1;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            //空格\t、回车\n、换行符\r、制表符\t
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    private String formatExcelDate(int day) {
        Calendar calendar = new GregorianCalendar(1900,0,-1);
        Date gregorianDate = calendar.getTime();
        String formatExcelDate = DateUtils.format(DateUtils.addDay(gregorianDate, day), DateUtils.YYYYMMDD);
        return formatExcelDate;
    }



    public void checkData(List oneObj,Studentinfo studentinfoData) {
        if(oneObj.get(0)!=null) {
            studentinfoData.setSchoolcode(replaceBlank(oneObj.get(0) + ""));
        }
        if(oneObj.get(2)!=null) {
            studentinfoData.setGender(replaceBlank(oneObj.get(2) + ""));
        }
        if(oneObj.get(3)!=null) {
            //int birthday = Integer.parseInt(replaceBlank(oneObj.get(3) + "").toString());
            //studentinfoData.setBirthday(formatExcelDate(birthday));
            studentinfoData.setBirthday(replaceBlank(oneObj.get(3) + ""));
        }
        if(oneObj.get(4)!=null) {
            studentinfoData.setAtivecode(replaceBlank(oneObj.get(4) + ""));
        }
        if(oneObj.get(5)!=null) {
            studentinfoData.setNativeplace(replaceBlank(oneObj.get(5) + ""));
        }
        if(oneObj.get(6)!=null) {
            studentinfoData.setNation(replaceBlank(oneObj.get(6) + ""));
        }
        if(oneObj.get(7)!=null) {
            studentinfoData.setNationality(replaceBlank(oneObj.get(7) + ""));
        }
        if(oneObj.get(8)!=null) {
            studentinfoData.setIdcardtype(replaceBlank(oneObj.get(8) + ""));
        }
        if(oneObj.get(9)!=null) {
            studentinfoData.setIdcard(replaceBlank(oneObj.get(9) + ""));
        }
        if(oneObj.get(10)!=null) {
            studentinfoData.setGat(replaceBlank(oneObj.get(10) + ""));
        }
        if(oneObj.get(11)!=null) {
            studentinfoData.setHealthy(replaceBlank(oneObj.get(11) + ""));
        }
        if(oneObj.get(12)!=null) {
            studentinfoData.setPolitytype(replaceBlank(oneObj.get(12) + ""));
        }
        if(oneObj.get(13)!=null) {
            studentinfoData.setRegisteredresidence(replaceBlank(oneObj.get(13) + ""));
        }
        if(oneObj.get(14)!=null) {
            studentinfoData.setRegisteredresidencexzq(replaceBlank(oneObj.get(14) + ""));
        }
        if(oneObj.get(15)!=null) {
            studentinfoData.setXqh(replaceBlank(oneObj.get(15) + ""));
        }
        if(oneObj.get(16)!=null) {
            studentinfoData.setBh(replaceBlank(oneObj.get(16) + ""));
        }
        if(oneObj.get(17)!=null) {
            //int lxnf = Integer.parseInt(replaceBlank(oneObj.get(17) + "").toString());
            //studentinfoData.setBirthday(formatExcelDate(lxnf));
            studentinfoData.setLxnf(replaceBlank(oneObj.get(17) + ""));
        }
        if(oneObj.get(18)!=null) {
            studentinfoData.setRxfs(replaceBlank(oneObj.get(18) + ""));
        }
        if(oneObj.get(19)!=null) {
            studentinfoData.setJdfs(replaceBlank(oneObj.get(19) + ""));
        }
        if(oneObj.get(20)!=null) {
            studentinfoData.setAddress(replaceBlank(oneObj.get(20) + ""));
        }
        if(oneObj.get(21)!=null) {
            studentinfoData.setPostaladdress(replaceBlank(oneObj.get(21) + ""));
        }
        if(oneObj.get(22)!=null) {
            studentinfoData.setHomeaddress(replaceBlank(oneObj.get(22) + ""));
        }
        if(oneObj.get(23)!=null) {
            studentinfoData.setMobile(replaceBlank(oneObj.get(23) + ""));
        }
        if(oneObj.get(24)!=null) {
            studentinfoData.setPostalcode(replaceBlank(oneObj.get(24) + ""));
        }
        if(oneObj.get(25)!=null) {
            studentinfoData.setOnlychild(replaceBlank(oneObj.get(25) + ""));
        }
        if(oneObj.get(26)!=null) {
            studentinfoData.setXqjy(replaceBlank(oneObj.get(26) + ""));
        }
        if(oneObj.get(27)!=null) {
            studentinfoData.setLset(replaceBlank(oneObj.get(27) + ""));
        }
        if(oneObj.get(28)!=null) {
            studentinfoData.setLscet(replaceBlank(oneObj.get(28) + ""));
        }
        if(oneObj.get(29)!=null) {
            studentinfoData.setCjr(replaceBlank(oneObj.get(29) + ""));
        }
        if(oneObj.get(30)!=null) {
            studentinfoData.setSqzz(replaceBlank(oneObj.get(30) + ""));
        }
        if(oneObj.get(31)!=null) {
            studentinfoData.setYb(replaceBlank(oneObj.get(31) + ""));
        }
        if(oneObj.get(32)!=null) {
            studentinfoData.setGr(replaceBlank(oneObj.get(32) + ""));
        }
        if(oneObj.get(33)!=null) {
            studentinfoData.setYfzn(replaceBlank(oneObj.get(33) + ""));
        }
        if(oneObj.get(34)!=null) {
            studentinfoData.setSqzn(replaceBlank(oneObj.get(34) + ""));
        }
        if(oneObj.get(35)!=null) {
            studentinfoData.setCwsqzn(replaceBlank(oneObj.get(35) + ""));
        }
        if(oneObj.get(36)!=null) {
            studentinfoData.setSxx(replaceBlank(oneObj.get(36) + ""));
        }
        if(oneObj.get(37)!=null) {
            studentinfoData.setSxxfs(replaceBlank(oneObj.get(37) + ""));
        }
        if(oneObj.get(38)!=null) {
            studentinfoData.setSfcwxc(replaceBlank(oneObj.get(38) + ""));
        }
        if(oneObj.get(39)!=null) {
            studentinfoData.setCym(replaceBlank(oneObj.get(39) + ""));
        }
        if(oneObj.get(40)!=null) {
            studentinfoData.setSfzyxq(replaceBlank(oneObj.get(40) + ""));
        }
        if(oneObj.get(41)!=null) {
            studentinfoData.setXx(replaceBlank(oneObj.get(41) + ""));
        }
        if(oneObj.get(42)!=null) {
            studentinfoData.setTc(replaceBlank(oneObj.get(42) + ""));
        }
        if(oneObj.get(43)!=null) {
            studentinfoData.setXjfh(replaceBlank(oneObj.get(43) + ""));
        }
        if(oneObj.get(44)!=null) {
            studentinfoData.setBlxh(replaceBlank(oneObj.get(44) + ""));
        }
        if(oneObj.get(45)!=null) {
            studentinfoData.setXsly(replaceBlank(oneObj.get(45) + ""));
        }
        if(oneObj.get(46)!=null) {
            studentinfoData.setDzxx(replaceBlank(oneObj.get(46) + ""));
        }
        if(oneObj.get(47)!=null) {
            studentinfoData.setZydz(replaceBlank(oneObj.get(47) + ""));
        }
        if(oneObj.get(48)!=null) {
            studentinfoData.setZfgmxw(replaceBlank(oneObj.get(48) + ""));
        }
        if(oneObj.get(49)!=null) {
            studentinfoData.setCyxm(replaceBlank(oneObj.get(49) + ""));
        }
        if(oneObj.get(50)!=null) {
            studentinfoData.setCygx(replaceBlank(oneObj.get(50) + ""));
        }
        if(oneObj.get(51)!=null) {
            studentinfoData.setCygxsm(replaceBlank(oneObj.get(51) + ""));
        }
        if(oneObj.get(52)!=null) {
            studentinfoData.setCyzz(replaceBlank(oneObj.get(52) + ""));
        }
        if(oneObj.get(53)!=null) {
            studentinfoData.setCyxzqh(replaceBlank(oneObj.get(53) + ""));
        }
        if(oneObj.get(54)!=null) {
            studentinfoData.setCydh(replaceBlank(oneObj.get(54) + ""));
        }
        if(oneObj.get(55)!=null) {
            studentinfoData.setCyjhr(replaceBlank(oneObj.get(55) + ""));
        }
        if(oneObj.get(56)!=null) {
            studentinfoData.setCysfzlx(replaceBlank(oneObj.get(56) + ""));
        }
        if(oneObj.get(57)!=null) {
            studentinfoData.setCysfzh(replaceBlank(oneObj.get(57) + ""));
        }
        if(oneObj.get(58)!=null) {
            studentinfoData.setCymz(replaceBlank(oneObj.get(58) + ""));
        }
        if(oneObj.get(59)!=null) {
            studentinfoData.setCygz(replaceBlank(oneObj.get(59) + ""));
        }
        if(oneObj.get(60)!=null) {
            studentinfoData.setCyzw(replaceBlank(oneObj.get(60) + ""));
        }
        if(oneObj.get(61)!=null) {
            studentinfoData.setCyrxm(replaceBlank(oneObj.get(61) + ""));
        }
        if(oneObj.get(62)!=null) {
            studentinfoData.setCyrgx(replaceBlank(oneObj.get(62) + ""));
        }
        if(oneObj.get(63)!=null) {
            studentinfoData.setCyrgxsm(replaceBlank(oneObj.get(63) + ""));
        }
        if(oneObj.get(64)!=null) {
            studentinfoData.setCyrzz(replaceBlank(oneObj.get(64) + ""));
        }
        if(oneObj.get(65)!=null) {
            studentinfoData.setCyrxzq(replaceBlank(oneObj.get(65) + ""));
        }
        if(oneObj.get(66)!=null) {
            studentinfoData.setCyrdh(replaceBlank(oneObj.get(66) + ""));
        }
        if(oneObj.get(67)!=null) {
            studentinfoData.setCyrjhr(replaceBlank(oneObj.get(67) + ""));
        }
        if(oneObj.get(68)!=null) {
            studentinfoData.setCyrsfzlx(replaceBlank(oneObj.get(68) + ""));
        }
        if(oneObj.get(69)!=null) {
            studentinfoData.setCyrzjh(replaceBlank(oneObj.get(69) + ""));
        }
        if(oneObj.get(70)!=null) {
            studentinfoData.setCyrmz(replaceBlank(oneObj.get(70) + ""));
        }
        if(oneObj.get(71)!=null) {
            studentinfoData.setCyrgzdw(replaceBlank(oneObj.get(71) + ""));
        }
        if(oneObj.get(72)!=null) {
            studentinfoData.setCyrzw(replaceBlank(oneObj.get(72) + ""));
        }
    }


    @Override
    public List<NameValueVo> searchUser(UserinfoVo userinfoVo) {
        List<UserinfoVo> userList = userinfoDao.searchUser(userinfoVo);
        List<NameValueVo> kvList = new ArrayList<>();
        for (UserinfoVo u : userList) {
            NameValueVo nvv = new NameValueVo();
            nvv.setValue(u.getUserId());
//            String orgName = StringUtils.isEmpty(u.getOrgName()) ? u.getOrgId() : u.getOrgName();
            String gradeName = StringUtils.isEmpty(u.getGradeName()) ? u.getGradeId() : u.getGradeName();
            String className = StringUtils.isEmpty(u.getClassName()) ? u.getClassId() : u.getClassName();
            String name = u.getRealName() + "-" + gradeName + className;
            nvv.setName(name);
            kvList.add(nvv);
        }
        return kvList;
    }

    @Override
    public AjaxResult scanStudentInfo(StudentRequest studentRequest) {
        String studentId = studentRequest.getStudentId();
        if (StringUtils.isEmpty(studentId)) {
            return AjaxResult.error("学生Id为空！");
        }
        String idCard = studentRequest.getIdCard();
        if (StringUtils.isEmpty(idCard)) {
            return AjaxResult.error("身份证号为空！");
        }
        if (!idcardValidator.isValidatedAllIdcard(idCard)) {
            return AjaxResult.error("身份证号不合法！");
        }
        String studentName = studentRequest.getStudentName();
        if (StringUtils.isEmpty(studentName)) {
            return AjaxResult.error("姓名为空！");
        }
        String gender = studentRequest.getGender();
        String genderName = null;
        if (StringUtils.isNotEmpty(gender)) {
            if (!"1".equals(gender) && !"2".equals(gender)) {
                return AjaxResult.error("性别格式错误！");
            }
            if ("1".equals(gender)) {
                genderName = "男";
            } else {
                genderName = "女";
            }
        }

        String mId = null;
        String nation = studentRequest.getNation();
        if (StringUtils.isNotEmpty(nation)) {
            List<Mz> mz = dictService.getMz();
            for (Mz m : mz) {
                if (m.getMzname().equals(nation)) {
                    mId = m.getMzId();
                    break;
                }
            }
            if (mId == null) {
                return AjaxResult.error("名族错误！");
            }
        }
        String birthday = studentRequest.getBirthday();
        if (StringUtils.isNotEmpty(birthday) && Utils.StringToData(birthday) == null) {
            return AjaxResult.error("出生年月日期格式必须为yyyy-MM-dd");
        }

        Userinfo userinfo = userinfoDao.selectByPrimaryKey(studentId);
        if (userinfo == null) {
            return AjaxResult.error("未查询到该学生的基本信息！");
        }


        if (!idCard.equals(userinfo.getIdCard()) || !studentName.equals(userinfo.getRealName()) || mId != null || gender != null) {
            Userinfo entity = new Userinfo();
            entity.setUserId(studentId);
            entity.setIdCard(idCard);
            entity.setRealName(studentName);
            entity.setNational(mId);
            entity.setGender(gender);
            userinfoDao.updateByPrimaryKeySelective(entity);
        }

        Studentinfo entity = new Studentinfo(studentId, genderName, birthday, studentRequest.getBirthplaceCode(), studentRequest.getNativePlace(), nation, studentRequest.getNationality(),
                studentRequest.getIdCardType(), idCard, studentRequest.getHealthy(), studentRequest.getPolityType(), studentRequest.getRegisteredResidence(), studentRequest.getRegisteredResidenceXzq(),
                studentRequest.getAddress(), studentRequest.getPostalAddress(), studentRequest.getHomeAddress(), studentRequest.getMobile(), studentRequest.getPostalCode(), studentRequest.getIdCardValidity(), studentRequest.getBloodType());
        Studentinfo studentinfo = studentinfoDao.selectByPrimaryKey(studentId);
        if (studentinfo == null) {
            studentinfoDao.insert(entity);
        } else {
            studentinfoDao.updateByPrimaryKeySelective(entity);
        }
        return AjaxResult.success();
    }

}
