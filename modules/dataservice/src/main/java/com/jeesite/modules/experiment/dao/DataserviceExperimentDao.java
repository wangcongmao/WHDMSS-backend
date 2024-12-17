package com.jeesite.modules.experiment.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.experiment.entity.DataserviceExperiment;

/**
 * 试验信息表DAO接口
 * @author wangcm
 * @version 2024-12-16
 */
@MyBatisDao
public interface DataserviceExperimentDao extends CrudDao<DataserviceExperiment> {
	
}