package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataCondition;
import com.jeesite.modules.dataservice.dao.DataserviceDeviceDataConditionDao;

/**
 * dataservice_device_data_conditionService
 * @author wangcm
 * @version 2025-02-21
 */
@Service
public class DataserviceDeviceDataConditionService extends CrudService<DataserviceDeviceDataConditionDao, DataserviceDeviceDataCondition> {
	
	/**
	 * 获取单条数据
	 * @param dataserviceDeviceDataCondition
	 * @return
	 */
	@Override
	public DataserviceDeviceDataCondition get(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		return super.get(dataserviceDeviceDataCondition);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceDeviceDataCondition 查询条件
	 * @param dataserviceDeviceDataCondition page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceDeviceDataCondition> findPage(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		return super.findPage(dataserviceDeviceDataCondition);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceDeviceDataCondition
	 * @return
	 */
	@Override
	public List<DataserviceDeviceDataCondition> findList(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		return super.findList(dataserviceDeviceDataCondition);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceDeviceDataCondition
	 */
	@Override
	@Transactional
	public void save(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		super.save(dataserviceDeviceDataCondition);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceDeviceDataCondition
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		super.updateStatus(dataserviceDeviceDataCondition);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceDeviceDataCondition
	 */
	@Override
	@Transactional
	public void delete(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		super.delete(dataserviceDeviceDataCondition);
	}
	
}