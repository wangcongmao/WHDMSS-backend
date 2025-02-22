package com.jeesite.modules.dataservice.service;

import java.util.*;

import com.jeesite.modules.dataservice.dao.DataserviceDeviceDataDao;
import com.jeesite.modules.dataservice.entity.support.ParamDataCondition;
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataCondition;
import com.jeesite.modules.dataservice.dao.DataserviceDeviceDataConditionDao;

import javax.annotation.Resource;

/**
 * dataservice_device_data_conditionService
 * @author wangcm
 * @version 2025-02-21
 */
@Service
public class DataserviceDeviceDataConditionService extends CrudService<DataserviceDeviceDataConditionDao, DataserviceDeviceDataCondition> {

	@Resource
	private DataserviceDeviceDataConditionDao dataserviceDeviceDataConditionDao;

	/**
	 * 获取单条数据
	 * @param dataserviceDeviceDataCondition
	 * @return
	 */
	@Override
	public DataserviceDeviceDataCondition get(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		return super.get(dataserviceDeviceDataCondition);
	}

	/**
	 * 查询分页数据
	 * @param dataserviceDeviceDataCondition 查询条件
	 * @param dataserviceDeviceDataCondition page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceDeviceDataCondition> findPage(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		return super.findPage(dataserviceDeviceDataCondition);
	}

	/**
	 * 查询列表数据
	 * @param dataserviceDeviceDataCondition
	 * @return
	 */
	@Override
	public List<DataserviceDeviceDataCondition> findList(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		return super.findList(dataserviceDeviceDataCondition);
	}

	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceDeviceDataCondition
	 */
	@Override
	@Transactional
	public void save(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		super.save(dataserviceDeviceDataCondition);
	}

	/**
	 * 新增
	 * @param dataserviceDeviceDataCondition
	 */
	@Transactional
	public void insertDeviceDataCondition(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		dataserviceDeviceDataConditionDao.insertDeviceDataCondition(dataserviceDeviceDataCondition);
	}

	/**.
	 * 删除
	 * @param structureDeviceId
	 */
	@Transactional
	public void deleteByStructureDeviceId(String structureDeviceId) {
		dataserviceDeviceDataConditionDao.deleteByStructureDeviceId(structureDeviceId);
	}

	/**
	 * 将某个类型自增
	 * @param structureDeviceId
	 * @param deviceParamName
	 * @param conditionType
	 */
	@Transactional
	public void incrementNums(String structureDeviceId, String deviceParamName, int conditionType) {
		dataserviceDeviceDataConditionDao.incrementNums(structureDeviceId, deviceParamName, conditionType);
	}

	/**
	 * 将某个类型自减
	 * @param structureDeviceId
	 * @param deviceParamName
	 * @param conditionType
	 */
	@Transactional
	public void decrementNums(String structureDeviceId, String deviceParamName, int conditionType) {
		dataserviceDeviceDataConditionDao.decrementNums(structureDeviceId, deviceParamName, conditionType);
	}

	/**
	 * 更新状态
	 * @param dataserviceDeviceDataCondition
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		super.updateStatus(dataserviceDeviceDataCondition);
	}

	/**
	 * 删除数据
	 * @param dataserviceDeviceDataCondition
	 */
	@Override
	@Transactional
	public void delete(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		super.delete(dataserviceDeviceDataCondition);
	}

	/**
	 * 获取设备参数数据状况
	 * @param
	 *
	 */
	@Transactional
	public List<ParamDataCondition> getParamConditionByDeviceId(String dataDeviceId) {
		List<DataserviceDeviceDataCondition> paramConditionByDeviceId = dataserviceDeviceDataConditionDao.getParamConditionByDeviceId(dataDeviceId);
		// 将参数封装到 list？不行需要name；map？新建一个对象 string 参数名，normalCount，uncertainCount，errorCount三个属性
		List<ParamDataCondition> res = new ArrayList<>();
		Map<String, ParamDataCondition> map = new HashMap<>();
		for (DataserviceDeviceDataCondition dataserviceDeviceDataCondition : paramConditionByDeviceId) {
			if (!map.containsKey(dataserviceDeviceDataCondition.getDeviceParamName())) {
				ParamDataCondition paramDataCondition1 = new ParamDataCondition();
				paramDataCondition1.setParamName(dataserviceDeviceDataCondition.getDeviceParamName());
				switch (dataserviceDeviceDataCondition.getConditionType()) {
					case 0:
						paramDataCondition1.setNormalCount(dataserviceDeviceDataCondition.getNums());
						break;
					case 1:
						paramDataCondition1.setUncertainCount(dataserviceDeviceDataCondition.getNums());
						break;
					case 2:
						paramDataCondition1.setErrorCount(dataserviceDeviceDataCondition.getNums());
						break;
				}
				map.put(dataserviceDeviceDataCondition.getDeviceParamName(), new ParamDataCondition(paramDataCondition1));
			} else {
				ParamDataCondition paramDataCondition1 = map.get(dataserviceDeviceDataCondition.getDeviceParamName());
				switch (dataserviceDeviceDataCondition.getConditionType()) {
					case 0:
						paramDataCondition1.setNormalCount(dataserviceDeviceDataCondition.getNums());
						break;
					case 1:
						paramDataCondition1.setUncertainCount(dataserviceDeviceDataCondition.getNums());
						break;
					case 2:
						paramDataCondition1.setErrorCount(dataserviceDeviceDataCondition.getNums());
						break;
				}
				map.put(dataserviceDeviceDataCondition.getDeviceParamName(), paramDataCondition1);
			}
		}
		Set<String> strings = map.keySet();
		for (String string : strings) {
			ParamDataCondition paramDataCondition = map.get(string);
			res.add(new ParamDataCondition(paramDataCondition));
		}
		return res;
	}


}