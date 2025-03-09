package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceWaveForecast;

/**
 * 波浪预报存储表DAO接口
 * @author wangcm
 * @version 2025-03-09
 */
@MyBatisDao
public interface DataserviceWaveForecastDao extends CrudDao<DataserviceWaveForecast> {
	
}