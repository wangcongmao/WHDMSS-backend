package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceChartPosition;
import com.jeesite.modules.dataservice.dao.DataserviceDeviceChartPositionDao;

import javax.annotation.Resource;

/**
 * 设备拓扑关系图位置Service
 * @author wangcm
 * @version 2025-04-18
 */
@Service
public class DataserviceDeviceChartPositionService extends CrudService<DataserviceDeviceChartPositionDao, DataserviceDeviceChartPosition> {
	@Resource
	DataserviceDeviceChartPositionDao dataserviceDeviceChartPositionDao;
	/**
	 * 获取单条数据
	 * @param dataserviceDeviceChartPosition
	 * @return
	 */
	@Override
	public DataserviceDeviceChartPosition get(DataserviceDeviceChartPosition dataserviceDeviceChartPosition) {
		return super.get(dataserviceDeviceChartPosition);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceDeviceChartPosition 查询条件
	 * @param dataserviceDeviceChartPosition page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceDeviceChartPosition> findPage(DataserviceDeviceChartPosition dataserviceDeviceChartPosition) {
		return super.findPage(dataserviceDeviceChartPosition);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceDeviceChartPosition
	 * @return
	 */
	@Override
	public List<DataserviceDeviceChartPosition> findList(DataserviceDeviceChartPosition dataserviceDeviceChartPosition) {
		return super.findList(dataserviceDeviceChartPosition);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceDeviceChartPosition
	 */
	@Override
	@Transactional
	public void save(DataserviceDeviceChartPosition dataserviceDeviceChartPosition) {
		super.save(dataserviceDeviceChartPosition);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceDeviceChartPosition
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceDeviceChartPosition dataserviceDeviceChartPosition) {
		super.updateStatus(dataserviceDeviceChartPosition);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceDeviceChartPosition
	 */
	@Override
	@Transactional
	public void delete(DataserviceDeviceChartPosition dataserviceDeviceChartPosition) {
		super.delete(dataserviceDeviceChartPosition);
	}

	@Transactional
	public List<DataserviceDeviceChartPosition> getAll() {
		return dataserviceDeviceChartPositionDao.getAll();
	}
}