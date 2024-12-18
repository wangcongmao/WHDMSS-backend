package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * experimentDeviceEntity
 * @author wangcm
 * @version 2024-12-18
 */
@Table(name="dataservice_experiment_service", alias="a", label="experimentDevice信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="service_name", attrName="serviceName", label="装置名称", queryType=QueryType.LIKE),
		@Column(name="service_longitude", attrName="serviceLongitude", label="经度"),
		@Column(name="service_latitude", attrName="serviceLatitude", label="维度"),
		@Column(name="service_type", attrName="serviceType", label="装置类型"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class DataserviceExperimentService extends DataEntity<DataserviceExperimentService> {
	
	private static final long serialVersionUID = 1L;
	private String serviceName;		// 装置名称
	private String serviceLongitude;		// 经度
	private String serviceLatitude;		// 维度
	private String serviceType;		// 装置类型

	public DataserviceExperimentService() {
		this(null);
	}
	
	public DataserviceExperimentService(String id){
		super(id);
	}
	
	@NotBlank(message="装置名称不能为空")
	@Size(min=0, max=100, message="装置名称长度不能超过 100 个字符")
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@Size(min=0, max=100, message="经度长度不能超过 100 个字符")
	public String getServiceLongitude() {
		return serviceLongitude;
	}

	public void setServiceLongitude(String serviceLongitude) {
		this.serviceLongitude = serviceLongitude;
	}
	
	@Size(min=0, max=100, message="维度长度不能超过 100 个字符")
	public String getServiceLatitude() {
		return serviceLatitude;
	}

	public void setServiceLatitude(String serviceLatitude) {
		this.serviceLatitude = serviceLatitude;
	}
	
	@NotBlank(message="装置类型不能为空")
	@Size(min=0, max=100, message="装置类型长度不能超过 100 个字符")
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
}