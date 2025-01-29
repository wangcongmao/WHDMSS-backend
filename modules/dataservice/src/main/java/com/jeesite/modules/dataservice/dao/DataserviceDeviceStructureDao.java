package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceData;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceStructure;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * deviceStructureDAO接口
 * @author wangcm
 * @version 2025-01-06
 */
@MyBatisDao
public interface DataserviceDeviceStructureDao extends CrudDao<DataserviceDeviceStructure> {
    @Select("SELECT * FROM dataservice_device_structure WHERE structure_device_id = #{dataDeviceId} LIMIT 1")
    DataserviceDeviceStructure getStructureByDeviceId(String dataDeviceId);

    @Select("SELECT * FROM dataservice_device_structure")
    List<DataserviceDeviceStructure> list();
}