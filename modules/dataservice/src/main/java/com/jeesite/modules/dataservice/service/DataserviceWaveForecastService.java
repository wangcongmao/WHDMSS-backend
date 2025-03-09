package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceWaveForecast;
import com.jeesite.modules.dataservice.dao.DataserviceWaveForecastDao;

/**
 * 波浪预报存储表Service
 * @author wangcm
 * @version 2025-03-09
 */
@Service
public class DataserviceWaveForecastService extends CrudService<DataserviceWaveForecastDao, DataserviceWaveForecast> {
	
	/**
	 * 获取单条数据
	 * @param dataserviceWaveForecast
	 * @return
	 */
	@Override
	public DataserviceWaveForecast get(DataserviceWaveForecast dataserviceWaveForecast) {
		return super.get(dataserviceWaveForecast);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceWaveForecast 查询条件
	 * @param dataserviceWaveForecast page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceWaveForecast> findPage(DataserviceWaveForecast dataserviceWaveForecast) {
		return super.findPage(dataserviceWaveForecast);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceWaveForecast
	 * @return
	 */
	@Override
	public List<DataserviceWaveForecast> findList(DataserviceWaveForecast dataserviceWaveForecast) {
		return super.findList(dataserviceWaveForecast);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceWaveForecast
	 */
	@Override
	@Transactional
	public void save(DataserviceWaveForecast dataserviceWaveForecast) {
		super.save(dataserviceWaveForecast);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceWaveForecast
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceWaveForecast dataserviceWaveForecast) {
		super.updateStatus(dataserviceWaveForecast);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceWaveForecast
	 */
	@Override
	@Transactional
	public void delete(DataserviceWaveForecast dataserviceWaveForecast) {
		super.delete(dataserviceWaveForecast);
	}
	
}