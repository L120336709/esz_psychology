package com.sundata.edu.dao;

import com.sundata.edu.domain.Orginfo;
import com.sundata.edu.framework.core.Mapper;
import com.sundata.edu.vo.OrginfoVo;
import com.sundata.edu.vo.IdTextItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构 数据层
 *
 * @author whj
 * @date 2019-04-17 10:41:06
 */
public interface OrginfoDao extends Mapper<Orginfo> {

    void updateOrgInfoPaths();

    List<Orginfo> getOrgInfosByParentOrgId(String parentOrgId);

    List<Orginfo> getOrgInfos();

    List<IdTextItem> getOrgInfoByOrgName(@Param("orgName") String orgName);

    List<Orginfo> selectList(OrginfoVo orginfoVo);

    List<Orginfo> selectbyorgidin(@Param("list")List<String> parentorgid);

}
