package com.sundata.edu.service;

import com.sundata.edu.domain.Orginfo;
import com.sundata.edu.enums.OperationEnum;
import com.sundata.edu.framework.core.Service;
import com.sundata.edu.receive.bean.OrgRequest;
import com.sundata.edu.vo.IdTextItem;
import com.sundata.edu.vo.OrginfoVo;
import com.sundata.edu.vo.Ztree;

import java.util.List;

/**
 * 机构 服务层
 *
 * @author whj
 * @date 2019-04-17 10:41:06
 */
public interface OrginfoService extends Service<Orginfo> {

    /**
     * 根据条件查询列表
     *
     * @param orginfoVo
     * @return
     */
    List<Orginfo> selectList(OrginfoVo orginfoVo);

    List<Orginfo> selectbyorgidin(List<String> parentorgid);
    /**
     * 逻辑删除 修改isdelete字段
     *
     * @param ids
     * @return
     */
    int removes(String ids);

    /**
     * 根据机构id获取机构信息
     *
     * @param orgId
     * @return
     */
    Orginfo getOrgInfoByOrgId(String orgId);

    void saveOrgInfo(Orginfo orginfo, OperationEnum operationEnum);

    List<Ztree> getOrgInfoTreeByParentOrgId(String parentOrgId);

    List<Orginfo> getOrgInfosByParentOrgId(String parentOrgId);

    List<Ztree> getOrgInfoTree();

    List<Orginfo> getOrgInfos();

    List<IdTextItem> getOrgInfoByOrgName(String orgName);

    void synOrgInfo(OrgRequest orgRequest);




}
