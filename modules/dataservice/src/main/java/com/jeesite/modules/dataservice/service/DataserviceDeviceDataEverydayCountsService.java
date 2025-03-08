package com.jeesite.modules.dataservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.jeesite.modules.dataservice.entity.DataserviceDeviceStructure;
import com.jeesite.modules.dataservice.entity.vo.DataserviceDeviceDataEverydayCountsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataEverydayCounts;
import com.jeesite.modules.dataservice.dao.DataserviceDeviceDataEverydayCountsDao;

import javax.annotation.Resource;

/**
 * 设备每天数据量Service
 * @author wangcm
 * @version 2025-02-23
 */
@Service
public class DataserviceDeviceDataEverydayCountsService extends CrudService<DataserviceDeviceDataEverydayCountsDao, DataserviceDeviceDataEverydayCounts> {

	@Resource
	private DataserviceDeviceStructureService dataserviceDeviceStructureService;

	@Resource
	private DataserviceDeviceDataService dataserviceDeviceDataService;

	@Resource
	private DataserviceDeviceDataEverydayCountsDao dataserviceDeviceDataEverydayCountsDao;

	/**
	 * 获取单条数据
	 * @param dataserviceDeviceDataEverydayCounts
	 * @return
	 */
	@Override
	public DataserviceDeviceDataEverydayCounts get(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts) {
		return super.get(dataserviceDeviceDataEverydayCounts);
	}

	/**
	 * 查询分页数据
	 * @param dataserviceDeviceDataEverydayCounts 查询条件
	 * @param dataserviceDeviceDataEverydayCounts page 分页对象
	 * @return
	 */
	@Override
	public Page<DataserviceDeviceDataEverydayCounts> findPage(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts) {
		return super.findPage(dataserviceDeviceDataEverydayCounts);
	}

	/**
	 * 查询列表数据
	 * @param dataserviceDeviceDataEverydayCounts
	 * @return
	 */
	@Override
	public List<DataserviceDeviceDataEverydayCounts> findList(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts) {
		return super.findList(dataserviceDeviceDataEverydayCounts);
	}

	/**
	 * 保存数据（插入或更新）
	 * @param dataserviceDeviceDataEverydayCounts
	 */
	@Override
	@Transactional
	public void save(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts) {
		super.save(dataserviceDeviceDataEverydayCounts);
	}

	/**
	 * 更新状态
	 * @param dataserviceDeviceDataEverydayCounts
	 */
	@Override
	@Transactional
	public void updateStatus(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts) {
		super.updateStatus(dataserviceDeviceDataEverydayCounts);
	}

	/**
	 * 删除数据
	 * @param dataserviceDeviceDataEverydayCounts
	 */
	@Override
	@Transactional
	public void delete(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts) {
		super.delete(dataserviceDeviceDataEverydayCounts);
	}

	/**
	 * 每天自动更新数据
	 * 1.获取所有的设备名id
	 * 2.根据id查询当天该设备增加的数据条数
	 */
	@Transactional
	public void autoUpdate() {
		List<DataserviceDeviceStructure> list = dataserviceDeviceStructureService.list();

		List<String> structureDeviceIdList = new ArrayList<>();
		for (DataserviceDeviceStructure dataserviceDeviceStructure : list) {
			String structureDeviceId = dataserviceDeviceStructure.getStructureDeviceId();
			structureDeviceIdList.add(structureDeviceId);
		}

		LocalDate yesterday = LocalDate.now().minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = yesterday.format(formatter);

		// 将 LocalDate 转换为 LocalDateTime（时分秒设置为 00:00:00）
		LocalDateTime localDateTime = yesterday.atStartOfDay();
		// 将 LocalDateTime 转换为 Date（需要时区信息）
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		for (String deviceId : structureDeviceIdList) {
			int deviceDateCounts = dataserviceDeviceDataService.getDeviceDateCounts(deviceId, formattedDate);
			DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts = new DataserviceDeviceDataEverydayCounts();
			dataserviceDeviceDataEverydayCounts.setCountsDeviceId(deviceId);
			dataserviceDeviceDataEverydayCounts.setCountsDate(date);
			dataserviceDeviceDataEverydayCounts.setCountsDataCount(deviceDateCounts);
			this.save(dataserviceDeviceDataEverydayCounts);
		}


	}

	/**
	 * 返回设备每日数据量列表
	 */
	@Transactional
	public List<DataserviceDeviceDataEverydayCountsVO> getAll() {
		 return dataserviceDeviceDataEverydayCountsDao.getAll();
	}

    public void deleteByDeviceId(String deviceId) {
		dataserviceDeviceDataEverydayCountsDao.deleteByDeviceId(deviceId);
    }
}