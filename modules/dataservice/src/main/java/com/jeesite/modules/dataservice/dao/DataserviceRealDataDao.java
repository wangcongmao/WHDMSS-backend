package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceRealData;

/**
 * 实测数据DAO接口
 * @author wangcm
 * @version 2025-02-25
 */
@MyBatisDao
public interface DataserviceRealDataDao extends CrudDao<DataserviceRealData> {
	
}