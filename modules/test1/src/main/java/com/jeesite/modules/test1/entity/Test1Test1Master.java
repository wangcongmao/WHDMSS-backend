package com.jeesite.modules.test1.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * test1_masterEntity
 * @author wangcm
 * @version 2024-12-27
 */
@Table(name="test1_test1_master", alias="a", label="测试信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="office_code", attrName="officeCode", label="部门编码"),
		@Column(name="office_name", attrName="officeName", label="部门名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(includeEntity=DataEntity.class),
		@Column(name="parent_code", attrName="parentCode", label="父级编号", isParentCode=true),
		@Column(includeEntity=TreeEntity.class),
	}, orderBy="a.tree_sorts, a.id"
)
public class Test1Test1Master extends TreeEntity<Test1Test1Master> {
	
	private static final long serialVersionUID = 1L;
	private String officeCode;		// 部门编码
	private String officeName;		// 部门名称

	public Test1Test1Master() {
		this(null);
	}
	
	public Test1Test1Master(String id){
		super(id);
	}
	
	@Override
	public Test1Test1Master getParent() {
		return parent;
	}

	@Override
	public void setParent(Test1Test1Master parent) {
		this.parent = parent;
	}
	
	@Size(min=0, max=64, message="部门编码长度不能超过 64 个字符")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	
	@Size(min=0, max=100, message="部门名称长度不能超过 100 个字符")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
}