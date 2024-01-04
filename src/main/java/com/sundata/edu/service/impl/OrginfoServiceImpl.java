package com.sundata.edu.service.impl;


import com.sundata.edu.dao.OrginfoDao;
import com.sundata.edu.domain.Orginfo;
import com.sundata.edu.enums.OperationEnum;
import com.sundata.edu.enums.StatusEnum;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.framework.core.Resource;
import com.sundata.edu.receive.bean.OrgRequest;
import com.sundata.edu.service.OrginfoService;
import com.sundata.edu.vo.IdTextItem;
import com.sundata.edu.vo.OrginfoVo;
import com.sundata.edu.vo.Ztree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构 服务层实现
 *
 * @author whj
 * @date 2019-04-17 10:41:06
 */
@Service("orginfoService")
//@Transactional
public class OrginfoServiceImpl extends AbstractService<Orginfo> implements OrginfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Resource resource;

    @Autowired
    private OrginfoDao orginfoDao;

    @Override
    public List<Orginfo> selectList(OrginfoVo orginfoVo) {
        return orginfoDao.selectList(orginfoVo);
    }


    @Override
    public List<Orginfo> selectbyorgidin(List<String> parentorgid){
        return orginfoDao.selectbyorgidin(parentorgid);
    }

    @Override
    public int removes(String ids) {
        Example example = new Example(Orginfo.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("orgId", idArray);
        Orginfo orginfo = new Orginfo();
        //orginfo.setIsdelete(1);
        return orginfoDao.updateByExampleSelective(orginfo, example);
    }

    /**
     * 根据机构id获取机构信息
     *
     * @param orgId
     * @return
     */
    @Override
    public Orginfo getOrgInfoByOrgId(String orgId) {
        Example example = new Example(Orginfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orgId", orgId);
        List<Orginfo> orginfos = orginfoDao.selectByExample(example);
        if (orginfos != null && orginfos.size() > 0) {
            return orginfos.get(0);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrgInfo(Orginfo orginfo, OperationEnum operationEnum) {
        if (orginfo.getParentOrgId() != null && orginfo.getParentOrgId().equals(resource.PARENT_ROOT_ORGID)) {
            //orginfo.setParentOrgId("0");
        }
        if (operationEnum == OperationEnum.INSERT) {
            orginfoDao.insertSelective(orginfo);
        } else {
            orginfoDao.updateByPrimaryKeySelective(orginfo);
        }
        orginfoDao.updateOrgInfoPaths();
    }

    @Override
    public List<Ztree> getOrgInfoTreeByParentOrgId(String parentOrgId) {
        List<Orginfo> orginfos = orginfoDao.getOrgInfosByParentOrgId(parentOrgId);
        return initZtree(orginfos);
    }

    @Override
    public List<Orginfo> getOrgInfosByParentOrgId(String parentOrgId) {
        return orginfoDao.getOrgInfosByParentOrgId(parentOrgId);
    }

    @Override
    public List<Ztree> getOrgInfoTree() {
        List<Orginfo> orginfos = orginfoDao.getOrgInfos();
        return initZtree(orginfos);
    }

    @Override
    public List<Orginfo> getOrgInfos() {
        return orginfoDao.getOrgInfos();
    }

    @Override
    public List<IdTextItem> getOrgInfoByOrgName(String orgName) {
        return orginfoDao.getOrgInfoByOrgName(orgName);
    }

    /**
     * 对象转部门树
     *
     * @param orginfos 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Orginfo> orginfos) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (Orginfo orginfo : orginfos) {
            if (StatusEnum.AVAILABLE.code() == orginfo.getStatus()) {
                Ztree ztree = new Ztree();
                ztree.setId(orginfo.getOrgId());
                ztree.setpId(orginfo.getParentOrgId());
                ztree.setName(orginfo.getOrgName());
                ztree.setTitle(orginfo.getOrgName());
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    @Override
    public void synOrgInfo(OrgRequest request) {
        Orginfo orginfo = orginfoDao.selectByPrimaryKey(request.getOutOrgId());
        String  parentId = null;
//        if (resource.PARENT_ROOT_ORGID.equals(request.getOutParentOrgId())) {
//            parentId = "0";
//        } else {
        Orginfo  parentOrganization = orginfoDao.selectByPrimaryKey(request.getOutParentOrgId());
        if (parentOrganization != null) {
            parentId = parentOrganization.getOrgId();
        }
        // }
        if (orginfo == null) {
            orginfo = new Orginfo();
            orginfo.setParentOrgId(parentId);
            orginfo.setOrgType(Integer.parseInt(request.getOrgType()));
            orginfo.setOrgName(request.getOrgName());
            orginfo.setStatus(request.getStatus());
            orginfo.setOrgId(request.getOutOrgId());
            orginfo.setElevel(request.getElevel());
            orginfoDao.insert(orginfo);
        } else {
            orginfo.setParentOrgId(parentId);
            orginfo.setOrgName(request.getOrgName());
            orginfo.setStatus(request.getStatus());
            orginfo.setOrgId(request.getOutOrgId());
            orginfo.setElevel(request.getElevel());
            orginfoDao.updateByPrimaryKeySelective(orginfo);
        }
        orginfoDao.updateOrgInfoPaths();
    }

}
