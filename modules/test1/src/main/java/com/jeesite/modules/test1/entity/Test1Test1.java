package com.jeesite.modules.test1.entity;

import javax.validation.constraints.Size;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * test1Entity
 * @author wangcm
 * @version 2024-12-18
 */
@Table(name="test1_test1", alias="a", label="test1信息", columns={
		@Column(name="id", attrName="id", label="主键", isPK=true),
		@Column(name="test1_name", attrName="test1Name", label="姓名", queryType=QueryType.LIKE),
		@Column(name="test1_age", attrName="test1Age", label="年龄"),
	}, orderBy="a.id DESC"
)
public class Test1Test1 extends DataEntity<Test1Test1> {
	
	private static final long serialVersionUID = 1L;
	private String test1Name;		// 姓名
	private String test1Age;		// 年龄

	public Test1Test1() {
		this(null);
	}
	
	public Test1Test1(String id){
		super(id);
	}
	
	@Size(min=0, max=100, message="姓名长度不能超过 100 个字符")
	public String getTest1Name() {
		return test1Name;
	}

	public void setTest1Name(String test1Name) {
		this.test1Name = test1Name;
	}
	
	@Size(min=0, max=100, message="年龄长度不能超过 100 个字符")
	public String getTest1Age() {
		return test1Age;
	}

	public void setTest1Age(String test1Age) {
		this.test1Age = test1Age;
	}
	
}