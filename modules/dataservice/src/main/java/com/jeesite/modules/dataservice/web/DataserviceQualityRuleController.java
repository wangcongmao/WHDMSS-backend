package com.jeesite.modules.dataservice.web;

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
import com.jeesite.modules.dataservice.entity.DataserviceQualityRule;
import com.jeesite.modules.dataservice.service.DataserviceQualityRuleService;

/**
 * qualityRuleController
 * @author wangcm
 * @version 2025-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/qualityRule")
public class DataserviceQualityRuleController extends BaseController {

	@Autowired
	private DataserviceQualityRuleService dataserviceQualityRuleService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceQualityRule get(String id, boolean isNewRecord) {
		return dataserviceQualityRuleService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:qualityRule:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceQualityRule dataserviceQualityRule, Model model) {
		model.addAttribute("dataserviceQualityRule", dataserviceQualityRule);
		return "modules/dataservice/dataserviceQualityRuleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:qualityRule:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceQualityRule> listData(DataserviceQualityRule dataserviceQualityRule, HttpServletRequest request, HttpServletResponse response) {
		dataserviceQualityRule.setPage(new Page<>(request, response));
		Page<DataserviceQualityRule> page = dataserviceQualityRuleService.findPage(dataserviceQualityRule);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:qualityRule:view")
	@RequestMapping(value = "form")
	public String form(DataserviceQualityRule dataserviceQualityRule, Model model) {
		model.addAttribute("dataserviceQualityRule", dataserviceQualityRule);
		return "modules/dataservice/dataserviceQualityRuleForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:qualityRule:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceQualityRule dataserviceQualityRule) {
		dataserviceQualityRuleService.save(dataserviceQualityRule);
		return renderResult(Global.TRUE, text("保存qualityRule成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:qualityRule:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceQualityRule dataserviceQualityRule) {
		dataserviceQualityRuleService.delete(dataserviceQualityRule);
		return renderResult(Global.TRUE, text("删除qualityRule成功！"));
	}
	
}