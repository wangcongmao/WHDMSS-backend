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
import com.jeesite.modules.dataservice.entity.DataserviceExperimentService;
import com.jeesite.modules.dataservice.service.DataserviceExperimentServiceService;

/**
 * experimentDeviceController
 * @author wangcm
 * @version 2024-12-18
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/experimentService")
public class DataserviceExperimentServiceController extends BaseController {

	@Autowired
	private DataserviceExperimentServiceService dataserviceExperimentServiceService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceExperimentService get(String id, boolean isNewRecord) {
		return dataserviceExperimentServiceService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:experimentService:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceExperimentService dataserviceExperimentService, Model model) {
		model.addAttribute("dataserviceExperimentService", dataserviceExperimentService);
		return "modules/dataservice/dataserviceExperimentServiceList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:experimentService:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceExperimentService> listData(DataserviceExperimentService dataserviceExperimentService, HttpServletRequest request, HttpServletResponse response) {
		dataserviceExperimentService.setPage(new Page<>(request, response));
		Page<DataserviceExperimentService> page = dataserviceExperimentServiceService.findPage(dataserviceExperimentService);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:experimentService:view")
	@RequestMapping(value = "form")
	public String form(DataserviceExperimentService dataserviceExperimentService, Model model) {
		model.addAttribute("dataserviceExperimentService", dataserviceExperimentService);
		return "modules/dataservice/dataserviceExperimentServiceForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:experimentService:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceExperimentService dataserviceExperimentService) {
		dataserviceExperimentServiceService.save(dataserviceExperimentService);
		return renderResult(Global.TRUE, text("保存experimentDevice成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:experimentService:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceExperimentService dataserviceExperimentService) {
		dataserviceExperimentServiceService.delete(dataserviceExperimentService);
		return renderResult(Global.TRUE, text("删除experimentDevice成功！"));
	}
	
}