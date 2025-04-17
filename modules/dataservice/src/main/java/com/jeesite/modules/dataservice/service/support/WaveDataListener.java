package com.jeesite.modules.dataservice.service.support;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jeesite.modules.dataservice.entity.support.WaveData;

import java.util.ArrayList;
import java.util.List;

public class WaveDataListener extends AnalysisEventListener<WaveData> {

    private final List<WaveData> dataList = new ArrayList<>();

    @Override
    public void invoke(WaveData data, AnalysisContext context) {
        dataList.add(data); // 每行加入列表
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 所有数据解析完毕
        System.out.println("共读取数据：" + dataList.size() + " 条");
    }

    public List<WaveData> getDataList() {
        return dataList;
    }
}