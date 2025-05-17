package com.jeesite.modules.dataservice.config;

import com.jeesite.modules.dataservice.dao.DataserviceDeviceDataEverydayCountsDao;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataEverydayCounts;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTasks {

    @Resource
    DataserviceDeviceDataEverydayCountsDao dataserviceDeviceDataEverydayCountsDao;

    /**
     * 测试用
     */
//    @Scheduled(fixedRate = 105000) // 每隔 5 秒执行一次
//    public void task1() {
//        System.out.println("每105秒执行一次任务: " + LocalDateTime.now());
//        List<DataserviceDeviceDataEverydayCounts> beforeOneDayCounts = dataserviceDeviceDataEverydayCountsDao.getBeforeOneDayCounts();
//
//        for (DataserviceDeviceDataEverydayCounts beforeOneDayCount : beforeOneDayCounts) {
//            dataserviceDeviceDataEverydayCountsDao.insertCount(beforeOneDayCount);
//        }
//    }

    @Scheduled(cron = "0 5 0 * * ?") // 每天 00:05 执行一次
    public void updateCountsDateCountTable() {
        List<DataserviceDeviceDataEverydayCounts> beforeOneDayCounts = dataserviceDeviceDataEverydayCountsDao.getBeforeOneDayCounts();

        for (DataserviceDeviceDataEverydayCounts beforeOneDayCount : beforeOneDayCounts) {
            dataserviceDeviceDataEverydayCountsDao.insertCount(beforeOneDayCount);
        }

        System.out.println("每天00:05执行任务: " + LocalDateTime.now());
    }

    /**
     * 本方法方法，重新更新表 DataEverydayCounts 数据
     * 取消注释后每次启动执行一次，会删除 dataservice_device_data_everyday_counts 表中的数据，然后重新统计每天各设备的数据条数
     * 由于系统中每天 00:05 执行一次统计任务，只统计前一天各设备数据条数，需要严格满足条件才会执行，并且只会统计前一天；若因为某些原因导致数据统计不完全或出错，
     * 取消下面注释运行一遍程序就会重新统计所有数据（不包括今天的数据）
     */

    // 定时任务，启动时执行一次
//    @PostConstruct
//    public void updateCountsDateCountTableAll() {
//        // 1.先清空表中所有数据
//        dataserviceDeviceDataEverydayCountsDao.deleteAllDate();
//        // 2.获取所有日期各个设备数据条数
//        List<DataserviceDeviceDataEverydayCounts> beforeOneDayCounts = dataserviceDeviceDataEverydayCountsDao.getAllDatesDeviceCounts();
//
//        for (DataserviceDeviceDataEverydayCounts beforeOneDayCount : beforeOneDayCounts) {
//            dataserviceDeviceDataEverydayCountsDao.insertCount(beforeOneDayCount);
//        }
//
//        System.out.println("每天00:05执行任务: " + LocalDateTime.now());
//    }
}