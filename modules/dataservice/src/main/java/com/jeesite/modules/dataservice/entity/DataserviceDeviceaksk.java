package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 设备AkSkEntity
 * @author wangcm
 * @version 2025-03-24
 */
@Table(name="dataservice_deviceaksk", alias="a", label="设备AkSk信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(includeEntity=DataEntity.class),
		@Column(name="structure_device_id", attrName="structureDeviceId", label="设备ID"),
		@Column(name="accesskey", attrName="accesskey", label="accessKey"),
		@Column(name="secretkey", attrName="secretkey", label="secretKey"),
	}, orderBy="a.update_date DESC"
)
public class DataserviceDeviceaksk extends DataEntity<DataserviceDeviceaksk> {
	
	private static final long serialVersionUID = 1L;
	private String structureDeviceId;		// 设备ID
	private String accesskey;		// accessKey
	private String secretkey;		// secretKey

	public DataserviceDeviceaksk() {
		this(null);
	}
	
	public DataserviceDeviceaksk(String id){
		super(id);
	}
	
	@NotBlank(message="设备ID不能为空")
	@Size(min=0, max=100, message="设备ID长度不能超过 100 个字符")
	public String getStructureDeviceId() {
		return structureDeviceId;
	}

	public void setStructureDeviceId(String structureDeviceId) {
		this.structureDeviceId = structureDeviceId;
	}
	
	@Size(min=0, max=256, message="accessKey长度不能超过 256 个字符")
	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	
	@Size(min=0, max=256, message="secretKey长度不能超过 256 个字符")
	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	
}