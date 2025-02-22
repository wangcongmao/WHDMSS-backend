package com.jeesite.modules.dataservice.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.dataservice.entity.support.ParamDataCondition;
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
import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataCondition;
import com.jeesite.modules.dataservice.service.DataserviceDeviceDataConditionService;

/**
 * dataservice_device_data_conditionController
 * @author wangcm
 * @version 2025-02-21
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/deviceDataCondition")
public class DataserviceDeviceDataConditionController extends BaseController {

	@Autowired
	private DataserviceDeviceDataConditionService dataserviceDeviceDataConditionService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceDeviceDataCondition get(Integer tid, boolean isNewRecord) {
		return dataserviceDeviceDataConditionService.get(tid, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:deviceDataCondition:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceDeviceDataCondition dataserviceDeviceDataCondition, Model model) {
		model.addAttribute("dataserviceDeviceDataCondition", dataserviceDeviceDataCondition);
		return "modules/dataservice/dataserviceDeviceDataConditionList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:deviceDataCondition:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceDeviceDataCondition> listData(DataserviceDeviceDataCondition dataserviceDeviceDataCondition, HttpServletRequest request, HttpServletResponse response) {
		dataserviceDeviceDataCondition.setPage(new Page<>(request, response));
		Page<DataserviceDeviceDataCondition> page = dataserviceDeviceDataConditionService.findPage(dataserviceDeviceDataCondition);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:deviceDataCondition:view")
	@RequestMapping(value = "form")
	public String form(DataserviceDeviceDataCondition dataserviceDeviceDataCondition, Model model) {
		model.addAttribute("dataserviceDeviceDataCondition", dataserviceDeviceDataCondition);
		return "modules/dataservice/dataserviceDeviceDataConditionForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:deviceStructure:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		dataserviceDeviceDataConditionService.save(dataserviceDeviceDataCondition);
		return renderResult(Global.TRUE, text("保存dataservice_device_data_condition成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:deviceDataCondition:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceDeviceDataCondition dataserviceDeviceDataCondition) {
		dataserviceDeviceDataConditionService.delete(dataserviceDeviceDataCondition);
		return renderResult(Global.TRUE, text("删除dataservice_device_data_condition成功！"));
	}

	/**
	 * 获取某个设备的各项参数的数据质量情况
	 * 考虑新建一个表-新建数据结构时在表中加记录   id，参数名，0，正常个数  |  id，参数名，1，可疑个数  |  id，参数名，2，异常个数
	 */
	/**
	 * 根据deviceId查询当前设备各参数数据状况
	 */
	@RequiresPermissions("dataservice:deviceData:view")
	@RequestMapping(value = {"getParamConditionByDeviceId", ""})
	@ResponseBody
	public List<ParamDataCondition> getParamConditionByDeviceId(String dataDeviceId) {
		return dataserviceDeviceDataConditionService.getParamConditionByDeviceId(dataDeviceId);
	}
	
}