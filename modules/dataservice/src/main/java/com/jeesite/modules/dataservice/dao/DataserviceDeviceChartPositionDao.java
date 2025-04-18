package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceChartPosition;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 设备拓扑关系图位置DAO接口
 * @author wangcm
 * @version 2025-04-18
 */
@MyBatisDao
public interface DataserviceDeviceChartPositionDao extends CrudDao<DataserviceDeviceChartPosition> {

    @Select("select * from dataservice_device_chart_position")
    List<DataserviceDeviceChartPosition> getAll();


    @Update("update dataservice_device_chart_position set position_x = #{positionX}, position_y = #{positionY} where device_name = #{deviceName}")
    void updatePosition(@Param("positionX") String positionX,
                        @Param("positionY") String positionY,
                        @Param("deviceName") String deviceName);

    @Update("update dataservice_device_chart_position set device_name = #{deviceName} where device_name = #{beforeName}")
    void updateName(@Param("deviceName") String deviceName, @Param("beforeName") String beforeName);

    @Insert("INSERT INTO dataservice_device_chart_position " +
            "(device_name) " +
            "VALUES (#{deviceName})")
    void insertPosition(String deviceName);

    @Delete("delete from dataservice_device_chart_position where device_name = #{deviceName}")
    void  deleteByDeviceName(@Param("deviceName") String deviceName);
}