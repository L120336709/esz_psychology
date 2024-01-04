package com.sundata.edu.framework.web.service;

import com.sundata.edu.dao.MzDao;
import com.sundata.edu.domain.Mz;
import com.sundata.edu.domain.SysDictData;
import com.sundata.edu.init.SystemInit;
import com.sundata.edu.service.*;
import com.sundata.edu.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("dict")
public class DictService {

    @Autowired
    private SysDictDataService sysDictDataService;



    @Autowired
    private OrginfoService orginfoService;



    @Autowired
    private UserinfoService userinfoService;


    @Autowired
    MzDao mzDao;



    /**
     * 根据字典类型查询字典数据信息
     *
     * @param typeCode 字典类型
     * @return 参数键值
     */
    public List<SysDictData> getDictDataByTypeCode(String typeCode) {
        return sysDictDataService.getDictDataByTypeCode(typeCode);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getDataLabel(String dictType, String dictValue) {
        return sysDictDataService.getDataLabel(dictType, dictValue);
    }


    /**
     * 获取活动类型map
     * @return
     */
    public Map<String, String> getActivityTypeMap(){
        return SystemInit.activityTypeMap;
    }

    /**
     * 获取活动级别map
     * @return
     */
    public Map<String, String> getActivityLevelMap(){
        return SystemInit.activityLevelMap;
    }

    public Map<String, String> getTeacherMap(){
        Map<String, String> userList = userinfoService.getUserByidentityActivity(Constant.IDENTITY_TEACHER);
        return userList;
    }

    public Map<String, String> getTeacherMapExcept(){
        Map<String, String> userList = userinfoService.getTeacherMapExcept(Constant.IDENTITY_TEACHER);
        return userList;
    }

    public Map<String, String> getStudentMap(){
        Map<String, String> userList = userinfoService.getUserByidentityActivity(Constant.IDENTITY_STUDENT);
        return userList;
    }

    public List<Mz> getMz(){
        List<Mz> mzs = mzDao.selectAll();
        return mzs;

    }

}
