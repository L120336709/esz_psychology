package com.sundata.edu.framework.core;


import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected Mapper<T> mapper;

    private Class<T> modelClass;

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public int insertSelective(T model) {
        return mapper.insertSelective(model);
    }

    @Override
    public int insertList(List<T> models) {
        return mapper.insertList(models);
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }

    @Override
    public int updateByPrimaryKeySelective(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public T selectOneByField(String fieldName, Object value) {
        Example example = new Example(modelClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(fieldName, value);
        List<T> list = mapper.selectByExample(example);
        if (list != null) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<T> selectByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> selectByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    @Override
    public int updateByExampleSelective(T record, Example example) {

        return mapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<T> selectByExample(Example example) {

        return mapper.selectByExample(example);
    }

}
