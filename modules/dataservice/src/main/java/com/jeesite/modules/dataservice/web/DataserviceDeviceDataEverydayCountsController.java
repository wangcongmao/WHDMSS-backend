package com.jeesite.modules.dataservice.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.dataservice.entity.vo.DataserviceDeviceDataEverydayCountsVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataEverydayCounts;
import com.jeesite.modules.dataservice.service.DataserviceDeviceDataEverydayCountsService;

/**
 * 设备每天数据量Controller
 * @author wangcm
 * @version 2025-02-23
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/deviceDataEverydayCounts")
public class DataserviceDeviceDataEverydayCountsController extends BaseController {

	@Autowired
	private DataserviceDeviceDataEverydayCountsService dataserviceDeviceDataEverydayCountsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceDeviceDataEverydayCounts get(String id, boolean isNewRecord) {
		return dataserviceDeviceDataEverydayCountsService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:deviceDataEverydayCounts:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts, Model model) {
		model.addAttribute("dataserviceDeviceDataEverydayCounts", dataserviceDeviceDataEverydayCounts);
		return "modules/dataservice/dataserviceDeviceDataEverydayCountsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:deviceDataEverydayCounts:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceDeviceDataEverydayCounts> listData(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts, HttpServletRequest request, HttpServletResponse response) {
		dataserviceDeviceDataEverydayCounts.setPage(new Page<>(request, response));
		Page<DataserviceDeviceDataEverydayCounts> page = dataserviceDeviceDataEverydayCountsService.findPage(dataserviceDeviceDataEverydayCounts);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:deviceDataEverydayCounts:view")
	@RequestMapping(value = "form")
	public String form(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts, Model model) {
		model.addAttribute("dataserviceDeviceDataEverydayCounts", dataserviceDeviceDataEverydayCounts);
		return "modules/dataservice/dataserviceDeviceDataEverydayCountsForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:deviceDataEverydayCounts:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts) {
		dataserviceDeviceDataEverydayCountsService.save(dataserviceDeviceDataEverydayCounts);
		return renderResult(Global.TRUE, text("保存设备每天数据量成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:deviceDataEverydayCounts:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceDeviceDataEverydayCounts dataserviceDeviceDataEverydayCounts) {
		dataserviceDeviceDataEverydayCountsService.delete(dataserviceDeviceDataEverydayCounts);
		return renderResult(Global.TRUE, text("删除设备每天数据量成功！"));
	}

	/**
	 * 返回设备每日数据量列表
	 */
	@RequiresPermissions("dataservice:deviceDataEverydayCounts:view")
	@GetMapping(value = "getAll")
	@ResponseBody
	public List<DataserviceDeviceDataEverydayCountsVO> getAll() {

		List<DataserviceDeviceDataEverydayCountsVO> res = dataserviceDeviceDataEverydayCountsService.getAll();
		return res;
	}
	
}