package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 波浪预报存储表Entity
 * @author wangcm
 * @version 2025-03-09
 */
@Table(name="dataservice_wave_forecast", alias="a", label="波浪预报存储表信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(includeEntity=DataEntity.class),
		@Column(name="longitude", attrName="longitude", label="经度"),
		@Column(name="latitude", attrName="latitude", label="维度"),
		@Column(name="location_name", attrName="locationName", label="位置名称", queryType=QueryType.LIKE),
		@Column(name="area_code", attrName="areaCode", label="区域编码"),
		@Column(name="wave_height", attrName="waveHeight", label="浪高"),
		@Column(name="forecast_time", attrName="forecastTime", label="预报时间"),
		@Column(name="data_time", attrName="dataTime", label="数据时间"),
		@Column(name="data_source", attrName="dataSource", label="数据来源"),
	}, orderBy="a.update_date DESC"
)
public class DataserviceWaveForecast extends DataEntity<DataserviceWaveForecast> {
	
	private static final long serialVersionUID = 1L;
	private String longitude;		// 经度
	private String latitude;		// 维度
	private String locationName;		// 位置名称
	private String areaCode;		// 区域编码
	private String waveHeight;		// 浪高
	private String forecastTime;		// 预报时间
	private String dataTime;		// 数据时间
	private String dataSource;		// 数据来源

	public DataserviceWaveForecast() {
		this(null);
	}
	
	public DataserviceWaveForecast(String id){
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
	
	@NotBlank(message="浪高不能为空")
	@Size(min=0, max=100, message="浪高长度不能超过 100 个字符")
	public String getWaveHeight() {
		return waveHeight;
	}

	public void setWaveHeight(String waveHeight) {
		this.waveHeight = waveHeight;
	}
	
	@NotBlank(message="预报时间不能为空")
	@Size(min=0, max=100, message="预报时间长度不能超过 100 个字符")
	public String getForecastTime() {
		return forecastTime;
	}

	public void setForecastTime(String forecastTime) {
		this.forecastTime = forecastTime;
	}
	
	@Size(min=0, max=100, message="数据时间长度不能超过 100 个字符")
	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	
	@Size(min=0, max=100, message="数据来源长度不能超过 100 个字符")
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
}