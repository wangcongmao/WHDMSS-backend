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
 * 设备每天数据量Entity
 * @author wangcm
 * @version 2025-02-23
 */
@Table(name="dataservice_device_data_everyday_counts", alias="a", label="设备每天数据量信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="counts_device_id", attrName="countsDeviceId", label="设备id"),
		@Column(name="counts_date", attrName="countsDate", label="日期"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="counts_data_count", attrName="countsDataCount", label="数据量"),
	}, orderBy="a.update_date DESC"
)
public class DataserviceDeviceDataEverydayCounts extends DataEntity<DataserviceDeviceDataEverydayCounts> {
	
	private static final long serialVersionUID = 1L;
	private String countsDeviceId;		// 设备id
	private Date countsDate;		// 日期
	private Integer countsDataCount;		// 数据量

	public DataserviceDeviceDataEverydayCounts() {
		this(null);
	}
	
	public DataserviceDeviceDataEverydayCounts(String id){
		super(id);
	}
	
	@NotBlank(message="设备id不能为空")
	@Size(min=0, max=512, message="设备id长度不能超过 512 个字符")
	public String getCountsDeviceId() {
		return countsDeviceId;
	}

	public void setCountsDeviceId(String countsDeviceId) {
		this.countsDeviceId = countsDeviceId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@NotNull(message="日期不能为空")
	public Date getCountsDate() {
		return countsDate;
	}

	public void setCountsDate(Date countsDate) {
		this.countsDate = countsDate;
	}
	
	@NotNull(message="数据量不能为空")
	public Integer getCountsDataCount() {
		return countsDataCount;
	}

	public void setCountsDataCount(Integer countsDataCount) {
		this.countsDataCount = countsDataCount;
	}
	
}