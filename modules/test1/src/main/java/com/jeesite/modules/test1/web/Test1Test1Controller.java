package com.jeesite.modules.test1.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.test1.entity.Test1Test1;
import com.jeesite.modules.test1.service.Test1Test1Service;

/**
 * test1Controller
 * @author wangcm
 * @version 2024-12-17
 */
@Controller
@RequestMapping(value = "${adminPath}/test1/test1")
public class Test1Test1Controller extends BaseController {

	@Autowired
	private Test1Test1Service test1Test1Service;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Test1Test1 get(String id, boolean isNewRecord) {
		return test1Test1Service.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test1:test1:view")
	@RequestMapping(value = {"list", ""})
	public String list(Test1Test1 test1Test1, Model model) {
		model.addAttribute("test1Test1", test1Test1);
		return "modules/test1/test1Test1List";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test1:test1:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Test1Test1> listData(Test1Test1 test1Test1, HttpServletRequest request, HttpServletResponse response) {
		test1Test1.setPage(new Page<>(request, response));
		Page<Test1Test1> page = test1Test1Service.findPage(test1Test1);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test1:test1:view")
	@RequestMapping(value = "form")
	public String form(Test1Test1 test1Test1, Model model) {
		model.addAttribute("test1Test1", test1Test1);
		return "modules/test1/test1Test1Form";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("test1:test1:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Test1Test1 test1Test1) {
		test1Test1Service.save(test1Test1);
		return renderResult(Global.TRUE, text("保存test1成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("test1:test1:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Test1Test1 test1Test1) {
		test1Test1Service.delete(test1Test1);
		return renderResult(Global.TRUE, text("删除test1成功！"));
	}
	
}