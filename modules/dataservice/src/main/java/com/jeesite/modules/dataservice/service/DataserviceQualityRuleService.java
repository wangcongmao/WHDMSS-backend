package com.jeesite.modules.dataservice.service;

import java.util.List;

import com.jeesite.modules.dataservice.entity.DataserviceDeviceData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceQualityRule;
import com.jeesite.modules.dataservice.dao.DataserviceQualityRuleDao;

import javax.annotation.Resource;

/**
 * qualityRuleService
 * @author wangcm
 * @version 2025-01-29
 */
@Service
public class DataserviceQualityRuleService extends CrudService<DataserviceQualityRuleDao, DataserviceQualityRule> {

	@Resource
	private DataserviceQualityRuleDao dataserviceQualityRuleDao;
	
	/**
	 * 获取单条数据
	 * @param dataserviceQualityRule
	 * @return
	 */
	@Override
	public DataserviceQualityRule get(DataserviceQualityRule dataserviceQualityRule) {
		return super.get(dataserviceQualityRule);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceQualityRule 查询条件
	 * @param dataserviceQualityRule page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceQualityRule> findPage(DataserviceQualityRule dataserviceQualityRule) {
		return super.findPage(dataserviceQualityRule);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceQualityRule
	 * @return
	 */
	@Override
	public List<DataserviceQualityRule> findList(DataserviceQualityRule dataserviceQualityRule) {
		return super.findList(dataserviceQualityRule);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceQualityRule
	 */
	@Override
	@Transactional
	public void save(DataserviceQualityRule dataserviceQualityRule) {
		super.save(dataserviceQualityRule);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceQualityRule
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceQualityRule dataserviceQualityRule) {
		super.updateStatus(dataserviceQualityRule);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceQualityRule
	 */
	@Override
	@Transactional
	public void delete(DataserviceQualityRule dataserviceQualityRule) {
		super.delete(dataserviceQualityRule);
	}

	// 根据设备编号获取多个规则
	public List<DataserviceQualityRule> getRuleByDeviceId(String dataDeviceId) {
		return dataserviceQualityRuleDao.getByDeviceId(dataDeviceId);
	}

	/**
	 * 获取所有
	 * @param
	 * @return
	 */
	public List<DataserviceQualityRule> listAll() {
		return dataserviceQualityRuleDao.listAll();
	}

    public void deleteByDeviceId(String deviceId) {
		dataserviceQualityRuleDao.deleteByDeviceId(deviceId);
    }
}