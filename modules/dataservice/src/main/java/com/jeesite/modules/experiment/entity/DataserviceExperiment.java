package com.jeesite.modules.experiment.entity;

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
 * 试验信息表Entity
 * @author wangcm
 * @version 2024-12-16
 */
@Table(name="dataservice_experiment", alias="a", label="试验信息表信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="experiment_name", attrName="experimentName", label="试验名称", queryType=QueryType.LIKE),
		@Column(name="experiment_testinggroundid", attrName="experimentTestinggroundid", label="试验场ID", isUpdateForce=true),
		@Column(name="experiment_testinggroundname", attrName="experimentTestinggroundname", label="试验场名称"),
		@Column(name="experiment_workplace", attrName="experimentWorkplace", label="试验单位"),
		@Column(name="experiment_staff", attrName="experimentStaff", label="试验人员"),
		@Column(name="experiment_testingtime", attrName="experimentTestingtime", label="测试时间"),
		@Column(name="experiment_userid", attrName="experimentUserid", label="创建用户id"),
	}, orderBy="a.id DESC"
)
public class DataserviceExperiment extends DataEntity<DataserviceExperiment> {
	
	private static final long serialVersionUID = 1L;
	private String experimentName;		// 试验名称
	private Long experimentTestinggroundid;		// 试验场ID
	private String experimentTestinggroundname;		// 试验场名称
	private String experimentWorkplace;		// 试验单位
	private String experimentStaff;		// 试验人员
	private Date experimentTestingtime;		// 测试时间
	private String experimentUserid;		// 创建用户id

	public DataserviceExperiment() {
		this(null);
	}
	
	public DataserviceExperiment(String id){
		super(id);
	}
	
	@NotBlank(message="试验名称不能为空")
	@Size(min=0, max=512, message="试验名称长度不能超过 512 个字符")
	public String getExperimentName() {
		return experimentName;
	}

	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
	}
	
	public Long getExperimentTestinggroundid() {
		return experimentTestinggroundid;
	}

	public void setExperimentTestinggroundid(Long experimentTestinggroundid) {
		this.experimentTestinggroundid = experimentTestinggroundid;
	}
	
	@NotBlank(message="试验场名称不能为空")
	@Size(min=0, max=512, message="试验场名称长度不能超过 512 个字符")
	public String getExperimentTestinggroundname() {
		return experimentTestinggroundname;
	}

	public void setExperimentTestinggroundname(String experimentTestinggroundname) {
		this.experimentTestinggroundname = experimentTestinggroundname;
	}
	
	@Size(min=0, max=512, message="试验单位长度不能超过 512 个字符")
	public String getExperimentWorkplace() {
		return experimentWorkplace;
	}

	public void setExperimentWorkplace(String experimentWorkplace) {
		this.experimentWorkplace = experimentWorkplace;
	}
	
	@Size(min=0, max=512, message="试验人员长度不能超过 512 个字符")
	public String getExperimentStaff() {
		return experimentStaff;
	}

	public void setExperimentStaff(String experimentStaff) {
		this.experimentStaff = experimentStaff;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@NotNull(message="测试时间不能为空")
	public Date getExperimentTestingtime() {
		return experimentTestingtime;
	}

	public void setExperimentTestingtime(Date experimentTestingtime) {
		this.experimentTestingtime = experimentTestingtime;
	}
	
	@NotBlank(message="创建用户id不能为空")
	@Size(min=0, max=100, message="创建用户id长度不能超过 100 个字符")
	public String getExperimentUserid() {
		return experimentUserid;
	}

	public void setExperimentUserid(String experimentUserid) {
		this.experimentUserid = experimentUserid;
	}
	
}