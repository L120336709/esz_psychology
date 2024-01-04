package com.sundata.edu.framework.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {
	/**
	 * 保存数据 null的属性不会保存，会使用数据库默认值
	 * 
	 * @param model
	 */
	int insertSelective(T model);

	/**
	 * 批量保存 null的属性不会保存，会使用数据库默认值
	 * 
	 * @param models
	 */
	int insertList(List<T> models);

	/**
	 * 通过主键删除 如果是联合主键传入对象
	 * 
	 * @param key
	 * @return
	 */
	int deleteByPrimaryKey(Object key);

	/**
	 * 批量刪除 eg：ids -> “1,2,3,4”
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByIds(String ids);

	/**
	 * 根据主键更新
	 * 
	 * @param model
	 * @return
	 */
	int updateByPrimaryKeySelective(T model);

	/**
	 * 通过主键查找 
	 * 
	 * @param key 如果是联合主键传入对象
	 * @return
	 */
	T selectByPrimaryKey(Object key);

	/**
	 * 通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 * @throws TooManyResultsException
	 */
	T selectOneByField(String fieldName, Object value);

	/**
	 * 通过多个ID查找//eg：ids -> “1,2,3,4”
	 * 
	 * @param ids
	 * @return
	 */
	List<T> selectByIds(String ids);

	/**
	 * 根据条件查找
	 * 
	 * @param condition
	 * @return
	 */
	List<T> selectByCondition(Condition condition);

	int updateByExampleSelective(T record, Example example);

	List<T> selectByExample(Example example);

}
