package com.jeesite.modules.dataservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * dataservice_device_data_conditionEntity
 * @author wangcm
 * @version 2025-02-21
 */
@Table(name="dataservice_device_data_condition", alias="a", label="dataservice_device_data_condition信息", columns={
		@Column(name="id", attrName="tid", label="id", isPK=true),
		@Column(name="structure_device_id", attrName="structureDeviceId", label="structure_device_id"),
		@Column(name="device_param_name", attrName="deviceParamName", label="device_param_name", queryType=QueryType.LIKE),
		@Column(name="condition_type", attrName="conditionType", label="condition_type"),
		@Column(name="nums", attrName="nums", label="nums"),
		@Column(name="remarks", attrName="remarks", label="remarks", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class DataserviceDeviceDataCondition extends DataEntity<DataserviceDeviceDataCondition> {
	
	private static final long serialVersionUID = 1L;
	private Integer tid;		// id
	private String structureDeviceId;		// structure_device_id
	private String deviceParamName;		// device_param_name
	private Integer conditionType;		// condition_type
	private Integer nums;		// nums

	public DataserviceDeviceDataCondition() {
		this(null);
	}
	
	public DataserviceDeviceDataCondition(String id){
		super(id);
	}
	
	@JsonSerialize(using = ToStringSerializer.class)
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	@NotBlank(message="structure_device_id不能为空")
	@Size(min=0, max=100, message="structure_device_id长度不能超过 100 个字符")
	public String getStructureDeviceId() {
		return structureDeviceId;
	}

	public void setStructureDeviceId(String structureDeviceId) {
		this.structureDeviceId = structureDeviceId;
	}
	
	@NotBlank(message="device_param_name不能为空")
	@Size(min=0, max=512, message="device_param_name长度不能超过 512 个字符")
	public String getDeviceParamName() {
		return deviceParamName;
	}

	public void setDeviceParamName(String deviceParamName) {
		this.deviceParamName = deviceParamName;
	}
	
	@NotNull(message="condition_type不能为空")
	public Integer getConditionType() {
		return conditionType;
	}

	public void setConditionType(Integer conditionType) {
		this.conditionType = conditionType;
	}
	
	@NotNull(message="nums不能为空")
	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}
	
}