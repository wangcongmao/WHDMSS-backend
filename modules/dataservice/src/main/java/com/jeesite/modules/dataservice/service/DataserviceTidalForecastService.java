package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceTidalForecast;
import com.jeesite.modules.dataservice.dao.DataserviceTidalForecastDao;

/**
 * 潮流预报存储表Service
 * @author wangcm
 * @version 2025-03-09
 */
@Service
public class DataserviceTidalForecastService extends CrudService<DataserviceTidalForecastDao, DataserviceTidalForecast> {
	
	/**
	 * 获取单条数据
	 * @param dataserviceTidalForecast
	 * @return
	 */
	@Override
	public DataserviceTidalForecast get(DataserviceTidalForecast dataserviceTidalForecast) {
		return super.get(dataserviceTidalForecast);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceTidalForecast 查询条件
	 * @param dataserviceTidalForecast page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceTidalForecast> findPage(DataserviceTidalForecast dataserviceTidalForecast) {
		return super.findPage(dataserviceTidalForecast);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceTidalForecast
	 * @return
	 */
	@Override
	public List<DataserviceTidalForecast> findList(DataserviceTidalForecast dataserviceTidalForecast) {
		return super.findList(dataserviceTidalForecast);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceTidalForecast
	 */
	@Override
	@Transactional
	public void save(DataserviceTidalForecast dataserviceTidalForecast) {
		super.save(dataserviceTidalForecast);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceTidalForecast
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceTidalForecast dataserviceTidalForecast) {
		super.updateStatus(dataserviceTidalForecast);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceTidalForecast
	 */
	@Override
	@Transactional
	public void delete(DataserviceTidalForecast dataserviceTidalForecast) {
		super.delete(dataserviceTidalForecast);
	}
	
}