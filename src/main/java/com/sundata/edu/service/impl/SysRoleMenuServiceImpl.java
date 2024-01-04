package com.sundata.edu.service.impl;


import com.sundata.edu.domain.SysRoleMenu;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.SysRoleMenuDao;
import com.sundata.edu.service.SysRoleMenuService;
import com.sundata.edu.vo.SysRoleMenuVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色菜单 服务层实现
 *
 * @author whj
 * @date 2019-04-17 15:22:45
 */
@Service("sysRoleMenuService")
//@Transactional
public class SysRoleMenuServiceImpl extends AbstractService<SysRoleMenu> implements SysRoleMenuService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public List<SysRoleMenu> selectList(SysRoleMenuVo sysRoleMenuVo) {
        Example example = new Example(SysRoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("", "");
        return sysRoleMenuDao.selectByExample(example);
    }


    @Override
    public int removes(String ids) {
        Example example = new Example(SysRoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("id", idArray);
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        //sysRoleMenu.setIsdelete(1);
        return sysRoleMenuDao.updateByExampleSelective(sysRoleMenu, example);
    }

}
