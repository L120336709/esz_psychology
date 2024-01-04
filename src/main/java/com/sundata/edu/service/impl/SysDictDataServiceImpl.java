package com.sundata.edu.service.impl;


import com.sundata.edu.domain.SysDictData;
import com.sundata.edu.enums.StatusEnum;
import com.sundata.edu.framework.core.AbstractService;
import com.sundata.edu.dao.SysDictDataDao;
import com.sundata.edu.service.SysDictDataService;
import com.sundata.edu.vo.SysDictDataVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典数据 服务层实现
 *
 * @author whj
 * @date 2019-04-17 16:06:10
 */
@Service("sysDictDataService")
//@Transactional
public class SysDictDataServiceImpl extends AbstractService<SysDictData> implements SysDictDataService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysDictDataDao sysDictDataDao;

    @Override
    public List<SysDictData> selectList(SysDictDataVo sysDictDataVo) {
        Example example = new Example(SysDictData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeCode", sysDictDataVo.getTypeCode());
        if (sysDictDataVo.getStatus() != null) {
            criteria.andEqualTo("status", sysDictDataVo.getStatus());
        }
        if (StringUtils.isNotEmpty(sysDictDataVo.getDataLabel())) {
            criteria.andLike("dataLabel", "%" + sysDictDataVo.getDataLabel() + "%");
        }
        example.orderBy("dataSort");
        return sysDictDataDao.selectByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int removes(String ids) {
        Example example = new Example(SysDictData.class);
        Example.Criteria criteria = example.createCriteria();
        List<Object> idArray = new ArrayList<>();
        for (String id : ids.split(",")) {
            idArray.add(id);
        }
        criteria.andIn("dataId", idArray);
        SysDictData sysDictData = new SysDictData();
        sysDictData.setStatus(StatusEnum.DISABLED.code());
        return sysDictDataDao.updateByExampleSelective(sysDictData, example);
    }

    /**
     * 根据类型获取字典数据列表
     *
     * @param typeCode
     * @return
     */
    @Override
    public List<SysDictData> getDictDataByTypeCode(String typeCode) {
        Example example = new Example(SysDictData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeCode", typeCode);
        criteria.andEqualTo("status", StatusEnum.AVAILABLE.code());
        example.orderBy("dataSort");
        return sysDictDataDao.selectByExample(example);
    }

    /**
     * 根据字段类型和字段值获取字典标题
     *
     * @param typeCode
     * @param dataValue
     * @return
     */
    @Override
    public String getDataLabel(String typeCode, String dataValue) {
        Example example = new Example(SysDictData.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeCode", typeCode);
        criteria.andEqualTo("dataValue", dataValue);
        List<SysDictData> dictDataList = sysDictDataDao.selectByExample(example);
        if (dictDataList != null && dictDataList.size() > 0) {
            return dictDataList.get(0).getDataLabel();
        }
        return "";
    }

}
