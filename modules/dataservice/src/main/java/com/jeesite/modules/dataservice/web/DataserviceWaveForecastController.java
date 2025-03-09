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
import com.jeesite.modules.dataservice.entity.DataserviceWaveForecast;
import com.jeesite.modules.dataservice.service.DataserviceWaveForecastService;

/**
 * 波浪预报存储表Controller
 * @author wangcm
 * @version 2025-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/waveForecast")
public class DataserviceWaveForecastController extends BaseController {

	@Autowired
	private DataserviceWaveForecastService dataserviceWaveForecastService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceWaveForecast get(String id, boolean isNewRecord) {
		return dataserviceWaveForecastService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:waveForecast:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceWaveForecast dataserviceWaveForecast, Model model) {
		model.addAttribute("dataserviceWaveForecast", dataserviceWaveForecast);
		return "modules/dataservice/dataserviceWaveForecastList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:waveForecast:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceWaveForecast> listData(DataserviceWaveForecast dataserviceWaveForecast, HttpServletRequest request, HttpServletResponse response) {
		dataserviceWaveForecast.setPage(new Page<>(request, response));
		Page<DataserviceWaveForecast> page = dataserviceWaveForecastService.findPage(dataserviceWaveForecast);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:waveForecast:view")
	@RequestMapping(value = "form")
	public String form(DataserviceWaveForecast dataserviceWaveForecast, Model model) {
		model.addAttribute("dataserviceWaveForecast", dataserviceWaveForecast);
		return "modules/dataservice/dataserviceWaveForecastForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:waveForecast:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceWaveForecast dataserviceWaveForecast) {
		dataserviceWaveForecastService.save(dataserviceWaveForecast);
		return renderResult(Global.TRUE, text("保存波浪预报存储表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:waveForecast:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceWaveForecast dataserviceWaveForecast) {
		dataserviceWaveForecastService.delete(dataserviceWaveForecast);
		return renderResult(Global.TRUE, text("删除波浪预报存储表成功！"));
	}
	
}