package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceData;
import com.jeesite.modules.dataservice.dao.DataserviceDeviceDataDao;

/**
 * deviceDataService
 * @author wangcm
 * @version 2025-01-06
 */
@Service
public class DataserviceDeviceDataService extends CrudService<DataserviceDeviceDataDao, DataserviceDeviceData> {
	
	/**
	 * 获取单条数据
	 * @param dataserviceDeviceData
	 * @return
	 */
	@Override
	public DataserviceDeviceData get(DataserviceDeviceData dataserviceDeviceData) {
		return super.get(dataserviceDeviceData);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceDeviceData 查询条件
	 * @param dataserviceDeviceData page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceDeviceData> findPage(DataserviceDeviceData dataserviceDeviceData) {
		return super.findPage(dataserviceDeviceData);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceDeviceData
	 * @return
	 */
	@Override
	public List<DataserviceDeviceData> findList(DataserviceDeviceData dataserviceDeviceData) {
		return super.findList(dataserviceDeviceData);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceDeviceData
	 */
	@Override
	@Transactional
	public void save(DataserviceDeviceData dataserviceDeviceData) {
		super.save(dataserviceDeviceData);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceDeviceData
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceDeviceData dataserviceDeviceData) {
		super.updateStatus(dataserviceDeviceData);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceDeviceData
	 */
	@Override
	@Transactional
	public void delete(DataserviceDeviceData dataserviceDeviceData) {
		super.delete(dataserviceDeviceData);
	}
	
}