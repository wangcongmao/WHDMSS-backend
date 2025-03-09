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
import com.jeesite.modules.dataservice.entity.DataserviceTidalForecast;
import com.jeesite.modules.dataservice.service.DataserviceTidalForecastService;

/**
 * 潮流预报存储表Controller
 * @author wangcm
 * @version 2025-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/tidalForecast")
public class DataserviceTidalForecastController extends BaseController {

	@Autowired
	private DataserviceTidalForecastService dataserviceTidalForecastService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceTidalForecast get(String id, boolean isNewRecord) {
		return dataserviceTidalForecastService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:tidalForecast:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceTidalForecast dataserviceTidalForecast, Model model) {
		model.addAttribute("dataserviceTidalForecast", dataserviceTidalForecast);
		return "modules/dataservice/dataserviceTidalForecastList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:tidalForecast:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceTidalForecast> listData(DataserviceTidalForecast dataserviceTidalForecast, HttpServletRequest request, HttpServletResponse response) {
		dataserviceTidalForecast.setPage(new Page<>(request, response));
		Page<DataserviceTidalForecast> page = dataserviceTidalForecastService.findPage(dataserviceTidalForecast);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:tidalForecast:view")
	@RequestMapping(value = "form")
	public String form(DataserviceTidalForecast dataserviceTidalForecast, Model model) {
		model.addAttribute("dataserviceTidalForecast", dataserviceTidalForecast);
		return "modules/dataservice/dataserviceTidalForecastForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:tidalForecast:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceTidalForecast dataserviceTidalForecast) {
		dataserviceTidalForecastService.save(dataserviceTidalForecast);
		return renderResult(Global.TRUE, text("保存潮流预报存储表成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:tidalForecast:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceTidalForecast dataserviceTidalForecast) {
		dataserviceTidalForecastService.delete(dataserviceTidalForecast);
		return renderResult(Global.TRUE, text("删除潮流预报存储表成功！"));
	}
	
}