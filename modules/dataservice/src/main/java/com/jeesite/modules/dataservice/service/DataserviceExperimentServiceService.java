package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceExperimentService;
import com.jeesite.modules.dataservice.dao.DataserviceExperimentServiceDao;

/**
 * experimentDeviceService
 * @author wangcm
 * @version 2024-12-18
 */
@Service
public class DataserviceExperimentServiceService extends CrudService<DataserviceExperimentServiceDao, DataserviceExperimentService> {
	
	/**
	 * 获取单条数据
	 * @param dataserviceExperimentService
	 * @return
	 */
	@Override
	public DataserviceExperimentService get(DataserviceExperimentService dataserviceExperimentService) {
		return super.get(dataserviceExperimentService);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceExperimentService 查询条件
	 * @param dataserviceExperimentService page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceExperimentService> findPage(DataserviceExperimentService dataserviceExperimentService) {
		return super.findPage(dataserviceExperimentService);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceExperimentService
	 * @return
	 */
	@Override
	public List<DataserviceExperimentService> findList(DataserviceExperimentService dataserviceExperimentService) {
		return super.findList(dataserviceExperimentService);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceExperimentService
	 */
	@Override
	@Transactional
	public void save(DataserviceExperimentService dataserviceExperimentService) {
		super.save(dataserviceExperimentService);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceExperimentService
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceExperimentService dataserviceExperimentService) {
		super.updateStatus(dataserviceExperimentService);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceExperimentService
	 */
	@Override
	@Transactional
	public void delete(DataserviceExperimentService dataserviceExperimentService) {
		super.delete(dataserviceExperimentService);
	}
	
}