package com.jeesite.modules.dataservice.dao;

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceStructure;
import com.jeesite.modules.dataservice.entity.DataservicePaltformDevice;
import com.jeesite.modules.dataservice.entity.DataserviceQualityRule;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * platformDeviceDAO接口
 * @author wangcm
 * @version 2024-12-27
 */
@MyBatisDao
public interface DataservicePaltformDeviceDao extends TreeDao<DataservicePaltformDevice> {
    @Select("SELECT * FROM dataservice_paltform_device")
    List<DataservicePaltformDevice> getAll();

}