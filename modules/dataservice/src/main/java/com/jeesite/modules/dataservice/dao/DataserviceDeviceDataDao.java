package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceData;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * deviceDataDAO接口
 * @author wangcm
 * @version 2025-01-06
 */
@MyBatisDao
public interface DataserviceDeviceDataDao extends CrudDao<DataserviceDeviceData> {
    // 根据设备编号查询多个设备数据
    @Select("SELECT * FROM dataservice_device_data WHERE data_device_id = #{dataDeviceId}")
    List<DataserviceDeviceData> getByDeviceId(String dataDeviceId);
}