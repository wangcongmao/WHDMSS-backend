package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * deviceDataEntity
 * @author wangcm
 * @version 2025-01-06
 */
@Table(name="dataservice_device_data", alias="a", label="deviceData信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="data_device_id", attrName="dataDeviceId", label="设备编号", queryType=QueryType.LIKE),
		@Column(name="data_device_data", attrName="dataDeviceData", label="设备数据", queryType=QueryType.LIKE),
		@Column(name="data_test_date", attrName="dataTestDate", label="测试时间", isUpdateForce=true),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class DataserviceDeviceData extends DataEntity<DataserviceDeviceData> {
	
	private static final long serialVersionUID = 1L;
	private String dataDeviceId;		// 设备编号
	private String dataDeviceData;		// 设备数据
	private Date dataTestDate;		// 测试时间

	public DataserviceDeviceData() {
		this(null);
	}
	
	public DataserviceDeviceData(String id){
		super(id);
	}
	
	@NotBlank(message="设备编号不能为空")
	@Size(min=0, max=100, message="设备编号长度不能超过 100 个字符")
	public String getDataDeviceId() {
		return dataDeviceId;
	}

	public void setDataDeviceId(String dataDeviceId) {
		this.dataDeviceId = dataDeviceId;
	}
	
	@Size(min=0, max=1024, message="设备数据长度不能超过 1024 个字符")
	public String getDataDeviceData() {
		return dataDeviceData;
	}

	public void setDataDeviceData(String dataDeviceData) {
		this.dataDeviceData = dataDeviceData;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getDataTestDate() {
		return dataTestDate;
	}

	public void setDataTestDate(Date dataTestDate) {
		this.dataTestDate = dataTestDate;
	}
	
	public Date getDataTestDate_gte() {
		return sqlMap.getWhere().getValue("data_test_date", QueryType.GTE);
	}

	public void setDataTestDate_gte(Date dataTestDate) {
		sqlMap.getWhere().and("data_test_date", QueryType.GTE, dataTestDate);
	}
	
	public Date getDataTestDate_lte() {
		return sqlMap.getWhere().getValue("data_test_date", QueryType.LTE);
	}

	public void setDataTestDate_lte(Date dataTestDate) {
		sqlMap.getWhere().and("data_test_date", QueryType.LTE, dataTestDate);
	}
	
}