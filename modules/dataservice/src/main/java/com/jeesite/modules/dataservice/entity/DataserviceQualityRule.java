package com.jeesite.modules.dataservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * qualityRuleEntity
 * @author wangcm
 * @version 2025-01-29
 */
@Table(name="dataservice_quality_rule", alias="a", label="qualityRule信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="quality_device_id", attrName="qualityDeviceId", label="设备编号"),
		@Column(name="quality_rule_name", attrName="qualityRuleName", label="质量规则名称", queryType=QueryType.LIKE),
		@Column(name="quality_rule", attrName="qualityRule", label="说明"),
		@Column(name="quality_isused", attrName="qualityIsused", label="状态"),
		@Column(name="quality_other", attrName="qualityOther", label="备注"),
	}, orderBy="a.id DESC"
)
public class DataserviceQualityRule extends DataEntity<DataserviceQualityRule> {
	
	private static final long serialVersionUID = 1L;
	private String qualityDeviceId;		// 设备编号
	private String qualityRuleName;		// 质量规则名称
	private String qualityRule;		// 说明
	private String qualityIsused;		// 状态
	private String qualityOther;		// 备注

	public DataserviceQualityRule() {
		this(null);
	}
	
	public DataserviceQualityRule(String id){
		super(id);
	}
	
	@NotBlank(message="设备编号不能为空")
	@Size(min=0, max=100, message="设备编号长度不能超过 100 个字符")
	public String getQualityDeviceId() {
		return qualityDeviceId;
	}

	public void setQualityDeviceId(String qualityDeviceId) {
		this.qualityDeviceId = qualityDeviceId;
	}
	
	@Size(min=0, max=100, message="质量规则名称长度不能超过 100 个字符")
	public String getQualityRuleName() {
		return qualityRuleName;
	}

	public void setQualityRuleName(String qualityRuleName) {
		this.qualityRuleName = qualityRuleName;
	}
	
	@Size(min=0, max=100, message="说明长度不能超过 100 个字符")
	public String getQualityRule() {
		return qualityRule;
	}

	public void setQualityRule(String qualityRule) {
		this.qualityRule = qualityRule;
	}
	
	@NotBlank(message="状态不能为空")
	@Size(min=0, max=100, message="状态长度不能超过 100 个字符")
	public String getQualityIsused() {
		return qualityIsused;
	}

	public void setQualityIsused(String qualityIsused) {
		this.qualityIsused = qualityIsused;
	}
	
	@Size(min=0, max=100, message="备注长度不能超过 100 个字符")
	public String getQualityOther() {
		return qualityOther;
	}

	public void setQualityOther(String qualityOther) {
		this.qualityOther = qualityOther;
	}
	
}