package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataEverydayCounts;
import com.jeesite.modules.dataservice.entity.vo.DataserviceDeviceDataEverydayCountsVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 设备每天数据量DAO接口
 * @author wangcm
 * @version 2025-02-23
 */
@MyBatisDao
public interface DataserviceDeviceDataEverydayCountsDao extends CrudDao<DataserviceDeviceDataEverydayCounts> {

    @Select("SELECT counts_device_id, counts_date, counts_data_count  FROM dataservice_device_data_everyday_counts dddec")
    List<DataserviceDeviceDataEverydayCountsVO> getAll();

    @Delete("delete from dataservice_device_data_everyday_counts where counts_device_id = #{deviceId}")
    void deleteByDeviceId(String deviceId);
}