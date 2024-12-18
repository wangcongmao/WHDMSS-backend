package com.jeesite.modules.dataservice.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
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
import com.jeesite.modules.dataservice.entity.DataserviceExperiment;
import com.jeesite.modules.dataservice.service.DataserviceExperimentService;

/**
 * experimentInfoController
 * @author wangcm
 * @version 2024-12-18
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/experiment")
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
	@RequiresPermissions("dataservice:experiment:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceExperiment dataserviceExperiment, Model model) {
		model.addAttribute("dataserviceExperiment", dataserviceExperiment);
		return "modules/dataservice/dataserviceExperimentList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:experiment:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceExperiment> listData(DataserviceExperiment dataserviceExperiment, HttpServletRequest request, HttpServletResponse response) {
		dataserviceExperiment.setPage(new Page<>(request, response));
		Page<DataserviceExperiment> page = dataserviceExperimentService.findPage(dataserviceExperiment);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:experiment:view")
	@RequestMapping(value = "form")
	public String form(DataserviceExperiment dataserviceExperiment, Model model) {
		model.addAttribute("dataserviceExperiment", dataserviceExperiment);
		return "modules/dataservice/dataserviceExperimentForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:experiment:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceExperiment dataserviceExperiment) {
		User user = UserUtils.getUser();
		String id = user.getId();
		dataserviceExperiment.setExperimentUserid(id);
		dataserviceExperimentService.save(dataserviceExperiment);
		return renderResult(Global.TRUE, text("保存experimentInfo成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:experiment:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceExperiment dataserviceExperiment) {
		dataserviceExperimentService.delete(dataserviceExperiment);
		return renderResult(Global.TRUE, text("删除experimentInfo成功！"));
	}
	
}