package com.maxmall.common.core.support;

import com.maxmall.common.base.exception.BusinessException;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * The class Base service.
 *
 * @param <T> the type parameter
 *
 * @author maxmall.net@gmail.com
 */
public abstract class BaseService<T> implements IService<T> {

	/**
	 * The Logger.
	 */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * The Mapper.
	 */
	@Autowired
	protected Mapper<T> mapper;

	/**
	 * Gets mapper.
	 *
	 * @return the mapper
	 */
	public Mapper<T> getMapper() {
		return mapper;
	}

	/**
	 * Select list.
	 *
	 * @param record the record
	 *
	 * @return the list
	 */
	@Override
	public List<T> select(T record) {
		return mapper.select(record);
	}

	/**
	 * Select by key t.
	 *
	 * @param key the key
	 *
	 * @return the t
	 */
	@Override
	public T selectByKey(Object key) {
		return mapper.selectByPrimaryKey(key);
	}

	/**
	 * Select all list.
	 *
	 * @return the list
	 */
	@Override
	public List<T> selectAll() {
		return mapper.selectAll();
	}

	/**
	 * Select one t.
	 *
	 * @param record the record
	 *
	 * @return the t
	 */
	@Override
	public T selectOne(T record) {
		return mapper.selectOne(record);
	}

	/**
	 * Select count int.
	 *
	 * @param record the record
	 *
	 * @return the int
	 */
	@Override
	public int selectCount(T record) {
		return mapper.selectCount(record);
	}

	/**
	 * Select by example list.
	 *
	 * @param example the example
	 *
	 * @return the list
	 */
	@Override
	public List<T> selectByExample(Object example) {
		return mapper.selectByExample(example);
	}

	/**
	 * Save int.
	 *
	 * @param record the record
	 *
	 * @return the int
	 */
	@Override
	public int save(T record) {
		return mapper.insertSelective(record);
	}

	/**
	 * Batch save int.
	 *
	 * @param list the list
	 *
	 * @return the int
	 */
	@Override
	public int batchSave(List<T> list) {
		int result = 0;
		for (T record : list) {
			int count = mapper.insertSelective(record);
			result += count;
		}
		return result;
	}

	/**
	 * Update int.
	 *
	 * @param entity the entity
	 *
	 * @return the int
	 */
	@Override
	public int update(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * Delete int.
	 *
	 * @param record the record
	 *
	 * @return the int
	 */
	@Override
	public int delete(T record) {
		return mapper.delete(record);
	}

	/**
	 * Delete by key int.
	 *
	 * @param key the key
	 *
	 * @return the int
	 */
	@Override
	public int deleteByKey(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}

	/**
	 * Batch delete int.
	 *
	 * @param list the list
	 *
	 * @return the int
	 */
	@Override
	public int batchDelete(List<T> list) {
		int result = 0;
		for (T record : list) {
			int count = mapper.delete(record);
			if (count < 1) {
				logger.error("删除数据失败");
				throw new BusinessException("删除数据失败!");
			}
			result += count;
		}
		return result;
	}

	/**
	 * Select count by example int.
	 *
	 * @param example the example
	 *
	 * @return the int
	 */
	@Override
	public int selectCountByExample(Object example) {
		return mapper.selectCountByExample(example);
	}

	/**
	 * Update by example int.
	 *
	 * @param record  the record
	 * @param example the example
	 *
	 * @return the int
	 */
	@Override
	public int updateByExample(T record, Object example) {
		return mapper.updateByExampleSelective(record, example);
	}

	/**
	 * Delete by example int.
	 *
	 * @param example the example
	 *
	 * @return the int
	 */
	@Override
	public int deleteByExample(Object example) {
		return mapper.deleteByPrimaryKey(example);
	}

	/**
	 * Select by row bounds list.
	 *
	 * @param record    the record
	 * @param rowBounds the row bounds
	 *
	 * @return the list
	 */
	@Override
	public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
		return mapper.selectByRowBounds(record, rowBounds);
	}

	/**
	 * Select by example and row bounds list.
	 *
	 * @param example   the example
	 * @param rowBounds the row bounds
	 *
	 * @return the list
	 */
	@Override
	public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
		return mapper.selectByExampleAndRowBounds(example, rowBounds);
	}

}
