package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceaksk;
import com.jeesite.modules.dataservice.dao.DataserviceDeviceakskDao;

import javax.annotation.Resource;

/**
 * 设备AkSkService
 * @author wangcm
 * @version 2025-03-24
 */
@Service
public class DataserviceDeviceakskService extends CrudService<DataserviceDeviceakskDao, DataserviceDeviceaksk> {

	@Resource
	private DataserviceDeviceakskDao dataserviceDeviceakskDao;
	
	/**
	 * 获取单条数据
	 * @param dataserviceDeviceaksk
	 * @return
	 */
	@Override
	public DataserviceDeviceaksk get(DataserviceDeviceaksk dataserviceDeviceaksk) {
		return super.get(dataserviceDeviceaksk);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceDeviceaksk 查询条件
	 * @param dataserviceDeviceaksk page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceDeviceaksk> findPage(DataserviceDeviceaksk dataserviceDeviceaksk) {
		return super.findPage(dataserviceDeviceaksk);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceDeviceaksk
	 * @return
	 */
	@Override
	public List<DataserviceDeviceaksk> findList(DataserviceDeviceaksk dataserviceDeviceaksk) {
		return super.findList(dataserviceDeviceaksk);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceDeviceaksk
	 */
	@Override
	@Transactional
	public void save(DataserviceDeviceaksk dataserviceDeviceaksk) {
		super.save(dataserviceDeviceaksk);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceDeviceaksk
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceDeviceaksk dataserviceDeviceaksk) {
		super.updateStatus(dataserviceDeviceaksk);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceDeviceaksk
	 */
	@Override
	@Transactional
	public void delete(DataserviceDeviceaksk dataserviceDeviceaksk) {
		super.delete(dataserviceDeviceaksk);
	}

	@Transactional
	public DataserviceDeviceaksk getById(String deviceId) {
		return dataserviceDeviceakskDao.getById(deviceId);
	}

	@Transactional
	public void deleteByDeviceId(String deviceId) {
		dataserviceDeviceakskDao.deleteByDeviceId(deviceId);
	}

	@Transactional
	public String getSkByAk(String accessKey) {
		return dataserviceDeviceakskDao.getSkByAk(accessKey);
	}
}