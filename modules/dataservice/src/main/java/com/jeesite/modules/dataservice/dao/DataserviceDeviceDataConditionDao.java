package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceData;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataCondition;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * dataservice_device_data_conditionDAO接口
 * @author wangcm
 * @version 2025-02-21
 */
@MyBatisDao
public interface DataserviceDeviceDataConditionDao extends CrudDao<DataserviceDeviceDataCondition> {

    // 根据设备编号设备各参数数据状况
    @Select("SELECT * FROM dataservice_device_data_condition WHERE structure_device_id = #{dataDeviceId}")
    List<DataserviceDeviceDataCondition> getParamConditionByDeviceId(String dataDeviceId);

    @Insert("INSERT INTO dataservice_device_data_condition (structure_device_id, device_param_name, nums, condition_type) " +
            "VALUES (#{structureDeviceId}, #{deviceParamName}, #{nums}, #{conditionType})")
    void insertDeviceDataCondition(DataserviceDeviceDataCondition dataserviceDeviceDataCondition);

    // 删除特定structureDeviceId的所有记录
    @Delete("DELETE FROM dataservice_device_data_condition WHERE structure_device_id = #{structureDeviceId}")
    void deleteByStructureDeviceId(String structureDeviceId);

    // 增加特定记录的nums值（根据structureDeviceId, deviceParamName 和 conditionType）
    @Update("UPDATE dataservice_device_data_condition SET nums = nums + 1 " +
            "WHERE structure_device_id = #{structureDeviceId} " +
            "AND device_param_name = #{deviceParamName} " +
            "AND condition_type = #{conditionType}")
    void incrementNums(@Param("structureDeviceId") String structureDeviceId,
                       @Param("deviceParamName") String deviceParamName,
                       @Param("conditionType") int conditionType);

    // 减少特定记录的nums值（根据structureDeviceId, deviceParamName 和 conditionType）
    @Update("UPDATE dataservice_device_data_condition SET nums = nums - 1 " +
            "WHERE structure_device_id = #{structureDeviceId} " +
            "AND device_param_name = #{deviceParamName} " +
            "AND condition_type = #{conditionType}")
    void decrementNums(@Param("structureDeviceId") String structureDeviceId,
                       @Param("deviceParamName") String deviceParamName,
                       @Param("conditionType") int conditionType);

}