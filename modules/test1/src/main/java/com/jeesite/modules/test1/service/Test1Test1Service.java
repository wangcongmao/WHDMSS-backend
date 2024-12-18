package com.jeesite.modules.test1.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test1.entity.Test1Test1;
import com.jeesite.modules.test1.dao.Test1Test1Dao;

/**
 * test1Service
 * @author wangcm
 * @version 2024-12-18
 */
@Service
public class Test1Test1Service extends CrudService<Test1Test1Dao, Test1Test1> {
	
	/**
	 * 获取单条数据
	 * @param test1Test1
	 * @return
	 */
	@Override
	public Test1Test1 get(Test1Test1 test1Test1) {
		return super.get(test1Test1);
	}
	
	/**
	 * 查询分页数据
	 * @param test1Test1 查询条件
	 * @param test1Test1 page 分页对象
	 * @return
	 */
	@Override
	public Page<Test1Test1> findPage(Test1Test1 test1Test1) {
		return super.findPage(test1Test1);
	}
	
	/**
	 * 查询列表数据
	 * @param test1Test1
	 * @return
	 */
	@Override
	public List<Test1Test1> findList(Test1Test1 test1Test1) {
		return super.findList(test1Test1);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param test1Test1
	 */
	@Override
	@Transactional
	public void save(Test1Test1 test1Test1) {
		super.save(test1Test1);
	}
	
	/**
	 * 更新状态
	 * @param test1Test1
	 */
	@Override
	@Transactional
	public void updateStatus(Test1Test1 test1Test1) {
		super.updateStatus(test1Test1);
	}
	
	/**
	 * 删除数据
	 * @param test1Test1
	 */
	@Override
	@Transactional
	public void delete(Test1Test1 test1Test1) {
		super.delete(test1Test1);
	}
	
}