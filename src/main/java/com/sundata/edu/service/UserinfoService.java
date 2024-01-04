package com.sundata.edu.service;

import com.sundata.edu.api.bean.StudentRequest;
import com.sundata.edu.api.bean.UserRequest;
import com.sundata.edu.domain.OverallBean;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 用户 服务层
 *
 * @author whj
 * @date 2019-04-17 10:25:26
 */
public interface UserinfoService extends Service<Userinfo> {

    /**
     * 根据条件查询列表
     *
     * @param userinfoVo
     * @return
     */
    List<UserinfoVo> selectList(UserinfoVo userinfoVo);


    /**
     * 逻辑删除 修改isdelete字段
     *
     * @param ids
     * @return
     */
    int removes(String ids);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    Userinfo getUserInfoByUserId(String userId);

    /**
     * 同步用户信息
     *
     * @param request
     * @return
     */
    int synUserInfo(UserRequest request);

    List<UserinfoVo> getRoleUsers(Integer roleId, UserinfoVo userinfoVo);

    List<UserinfoVo> getRoleUnUsers(Integer roleId, UserinfoVo userinfoVo);

    List<Map<String,String>> getUserByidentity(UserinfoVo userinfoVo,Integer identity);

    Map<String,String> getUserByidentityActivity(Integer identity);

     Userinfo getUserinfoByUserID(String userid);
    /**
     * 查询学生总体情况列表
     * @param overallVo
     * @return
     */
    List<OverallBean> selectOverallList(OverallVo overallVo);

    OverallBean selectOneOverallInfo(OverallVo overallVo);

    /**
     * 根据年级班级id查询学生
     * @param params
     * @return
     */
    List<Map<String,Object>> getStudentsByGradeClassId(Map<String, Object> params);

    Map<String,String> getTeacherMapExcept(Integer identityTeacher);

    //查询单条数据
    StudentinfoVo selectOneStudent(UserinfoVo userinfoVo);

    //保存单条数据
    int saveStudentinfo(StudentinfoVo studentinfoVo);

    int insertAndUpdate(List<Object> stu) throws Exception;


    /**
     * 通过用户姓名搜索用户，formSelects-v4使用
     * @param userinfoVo
     * @return
     */
    List<NameValueVo> searchUser(UserinfoVo userinfoVo);

    /**
     * 扫描的方式维护学生信息
     * @param studentRequest
     * @return
     */
    AjaxResult scanStudentInfo(StudentRequest studentRequest);

}
