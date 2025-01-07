package com.jeesite.modules.dataservice.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dataservice.entity.DataserviceDeviceData;
import com.jeesite.modules.dataservice.service.DataserviceDeviceDataService;
import org.springframework.web.bind.annotation.*;

/**
 * deviceDataController
 * @author wangcm
 * @version 2025-01-06
 */
// todo 现在是前端分页后端不分页，后面要改为前后端分页
@Controller
@RequestMapping(value = "${adminPath}/dataservice/deviceData")
public class DataserviceDeviceDataController extends BaseController {

	@Autowired
	private DataserviceDeviceDataService dataserviceDeviceDataService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceDeviceData get(String id, boolean isNewRecord) {
		return dataserviceDeviceDataService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:deviceData:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceDeviceData dataserviceDeviceData, Model model) {
		model.addAttribute("dataserviceDeviceData", dataserviceDeviceData);
		return "modules/dataservice/dataserviceDeviceDataList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:deviceData:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceDeviceData> listData(DataserviceDeviceData dataserviceDeviceData, HttpServletRequest request, HttpServletResponse response) {
		dataserviceDeviceData.setPage(new Page<>(request, response));
		Page<DataserviceDeviceData> page = dataserviceDeviceDataService.findPage(dataserviceDeviceData);
		return page;
	}

	@RequiresPermissions("dataservice:deviceData:view")
	@RequestMapping(value = "listDataPage")
	@ResponseBody
	public List<DataserviceDeviceData> getDeviceDataByDeviceId(DataserviceDeviceData dataserviceDeviceData) {
		if (dataserviceDeviceData.getDataDeviceId().equals("")) {
			return dataserviceDeviceDataService.findList(dataserviceDeviceData);
		} else {
			return dataserviceDeviceDataService.getDeviceDataByDeviceId(dataserviceDeviceData.getDataDeviceId());
		}
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:deviceData:view")
	@RequestMapping(value = "form")
	public String form(DataserviceDeviceData dataserviceDeviceData, Model model) {
		model.addAttribute("dataserviceDeviceData", dataserviceDeviceData);
		return "modules/dataservice/dataserviceDeviceDataForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:deviceData:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceDeviceData dataserviceDeviceData) {
		dataserviceDeviceDataService.save(dataserviceDeviceData);
		return renderResult(Global.TRUE, text("保存deviceData成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:deviceData:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceDeviceData dataserviceDeviceData) {
		dataserviceDeviceDataService.delete(dataserviceDeviceData);
		return renderResult(Global.TRUE, text("删除deviceData成功！"));
	}
	
}