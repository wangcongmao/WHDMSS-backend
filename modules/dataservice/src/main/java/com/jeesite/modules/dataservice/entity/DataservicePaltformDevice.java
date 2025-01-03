package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * platformDeviceEntity
 * @author wangcm
 * @version 2024-12-27
 */
@Table(name="dataservice_paltform_device", alias="a", label="平台与设备信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="parent_code", attrName="parent.id", label="父级编号", isParentCode=true),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="device_name", attrName="deviceName", label="名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="device_type", attrName="deviceType", label="类型"),
		@Column(name="device_longitude", attrName="deviceLongitude", label="经度"),
		@Column(name="device_dimension", attrName="deviceDimension", label="维度"),
		@Column(name="device_maketime", attrName="deviceMaketime", label="布放时间", isUpdateForce=true),
		@Column(name="device_repairtime", attrName="deviceRepairtime", label="下次检修时间", isUpdateForce=true),
		@Column(name="device_mapposition", attrName="deviceMapposition", label="地图位置"),
	}, orderBy="a.tree_sorts, a.id"
)
public class DataservicePaltformDevice extends TreeEntity<DataservicePaltformDevice> {
	
	private static final long serialVersionUID = 1L;
	private String deviceName;		// 名称
	private String deviceType;		// 类型
	private String deviceLongitude;		// 经度
	private String deviceDimension;		// 维度
	private Date deviceMaketime;		// 布放时间
	private Date deviceRepairtime;		// 下次检修时间
	private String deviceMapposition;		// 地图位置

	public DataservicePaltformDevice() {
		this(null);
	}
	
	public DataservicePaltformDevice(String id){
		super(id);
	}
	
	@Override
	public DataservicePaltformDevice getParent() {
		return parent;
	}

	@Override
	public void setParent(DataservicePaltformDevice parent) {
		this.parent = parent;
	}
	
	@NotBlank(message="名称不能为空")
	@Size(min=0, max=100, message="名称长度不能超过 100 个字符")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@NotBlank(message="类型不能为空")
	@Size(min=0, max=100, message="类型长度不能超过 100 个字符")
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	@Size(min=0, max=100, message="经度长度不能超过 100 个字符")
	public String getDeviceLongitude() {
		return deviceLongitude;
	}

	public void setDeviceLongitude(String deviceLongitude) {
		this.deviceLongitude = deviceLongitude;
	}
	
	@Size(min=0, max=100, message="维度长度不能超过 100 个字符")
	public String getDeviceDimension() {
		return deviceDimension;
	}

	public void setDeviceDimension(String deviceDimension) {
		this.deviceDimension = deviceDimension;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getDeviceMaketime() {
		return deviceMaketime;
	}

	public void setDeviceMaketime(Date deviceMaketime) {
		this.deviceMaketime = deviceMaketime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getDeviceRepairtime() {
		return deviceRepairtime;
	}

	public void setDeviceRepairtime(Date deviceRepairtime) {
		this.deviceRepairtime = deviceRepairtime;
	}
	
	@Size(min=0, max=100, message="地图位置长度不能超过 100 个字符")
	public String getDeviceMapposition() {
		return deviceMapposition;
	}

	public void setDeviceMapposition(String deviceMapposition) {
		this.deviceMapposition = deviceMapposition;
	}
	
}