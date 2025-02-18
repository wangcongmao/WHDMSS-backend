package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceQualityRule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * qualityRuleDAO接口
 * @author wangcm
 * @version 2025-01-29
 */
@MyBatisDao
public interface DataserviceQualityRuleDao extends CrudDao<DataserviceQualityRule> {
	@Select("SELECT * FROM dataservice_quality_rule WHERE quality_device_id = #{dataDeviceId}")
    List<DataserviceQualityRule> getByDeviceId(String dataDeviceId);
}