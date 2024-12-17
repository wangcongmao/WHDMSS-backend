package com.jeesite.modules.experiment.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.experiment.entity.DataserviceExperiment;
import com.jeesite.modules.experiment.service.DataserviceExperimentService;

/**
 * 试验信息表Controller
 * @author wangcm
 * @version 2024-12-16
 */
@Controller
@Api(tags = "试验信息管理")
@RequestMapping(value = "${adminPath}/experiment/dataserviceExperiment")
@CrossOrigin
public class DataserviceExperimentController extends BaseController {

	@Autowired
	private DataserviceExperimentService dataserviceExperimentService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceExperiment get(String id, boolean isNewRecord) {
		return dataserviceExperimentService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("experiment:dataserviceExperiment:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceExperiment dataserviceExperiment, Model model) {
		model.addAttribute("dataserviceExperiment", dataserviceExperiment);
		return "modules/experiment/dataserviceExperimentList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("experiment:dataserviceExperiment:view")
	@PostMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceExperiment> listData(DataserviceExperiment dataserviceExperiment, HttpServletRequest request, HttpServletResponse response) {
		dataserviceExperiment.setPage(new Page<>(request, response));
		Page<DataserviceExperiment> page = dataserviceExperimentService.findPage(dataserviceExperiment);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("experiment:dataserviceExperiment:view")
	@RequestMapping(value = "form")
	public String form(DataserviceExperiment dataserviceExperiment, Model model) {
		model.addAttribute("dataserviceExperiment", dataserviceExperiment);
		return "modules/experiment/dataserviceExperimentForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("experiment:dataserviceExperiment:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceExperiment dataserviceExperiment) {
		dataserviceExperimentService.save(dataserviceExperiment);
		return renderResult(Global.TRUE, text("保存试验信息表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("experiment:dataserviceExperiment:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceExperiment dataserviceExperiment) {
		dataserviceExperimentService.delete(dataserviceExperiment);
		return renderResult(Global.TRUE, text("删除试验信息表成功！"));
	}
	
}