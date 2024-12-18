package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceExperiment;
import com.jeesite.modules.dataservice.dao.DataserviceExperimentDao;

/**
 * experimentInfoService
 * @author wangcm
 * @version 2024-12-18
 */
@Service
public class DataserviceExperimentService extends CrudService<DataserviceExperimentDao, DataserviceExperiment> {
	
	/**
	 * 获取单条数据
	 * @param dataserviceExperiment
	 * @return
	 */
	@Override
	public DataserviceExperiment get(DataserviceExperiment dataserviceExperiment) {
		return super.get(dataserviceExperiment);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceExperiment 查询条件
	 * @param dataserviceExperiment page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceExperiment> findPage(DataserviceExperiment dataserviceExperiment) {
		return super.findPage(dataserviceExperiment);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceExperiment
	 * @return
	 */
	@Override
	public List<DataserviceExperiment> findList(DataserviceExperiment dataserviceExperiment) {
		return super.findList(dataserviceExperiment);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceExperiment
	 */
	@Override
	@Transactional
	public void save(DataserviceExperiment dataserviceExperiment) {
		super.save(dataserviceExperiment);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceExperiment
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceExperiment dataserviceExperiment) {
		super.updateStatus(dataserviceExperiment);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceExperiment
	 */
	@Override
	@Transactional
	public void delete(DataserviceExperiment dataserviceExperiment) {
		super.delete(dataserviceExperiment);
	}
	
}