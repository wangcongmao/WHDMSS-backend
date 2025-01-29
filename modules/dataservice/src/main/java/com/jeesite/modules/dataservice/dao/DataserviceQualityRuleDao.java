package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceQualityRule;

/**
 * qualityRuleDAO接口
 * @author wangcm
 * @version 2025-01-29
 */
@MyBatisDao
public interface DataserviceQualityRuleDao extends CrudDao<DataserviceQualityRule> {
	
}