package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceaksk;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
 * 设备AkSkDAO接口
 * @author wangcm
 * @version 2025-03-24
 */
@MyBatisDao
public interface DataserviceDeviceakskDao extends CrudDao<DataserviceDeviceaksk> {

    @Select("select * from dataservice_deviceaksk where structure_device_id=#{deviceId}")
    DataserviceDeviceaksk getById(String deviceId);

    @Delete("delete from dataservice_deviceaksk where structure_device_id=#{deviceId}")
    void deleteByDeviceId(String deviceId);

    @Select("select secretKey from dataservice_deviceaksk where accessKey=#{accessKey}")
    String getSkByAk(String accessKey);
}