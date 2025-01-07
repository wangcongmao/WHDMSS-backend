//package com.jeesite.modules.dataservice.service;
//
//import java.util.List;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.jeesite.common.entity.Page;
//import com.jeesite.common.service.CrudService;
//import com.jeesite.modules.dataservice.entity.DataserviceDeviceStructure;
//import com.jeesite.modules.dataservice.dao.DataserviceDeviceStructureDao;
//
///**
// * deviceStructureService
// * @author wangcm
// * @version 2025-01-06
// */
//@Service
//public class DataserviceDeviceStructureService extends CrudService<DataserviceDeviceStructureDao, DataserviceDeviceStructure> {
//
//	/**
//	 * 获取单条数据
//	 * @param dataserviceDeviceStructure
//	 * @return
//	 */
//	@Override
//	public DataserviceDeviceStructure get(DataserviceDeviceStructure dataserviceDeviceStructure) {
//		return super.get(dataserviceDeviceStructure);
//	}
//
//	/**
//	 * 查询分页数据
//	 * @param dataserviceDeviceStructure 查询条件
//	 * @param dataserviceDeviceStructure page 分页对象
//	 * @return
//	 */
//	@Override
//	public Page<DataserviceDeviceStructure> findPage(DataserviceDeviceStructure dataserviceDeviceStructure) {
//		return super.findPage(dataserviceDeviceStructure);
//	}
//
//	/**
//	 * 查询列表数据
//	 * @param dataserviceDeviceStructure
//	 * @return
//	 */
//	@Override
//	public List<DataserviceDeviceStructure> findList(DataserviceDeviceStructure dataserviceDeviceStructure) {
//		return super.findList(dataserviceDeviceStructure);
//	}
//
//	/**
//	 * 保存数据（插入或更新）
//	 * @param dataserviceDeviceStructure
//	 */
//	@Override
//	@Transactional
//	public void save(DataserviceDeviceStructure dataserviceDeviceStructure) {
//		super.save(dataserviceDeviceStructure);
//	}
//
//	/**
//	 * 更新状态
//	 * @param dataserviceDeviceStructure
//	 */
//	@Override
//	@Transactional
//	public void updateStatus(DataserviceDeviceStructure dataserviceDeviceStructure) {
//		super.updateStatus(dataserviceDeviceStructure);
//	}
//
//	/**
//	 * 删除数据
//	 * @param dataserviceDeviceStructure
//	 */
//	@Override
//	@Transactional
//	public void delete(DataserviceDeviceStructure dataserviceDeviceStructure) {
//		super.delete(dataserviceDeviceStructure);
//	}
//
//}