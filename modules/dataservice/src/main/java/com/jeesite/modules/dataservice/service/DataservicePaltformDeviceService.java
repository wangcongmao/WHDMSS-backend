package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.dataservice.entity.DataservicePaltformDevice;
import com.jeesite.modules.dataservice.dao.DataservicePaltformDeviceDao;

/**
 * platformDeviceService
 * @author wangcm
 * @version 2024-12-27
 */
@Service
public class DataservicePaltformDeviceService extends TreeService<DataservicePaltformDeviceDao, DataservicePaltformDevice> {
	
	/**
	 * 获取单条数据
	 * @param dataservicePaltformDevice
	 * @return
	 */
	@Override
	public DataservicePaltformDevice get(DataservicePaltformDevice dataservicePaltformDevice) {
		return super.get(dataservicePaltformDevice);
	}
	
	/**
	 * 查询分页数据
	 * @param dataservicePaltformDevice 查询条件
	 * @param dataservicePaltformDevice page 分页对象
	 * @return
	 */
	@Override
	public Page<DataservicePaltformDevice> findPage(DataservicePaltformDevice dataservicePaltformDevice) {
		return super.findPage(dataservicePaltformDevice);
	}
	
	/**
	 * 查询列表数据
	 * @param dataservicePaltformDevice
	 * @return
	 */
	@Override
	public List<DataservicePaltformDevice> findList(DataservicePaltformDevice dataservicePaltformDevice) {
		return super.findList(dataservicePaltformDevice);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataservicePaltformDevice
	 */
	@Override
	@Transactional
	public void save(DataservicePaltformDevice dataservicePaltformDevice) {
		super.save(dataservicePaltformDevice);
	}
	
	/**
	 * 更新状态
	 * @param dataservicePaltformDevice
	 */
	@Override
	@Transactional
	public void updateStatus(DataservicePaltformDevice dataservicePaltformDevice) {
		super.updateStatus(dataservicePaltformDevice);
	}
	
	/**
	 * 删除数据
	 * @param dataservicePaltformDevice
	 */
	@Override
	@Transactional
	public void delete(DataservicePaltformDevice dataservicePaltformDevice) {
		super.delete(dataservicePaltformDevice);
	}
	
}