package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 潮流预报存储表Entity
 * @author wangcm
 * @version 2025-03-09
 */
@Table(name="dataservice_tidal_forecast", alias="a", label="潮流预报存储表信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(includeEntity=DataEntity.class),
		@Column(name="longitude", attrName="longitude", label="经度"),
		@Column(name="latitude", attrName="latitude", label="维度"),
		@Column(name="location_name", attrName="locationName", label="位置名称", queryType=QueryType.LIKE),
		@Column(name="area_code", attrName="areaCode", label="区域编码"),
		@Column(name="vx", attrName="vx", label="潮流x分量"),
		@Column(name="vy", attrName="vy", label="潮流y分量"),
		@Column(name="magnitude", attrName="magnitude", label="流速大小"),
		@Column(name="direction", attrName="direction", label="流向角度"),
		@Column(name="forecast_time", attrName="forecastTime", label="预报时间"),
		@Column(name="data_time", attrName="dataTime", label="数据时间", isUpdateForce=true),
		@Column(name="time_slot", attrName="timeSlot", label="时间槽"),
		@Column(name="data_source", attrName="dataSource", label="数据来源"),
	}, orderBy="a.update_date DESC"
)
public class DataserviceTidalForecast extends DataEntity<DataserviceTidalForecast> {
	
	private static final long serialVersionUID = 1L;
	private String longitude;		// 经度
	private String latitude;		// 维度
	private String locationName;		// 位置名称
	private String areaCode;		// 区域编码
	private String vx;		// 潮流x分量
	private String vy;		// 潮流y分量
	private String magnitude;		// 流速大小
	private String direction;		// 流向角度
	private Date forecastTime;		// 预报时间
	private Date dataTime;		// 数据时间
	private String timeSlot;		// 时间槽
	private String dataSource;		// 数据来源

	public DataserviceTidalForecast() {
		this(null);
	}
	
	public DataserviceTidalForecast(String id){
		super(id);
	}
	
	@NotBlank(message="经度不能为空")
	@Size(min=0, max=100, message="经度长度不能超过 100 个字符")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@NotBlank(message="维度不能为空")
	@Size(min=0, max=100, message="维度长度不能超过 100 个字符")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Size(min=0, max=100, message="位置名称长度不能超过 100 个字符")
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	@Size(min=0, max=100, message="区域编码长度不能超过 100 个字符")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@NotBlank(message="潮流x分量不能为空")
	@Size(min=0, max=100, message="潮流x分量长度不能超过 100 个字符")
	public String getVx() {
		return vx;
	}

	public void setVx(String vx) {
		this.vx = vx;
	}
	
	@NotBlank(message="潮流y分量不能为空")
	@Size(min=0, max=100, message="潮流y分量长度不能超过 100 个字符")
	public String getVy() {
		return vy;
	}

	public void setVy(String vy) {
		this.vy = vy;
	}
	
	@NotBlank(message="流速大小不能为空")
	@Size(min=0, max=100, message="流速大小长度不能超过 100 个字符")
	public String getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(String magnitude) {
		this.magnitude = magnitude;
	}
	
	@Size(min=0, max=100, message="流向角度长度不能超过 100 个字符")
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@NotNull(message="预报时间不能为空")
	public Date getForecastTime() {
		return forecastTime;
	}

	public void setForecastTime(Date forecastTime) {
		this.forecastTime = forecastTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}
	
	@Size(min=0, max=100, message="时间槽长度不能超过 100 个字符")
	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	@Size(min=0, max=100, message="数据来源长度不能超过 100 个字符")
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
}