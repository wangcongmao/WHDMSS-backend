package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceData;

/**
 * deviceDataDAO接口
 * @author wangcm
 * @version 2025-01-06
 */
@MyBatisDao
public interface DataserviceDeviceDataDao extends CrudDao<DataserviceDeviceData> {
	
}