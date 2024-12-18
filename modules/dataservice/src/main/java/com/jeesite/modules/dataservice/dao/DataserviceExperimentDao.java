package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceExperiment;

/**
 * experimentInfoDAO接口
 * @author wangcm
 * @version 2024-12-18
 */
@MyBatisDao
public interface DataserviceExperimentDao extends CrudDao<DataserviceExperiment> {
	
}