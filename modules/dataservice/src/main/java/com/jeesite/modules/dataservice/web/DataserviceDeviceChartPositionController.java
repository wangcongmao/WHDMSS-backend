package com.jeesite.modules.dataservice.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.dataservice.dao.DataserviceDeviceChartPositionDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.checkerframework.checker.units.qual.A;
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
import com.jeesite.modules.dataservice.entity.DataserviceDeviceChartPosition;
import com.jeesite.modules.dataservice.service.DataserviceDeviceChartPositionService;

/**
 * 设备拓扑关系图位置Controller
 * @author wangcm
 * @version 2025-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/deviceChartPosition")
public class DataserviceDeviceChartPositionController extends BaseController {

	@Autowired
	private DataserviceDeviceChartPositionService dataserviceDeviceChartPositionService;

	@Autowired
	private DataserviceDeviceChartPositionDao dataserviceDeviceChartPositionDao;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceDeviceChartPosition get(String id, boolean isNewRecord) {
		return dataserviceDeviceChartPositionService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
//	@RequiresPermissions("dataservice:deviceChartPosition:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceDeviceChartPosition dataserviceDeviceChartPosition, Model model) {
		model.addAttribute("dataserviceDeviceChartPosition", dataserviceDeviceChartPosition);
		return "modules/dataservice/dataserviceDeviceChartPositionList";
	}
	
	/**
	 * 查询列表数据
	 */
//	@RequiresPermissions("dataservice:deviceChartPosition:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceDeviceChartPosition> listData(DataserviceDeviceChartPosition dataserviceDeviceChartPosition, HttpServletRequest request, HttpServletResponse response) {
		dataserviceDeviceChartPosition.setPage(new Page<>(request, response));
		Page<DataserviceDeviceChartPosition> page = dataserviceDeviceChartPositionService.findPage(dataserviceDeviceChartPosition);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
//	@RequiresPermissions("dataservice:deviceChartPosition:view")
	@RequestMapping(value = "form")
	public String form(DataserviceDeviceChartPosition dataserviceDeviceChartPosition, Model model) {
		model.addAttribute("dataserviceDeviceChartPosition", dataserviceDeviceChartPosition);
		return "modules/dataservice/dataserviceDeviceChartPositionForm";
	}

	/**
	 * 保存数据
	 */
//	@RequiresPermissions("dataservice:deviceChartPosition:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceDeviceChartPosition dataserviceDeviceChartPosition) {
//		dataserviceDeviceChartPositionService.save(dataserviceDeviceChartPosition);
		dataserviceDeviceChartPositionDao.updatePosition(dataserviceDeviceChartPosition.getPositionX(), dataserviceDeviceChartPosition.getPositionY(), dataserviceDeviceChartPosition.getDeviceName());
		return renderResult(Global.TRUE, text("保存设备拓扑关系图位置成功！"));
	}
	
	/**
	 * 删除数据
	 */
//	@RequiresPermissions("dataservice:deviceChartPosition:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceDeviceChartPosition dataserviceDeviceChartPosition) {
		dataserviceDeviceChartPositionService.delete(dataserviceDeviceChartPosition);
		return renderResult(Global.TRUE, text("删除设备拓扑关系图位置成功！"));
	}

	@RequestMapping(value = "getAll")
	@RequiresPermissions("dataservice:deviceChartPosition:view")
	@ResponseBody
	public List<DataserviceDeviceChartPosition> getAll() {
		List<DataserviceDeviceChartPosition> all = dataserviceDeviceChartPositionService.getAll();
		return all;
	}
	
}