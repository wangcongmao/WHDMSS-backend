package com.jeesite.modules.test1.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.test1.entity.Test1Test1Master;
import com.jeesite.modules.test1.dao.Test1Test1MasterDao;

/**
 * test1_masterService
 * @author wangcm
 * @version 2024-12-27
 */
@Service
public class Test1Test1MasterService extends TreeService<Test1Test1MasterDao, Test1Test1Master> {
	
	/**
	 * 获取单条数据
	 * @param test1Test1Master
	 * @return
	 */
	@Override
	public Test1Test1Master get(Test1Test1Master test1Test1Master) {
		return super.get(test1Test1Master);
	}
	
	/**
	 * 查询分页数据
	 * @param test1Test1Master 查询条件
	 * @param test1Test1Master page 分页对象
	 * @return
	 */
	@Override
	public Page<Test1Test1Master> findPage(Test1Test1Master test1Test1Master) {
		return super.findPage(test1Test1Master);
	}
	
	/**
	 * 查询列表数据
	 * @param test1Test1Master
	 * @return
	 */
	@Override
	public List<Test1Test1Master> findList(Test1Test1Master test1Test1Master) {
		return super.findList(test1Test1Master);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param test1Test1Master
	 */
	@Override
	@Transactional
	public void save(Test1Test1Master test1Test1Master) {
		super.save(test1Test1Master);
	}
	
	/**
	 * 更新状态
	 * @param test1Test1Master
	 */
	@Override
	@Transactional
	public void updateStatus(Test1Test1Master test1Test1Master) {
		super.updateStatus(test1Test1Master);
	}
	
	/**
	 * 删除数据
	 * @param test1Test1Master
	 */
	@Override
	@Transactional
	public void delete(Test1Test1Master test1Test1Master) {
		super.delete(test1Test1Master);
	}
	
}