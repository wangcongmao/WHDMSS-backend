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
    //获取正常数据总数
    @Select("SELECT COUNT(*) FROM dataservice_device_data WHERE status = 0")
    Integer getNormalDataCount();
    //获取可疑数据总数
    @Select("SELECT COUNT(*) FROM dataservice_device_data WHERE status = 5")
    Integer getUncertainDataCount();
    //获取异常数据总数
    @Select("SELECT COUNT(*) FROM dataservice_device_data WHERE status = 6")
    Integer getErrorDataCount();

    //获取设备正常数据总数
    @Select("SELECT COUNT(*) FROM dataservice_device_data WHERE status = 0 AND data_device_id = #{dataDeviceId}")
    Integer getDeviceNormalDataCount(String dataDeviceId);
    //获取设备可疑数据总数
    @Select("SELECT COUNT(*) FROM dataservice_device_data WHERE status = 5 AND data_device_id = #{dataDeviceId}")
    Integer getDeviceUncertainDataCount(String dataDeviceId);
    //获取设备异常数据总数
    @Select("SELECT COUNT(*) FROM dataservice_device_data WHERE status = 6 AND data_device_id = #{dataDeviceId}")
    Integer getDeviceErrorDataCount(String dataDeviceId);

    @Select("SELECT COUNT(*) FROM dataservice_device_data WHERE data_device_id = #{dataDeviceId} AND update_date = #{formattedDate}")
    Integer getDeviceDateCounts(String dataDeviceId, String formattedDate);

    //获取设备具体数据某一项异常时可以先获取该设备所有数据，在service层计算各参数数量

    //饼图用这个图https://echarts.apache.org/examples/zh/editor.html?c=pie-simple


}