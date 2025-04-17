package com.jeesite.modules.dataservice.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceData;
import com.jeesite.modules.dataservice.dao.DataserviceDeviceDataDao;

import javax.annotation.Resource;

/**
 * deviceDataService
 * @author wangcm
 * @version 2025-01-06
 */
@Service
public class DataserviceDeviceDataService extends CrudService<DataserviceDeviceDataDao, DataserviceDeviceData> {
	@Resource
	private DataserviceDeviceDataDao dataserviceDeviceDataDao;
	/**
	 * 获取单条数据
	 * @param dataserviceDeviceData
	 * @return
	 */
	@Override
	public DataserviceDeviceData get(DataserviceDeviceData dataserviceDeviceData) {
		return super.get(dataserviceDeviceData);
	}
	
	/**
	 * 查询分页数据
	 * @param dataserviceDeviceData 查询条件
	 * @param dataserviceDeviceData page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceDeviceData> findPage(DataserviceDeviceData dataserviceDeviceData) {
		return super.findPage(dataserviceDeviceData);
	}
	
	/**
	 * 查询列表数据
	 * @param dataserviceDeviceData
	 * @return
	 */
	@Override
	public List<DataserviceDeviceData> findList(DataserviceDeviceData dataserviceDeviceData) {
		return super.findList(dataserviceDeviceData);
	}

	/**
	 * 分页列表数据
	 * @param
	 * @return
	 */
	public List<DataserviceDeviceData> pageData(String dataDeviceId, Integer page, Integer pageSize) {
		Integer offset = calculateOffset(page, pageSize);
		return dataserviceDeviceDataDao.getPageData(dataDeviceId, offset, pageSize);
	}

	/**
	 * 分页列表数据
	 * @param
	 * @return
	 */
	public List<DataserviceDeviceData> pageData(Integer offSite, Integer pageSize) {
		return dataserviceDeviceDataDao.getPageAllData(offSite, pageSize);
	}

	// 计算分页的 OFFSET
	Integer calculateOffset(Integer page, Integer pageSize) {
		return (page - 1) * pageSize;
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceDeviceData
	 */
	@Override
	@Transactional
	public void save(DataserviceDeviceData dataserviceDeviceData) {
		super.save(dataserviceDeviceData);
	}
	
	/**
	 * 更新状态
	 * @param dataserviceDeviceData
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceDeviceData dataserviceDeviceData) {
		super.updateStatus(dataserviceDeviceData);
	}
	
	/**
	 * 删除数据
	 * @param dataserviceDeviceData
	 */
	@Override
	@Transactional
	public void delete(DataserviceDeviceData dataserviceDeviceData) {
		super.delete(dataserviceDeviceData);
	}

	public void deleteByDeviceId(String deviceId) {
		dataserviceDeviceDataDao.deleteByDeviceId(deviceId);
	}

	// 根据设备编号获取多个设备数据
	public List<DataserviceDeviceData> getDeviceDataByDeviceId(String dataDeviceId) {
		return dataserviceDeviceDataDao.getByDeviceId(dataDeviceId);
	}

	/**
	 * 获取正常数据总数
	 * @return
	 */
	public Integer getNormalDataCount() {
		return dataserviceDeviceDataDao.getNormalDataCount();
	}

	/**
	 * 获取可疑数据总数
	 * @return
	 */
	public Integer getUncertainDataCount() {
		return dataserviceDeviceDataDao.getUncertainDataCount();
	}

	/**
	 * 获取异常数据总数
	 * @return
	 */
	public Integer getErrorDataCount() {
		return dataserviceDeviceDataDao.getErrorDataCount();
	}

	/**
	 * 获取单个设备正常数据总数
	 * @return
	 */
	public Integer getDeviceNormalDataCount(String dataDeviceId) {
		return dataserviceDeviceDataDao.getDeviceNormalDataCount(dataDeviceId);
	}

	/**
	 * 获取单个设备可疑数据总数
	 * @return
	 */
	public Integer getDeviceUncertainDataCount(String dataDeviceId) {
		return dataserviceDeviceDataDao.getDeviceUncertainDataCount(dataDeviceId);
	}

	/**
	 * 获取单个设备异常数据总数
	 * @return
	 */
	public Integer getDeviceErrorDataCount(String dataDeviceId) {
		return dataserviceDeviceDataDao.getDeviceErrorDataCount(dataDeviceId);
	}

	/**
	 * 获取某个设备当天数据条数
	 * @param deviceId
	 * @param formattedDate
	 * @return
	 */
    public int getDeviceDateCounts(String deviceId, String formattedDate) {
		return dataserviceDeviceDataDao.getDeviceDateCounts(deviceId, formattedDate);
    }

	/**
	 * 获取数据总数
	 * @return
	 */
	public Integer getDataCount(String deviceId) {
		return dataserviceDeviceDataDao.getDataCount(deviceId);
	}

	public List<DataserviceDeviceData> getAllByDeviceId(String qualityDeviceId) {
		return dataserviceDeviceDataDao.getAllByDeviceId(qualityDeviceId);
	}

	/**
	 * 根据经纬度获取水深
	 * @param dataDeviceId 设备id
	 * @param longitude 经度
	 * @param latitude 维度
	 * @return
	 */
	public Integer getDepth(String dataDeviceId, String longitude, String latitude) {
		DataserviceDeviceData dataserviceDeviceData = dataserviceDeviceDataDao.getDepth(dataDeviceId, longitude, latitude);
		if (dataserviceDeviceData == null) {
			return null;
		}
		String dataDeviceData = dataserviceDeviceData.getDataDeviceData();
		// {流速: '1.2', 流向: '2.8'}
		String substring = dataDeviceData.substring(dataDeviceData.indexOf("depth: '") + 8, dataDeviceData.length() - 2);
		int i = Integer.parseInt(substring);

		return i;
	}
}