package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * deviceStructureEntity
 * @author wangcm
 * @version 2025-01-06
 */
@Table(name="dataservice_device_structure", alias="a", label="设备数据结构信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="structure_device_id", attrName="structureDeviceId", label="设备编号", queryType=QueryType.LIKE),
		@Column(name="structure_data", attrName="structureData", label="设备数据结构", queryType=QueryType.LIKE),
		@Column(name="data_type", attrName="dataType", label="类型"),
	}, orderBy="a.id DESC"
)
public class DataserviceDeviceStructure extends DataEntity<DataserviceDeviceStructure> {
	
	private static final long serialVersionUID = 1L;
	private String structureDeviceId;		// 设备编号
	private String structureData;		// 设备数据结构
	private Integer dataType; //类型

	public DataserviceDeviceStructure() {
		this(null);
	}
	
	public DataserviceDeviceStructure(String id){
		super(id);
	}
	
	@NotBlank(message="设备编号不能为空")
	@Size(min=0, max=100, message="设备编号长度不能超过 100 个字符")
	public String getStructureDeviceId() {
		return structureDeviceId;
	}

	public void setStructureDeviceId(String structureDeviceId) {
		this.structureDeviceId = structureDeviceId;
	}
	
	@Size(min=0, max=1024, message="设备数据结构长度不能超过 1024 个字符")
	public String getStructureData() {
		return structureData;
	}

	public void setStructureData(String structureData) {
		this.structureData = structureData;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	
}