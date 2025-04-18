package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 设备拓扑关系图位置Entity
 * @author wangcm
 * @version 2025-04-18
 */
@Table(name="dataservice_device_chart_position", alias="a", label="设备拓扑关系图位置信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="device_name", attrName="deviceName", label="名称", queryType=QueryType.LIKE),
		@Column(name="position_x", attrName="positionX", label="x"),
		@Column(name="position_y", attrName="positionY", label="y"),
		@Column(name="remarks", attrName="remarks", label="备注", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class DataserviceDeviceChartPosition extends DataEntity<DataserviceDeviceChartPosition> {
	
	private static final long serialVersionUID = 1L;
	private String deviceName;		// 名称
	private String positionX;		// x
	private String positionY;		// y

	public DataserviceDeviceChartPosition() {
		this(null);
	}
	
	public DataserviceDeviceChartPosition(String id){
		super(id);
	}
	
	@Size(min=0, max=100, message="名称长度不能超过 100 个字符")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Size(min=0, max=100, message="x长度不能超过 100 个字符")
	public String getPositionX() {
		return positionX;
	}

	public void setPositionX(String positionX) {
		this.positionX = positionX;
	}
	
	@Size(min=0, max=100, message="y长度不能超过 100 个字符")
	public String getPositionY() {
		return positionY;
	}

	public void setPositionY(String positionY) {
		this.positionY = positionY;
	}
	
}