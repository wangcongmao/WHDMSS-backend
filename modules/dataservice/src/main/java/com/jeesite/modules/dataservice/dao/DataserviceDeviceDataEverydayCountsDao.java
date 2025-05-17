package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataEverydayCounts;
import com.jeesite.modules.dataservice.entity.vo.DataserviceDeviceDataEverydayCountsVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 设备每天数据量DAO接口
 * 
 * @author wangcm
 * @version 2025-02-23
 */
@MyBatisDao
public interface DataserviceDeviceDataEverydayCountsDao extends CrudDao<DataserviceDeviceDataEverydayCounts> {

    @Select("SELECT counts_device_id, counts_date, counts_data_count  FROM dataservice_device_data_everyday_counts dddec")
    List<DataserviceDeviceDataEverydayCountsVO> getAll();

    @Insert("INSERT INTO dataservice_device_data_everyday_counts(counts_device_id, counts_date, counts_data_count) VALUES(#{countsDeviceId}, #{countsDate}, #{countsDataCount})")
    int insertCount(DataserviceDeviceDataEverydayCounts counts);

    @Delete("delete from dataservice_device_data_everyday_counts where counts_device_id = #{deviceId}")
    void deleteByDeviceId(String deviceId);

    /**
     * 查询各设备前一天数据量
     */
    @Select("SELECT \n" +
            "    data_device_id AS countsDeviceId,\n" +
            "    COUNT(*) AS countsDataCount,\n" +
            "    CURDATE() - INTERVAL 1 DAY AS countsDate\n" +
            "FROM \n" +
            "    dataservice_device_data\n" +
            "WHERE \n" +
            "    create_date >= CURDATE() - INTERVAL 1 DAY\n" +
            "    AND create_date < CURDATE()\n" +
            "GROUP BY \n" +
            "    data_device_id;")
    List<DataserviceDeviceDataEverydayCounts> getBeforeOneDayCounts();

    @Select("SELECT \n" +
            "    data_device_id AS countsDeviceId,\n" +
            "    COUNT(*) AS countsDataCount,\n" +
            "    DATE(create_date) AS countsDate\n" +  // 获取每条记录的日期部分
            "FROM \n" +
            "    dataservice_device_data\n" +
            "WHERE \n" +
            "    create_date < CURDATE()\n" +
            "GROUP BY \n" +
            "    data_device_id, DATE(create_date);")  // 按设备 ID 和日期分组
    List<DataserviceDeviceDataEverydayCounts> getAllDatesDeviceCounts();

    @Delete("DELETE FROM dataservice_device_data_everyday_counts;")
    void deleteAllDate();
}