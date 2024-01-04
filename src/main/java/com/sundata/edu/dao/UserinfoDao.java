package com.sundata.edu.dao;

import com.sundata.edu.domain.OverallBean;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.core.Mapper;
import com.sundata.edu.vo.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户 数据层
 *
 * @author whj
 * @date 2019-04-17 10:25:26
 */
public interface UserinfoDao extends Mapper<Userinfo> {

    List<UserinfoVo> selectList(UserinfoVo userinfoVo);

    List<ExcelStudentVo> selectStudenList(UserinfoVo userinfoVo);

    List<UserinfoVo> getRoleUsers(@Param("roleId") Integer roleId, @Param("userinfo") UserinfoVo userinfo);

    List<UserinfoVo> getRoleUnUsers(@Param("roleId") Integer roleId, @Param("userinfo") UserinfoVo userinfo);

    List<UserinfoVo> getUserByidentity(UserinfoVo userinfoVo);

    /**
     * getUserinfoByUserID
     */

    public Userinfo getUserinfoByUserID(String user_id);

    /**
     * 查询学生总体情况
     * @param overallVo
     * @return
     */
    List<OverallBean> selectOverallList(OverallVo overallVo);

    /**
     * 根据学生id查询单个
     * @param overallVo
     * @return
     */
    OverallBean selectOneOverallInfo(OverallVo overallVo);

    List<Map<String,Object>> getStudentsByGradeClassId(Map<String, Object> params);

    List<UserinfoVo> getTeacherMapExcept(UserinfoVo userinfoVo);

    StudentinfoVo selectOneStudent(UserinfoVo userinfoVo);

    /**
     * 通过用户姓名搜索用户，formSelects-v4使用
     * @param userinfoVo
     * @return
     */
    List<UserinfoVo> searchUser(UserinfoVo userinfoVo);

    @MapKey("idCard")
    Map<String,Userinfo> getUserListByIdCard(List<String> list);


    List<Userinfo> getUserInfoByCondition(@Param("idCardList") ArrayList<String> idCardList, @Param("orgIds") List<String> orgIds);

    /**
     * 获取机构的管理员账号的角色所有的权限菜单id
     * @param orgId
     * @return
     */
    List<Integer> getMenuOrgAdmin(String orgId);
}
