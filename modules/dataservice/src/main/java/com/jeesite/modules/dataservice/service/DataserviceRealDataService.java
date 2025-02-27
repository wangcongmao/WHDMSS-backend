package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceRealData;
import com.jeesite.modules.dataservice.dao.DataserviceRealDataDao;

/**
 * 实测数据Service
 * @author wangcm
 * @version 2025-02-25
 */
@Service
public class DataserviceRealDataService extends CrudService<DataserviceRealDataDao, DataserviceRealData> {
	
	/**
	 * 获取单条数据
	 * @param dataserviceRealData
	 * @return
	 */
	@Override
	public DataserviceRealData get(DataserviceRealData dataserviceRealData) {
		return super.get(dataserviceRealData);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceRealData 查询条件
	 * @param dataserviceRealData page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceRealData> findPage(DataserviceRealData dataserviceRealData) {
		return super.findPage(dataserviceRealData);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceRealData
	 * @return
	 */
	@Override
	public List<DataserviceRealData> findList(DataserviceRealData dataserviceRealData) {
		return super.findList(dataserviceRealData);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceRealData
	 */
	@Override
	@Transactional
	public void save(DataserviceRealData dataserviceRealData) {
		super.save(dataserviceRealData);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceRealData
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceRealData dataserviceRealData) {
		super.updateStatus(dataserviceRealData);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceRealData
	 */
	@Override
	@Transactional
	public void delete(DataserviceRealData dataserviceRealData) {
		super.delete(dataserviceRealData);
	}
	
}