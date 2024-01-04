package com.sundata.edu.service.impl;

import com.sundata.edu.dao.ClassinfoDao;
import com.sundata.edu.dao.GradeinfoDao;
import com.sundata.edu.dao.UserClassInfoDao;
import com.sundata.edu.domain.UserClassInfo;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.service.UserClassInfoService;
import com.sundata.edu.util.MenuUtils;
import com.sundata.edu.util.Utils;
import com.sundata.edu.vo.UserClassInfoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 班主任授权管理业务类
 */
@Service("userClassInfoService")
public class UserClassInfoServiceImpl extends AbstractService<UserClassInfo> implements UserClassInfoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserClassInfoDao userClassInfoDao;

    @Autowired
    private GradeinfoDao gradeinfoDao;

    @Autowired
    private ClassinfoDao classinfoDao;

    /**
     * 查询分配了班级的老师
     *
     * @return
     */
    @Override
    public List<UserClassInfo> selectUserClassInfoList(UserClassInfoVo userClassInfoVo) {

        List<UserClassInfo> userClassInfos = new ArrayList<UserClassInfo>();
        //查询老师
        List<Map<String, String>> usersList = userClassInfoDao.selectUsersList();

        if (CollectionUtils.isEmpty(usersList)){
            return userClassInfos;
        }

        Map<String, String> pamrams = new HashMap<String, String>();
        //遍历老师
        for (Map<String, String> users : usersList){
            UserClassInfo info = new UserClassInfo();
            String teacherId = users.get("teacherId");
            info.setTeacherId(teacherId);
            info.setTeacherName(users.get("teacherName"));
            info.setCreated(users.get("created"));

            pamrams.put("userId",teacherId);
            List<Map<String, String>> usersClasses = userClassInfoDao.getUsersClasses(pamrams);
            if (CollectionUtils.isEmpty(usersClasses)){
                continue;
            }
            List<String> classInfoList = new ArrayList<String>();
            for (Map<String, String> userClass : usersClasses) {
                classInfoList.add(userClass.get("gradeName") + userClass.get("className"));
            }

            info.setClassInfoList(classInfoList);
            userClassInfos.add(info);
        }

        return userClassInfos;
    }


    /**
     * 加载角色菜单列表树
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> getGradeClassTreeData(String teacherId) {
        List<Map<String, Object>> trees = new ArrayList<>();
        Map<String, Object> params = new HashMap<String, Object>();
        List<Map<String, Object>> gradeInfos = gradeinfoDao.selectGradeInfos(params);
        //如果老师id不为空说明是修改初始化树
        if (teacherId != null) {
            Map<String, String> param = new HashMap<String, String>();
            param.put("userId",teacherId);
            List<Map<String, String>> usersClasses = userClassInfoDao.getUsersClasses(param);
            trees = MenuUtils.getGradeInfosTrees(gradeInfos, true, usersClasses, true, trees);
        } else {
            trees = MenuUtils.getGradeInfosTrees(gradeInfos, false, null, true, trees);
        }

        for (Map<String, Object> map : gradeInfos){
            params.put("gradeId",map.get("id"));
            List<Map<String, Object>> classInfoList = classinfoDao.selectClassInfoByGradeId(params);
            if (teacherId != null) {
                Map<String, String> param = new HashMap<String, String>();
                param.put("userId",teacherId);
                List<Map<String, String>> usersClasses = userClassInfoDao.getUsersClasses(param);
                trees = MenuUtils.getClassInfosTrees(classInfoList, true, usersClasses, true, trees);
            } else {
                trees = MenuUtils.getClassInfosTrees(classInfoList, false, null, true, trees);
            }
        }

        return trees;
    }

    @Override
    public int addUserClassInfos(String teacherId, String ids) {
        String[] idArr = ids.split(",");
        List<UserClassInfo> userList = new ArrayList<UserClassInfo>();
        Map<String, Set<String>> gradeClassesMap = new HashMap<String, Set<String>>();
        Set<String> set = null;
        String key = "";
        for (int i = 0; i < idArr.length; i++){
            String id = idArr[i];
            if (id.length() == 4){
                if (StringUtils.isNotEmpty(key) && CollectionUtils.isNotEmpty(set)){
                    gradeClassesMap.put(key,set);
                }
                key = id;
                set = new HashSet<String>();
            }
            if (id.length() == 8){
                set.add(id);
            }
        }
        gradeClassesMap.put(key,set);

        List<UserClassInfo> userClassInfos = new ArrayList<UserClassInfo>();
        Set<String> keys = gradeClassesMap.keySet();
        for (String gradeId : keys){
            Set<String> classes = gradeClassesMap.get(gradeId);
            for (String classId : classes){
                UserClassInfo info = new UserClassInfo();
                info.setTeacherId(teacherId);
                info.setGradeId(gradeId);
                info.setClassId(classId);
                info.setCreated(Utils.getTimeInt() + "");
                userClassInfos.add(info);
            }
        }

        return userClassInfoDao.addUserClassInfos(userClassInfos);
    }

    @Override
    public int deleteUserClassInfos(String teacherId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId",teacherId);
        return userClassInfoDao.deleteUserClassInfos(params);
    }

    @Override
    public boolean checkUserClassInfo(String teacherId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId",teacherId);
        int count = userClassInfoDao.checkUserClassInfo(params);
        return count > 0 ? true : false;
    }

    @Override
    public List<String> queryClassIdsByUserId(Map<String, String> params) {
        return userClassInfoDao.queryClassIdsByUserId(params);
    }

}
