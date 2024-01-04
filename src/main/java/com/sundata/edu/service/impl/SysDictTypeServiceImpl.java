package com.sundata.edu.service.impl;


import com.sundata.edu.domain.SysDictType;
import com.sundata.edu.enums.StatusEnum;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.SysDictTypeDao;
import com.sundata.edu.service.SysDictTypeService;
import com.sundata.edu.vo.SysDictTypeVo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 字段类型 服务层实现
 *
 * @author whj
 * @date 2019-04-17 16:06:10
 */
@Service("sysDictTypeService")
//@Transactional
public class SysDictTypeServiceImpl extends AbstractService<SysDictType> implements SysDictTypeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SysDictTypeDao sysDictTypeDao;

    @Override
    public List<SysDictType> selectList(SysDictTypeVo sysDictTypeVo) {
        Example example = new Example(SysDictType.class);
        Example.Criteria criteria = example.createCriteria();
        if (sysDictTypeVo.getStatus() != null) {
            criteria.andEqualTo("status", sysDictTypeVo.getStatus());
        }
        if (StringUtils.isNotEmpty(sysDictTypeVo.getTypeName())) {
            criteria.andLike("typeName", "%" + sysDictTypeVo.getTypeName() + "%");
        }
        if (StringUtils.isNotEmpty(sysDictTypeVo.getTypeCode())) {
            criteria.andLike("typeCode", "%" + sysDictTypeVo.getTypeCode() + "%");
        }
        if (sysDictTypeVo.getParams() != null && StringUtils.isNotEmpty(sysDictTypeVo.getParams().get("beginTime") + "")) {
            String time = sysDictTypeVo.getParams().get("beginTime") + "";
            criteria.andGreaterThanOrEqualTo("created", time);
        }
        if (sysDictTypeVo.getParams() != null && StringUtils.isNotEmpty(sysDictTypeVo.getParams().get("endTime") + "")) {
            String time = sysDictTypeVo.getParams().get("endTime") + "";
            DateTime dateTime = new DateTime(time);
            criteria.andLessThan("created", dateTime.plusDays(1).toString("yyyy-MM-dd"));
        }
        example.orderBy("typeId");
        return sysDictTypeDao.selectByExample(example);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removes(String ids) {
        Example example = new Example(SysDictType.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("typeId", idArray);
        SysDictType sysDictType = new SysDictType();
        sysDictType.setStatus(StatusEnum.DISABLED.code());
        sysDictType.setUpdated(new Date());
        return sysDictTypeDao.updateByExampleSelective(sysDictType, example);
    }

    /**
     * 根据dictType检查是否存在
     *
     * @param typeCode
     * @param typeId
     * @return
     */
    @Override
    public boolean checkExistSysDictType(String typeCode, Integer typeId) {
        Example example = new Example(SysDictType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeCode", typeCode);
        criteria.andEqualTo("status", StatusEnum.AVAILABLE.code());
        if (typeId != null) {
            criteria.andNotEqualTo("typeId", typeId);
        }
        List<SysDictType> sysDictTypes = sysDictTypeDao.selectByExample(example);
        return sysDictTypes.size() > 0;
    }

}
