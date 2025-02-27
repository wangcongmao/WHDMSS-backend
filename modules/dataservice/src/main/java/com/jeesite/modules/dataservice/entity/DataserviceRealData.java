package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 实测数据Entity
 * @author wangcm
 * @version 2025-02-25
 */
@Table(name="dataservice_real_data", alias="a", label="实测数据信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="data_sbid", attrName="dataSbid", label="设备id"),
	}, orderBy="a.id DESC"
)
public class DataserviceRealData extends DataEntity<DataserviceRealData> {
	
	private static final long serialVersionUID = 1L;
	private String dataSbid;		// 设备id

	public DataserviceRealData() {
		this(null);
	}
	
	public DataserviceRealData(String id){
		super(id);
	}
	
	@Size(min=0, max=100, message="设备id长度不能超过 100 个字符")
	public String getDataSbid() {
		return dataSbid;
	}

	public void setDataSbid(String dataSbid) {
		this.dataSbid = dataSbid;
	}
	
}