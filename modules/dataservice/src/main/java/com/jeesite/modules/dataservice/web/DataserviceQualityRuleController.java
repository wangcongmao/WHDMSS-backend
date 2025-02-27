package com.jeesite.modules.dataservice.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.dataservice.entity.DataserviceDeviceStructure;
import com.jeesite.modules.dataservice.service.DataserviceDeviceDataService;
import com.jeesite.modules.dataservice.service.DataserviceDeviceStructureService;
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
 * @author wangcmn
 * @version 2025-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/qualityRule")
public class DataserviceQualityRuleController extends BaseController {

	@Autowired
	private DataserviceQualityRuleService dataserviceQualityRuleService;

	@Autowired
	private DataserviceDeviceStructureService dataserviceDeviceStructureService;

	@Resource
	private DataserviceDeviceDataService dataserviceDeviceDataService;
	
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
	 * 返回设备列表
	 */
	@RequiresPermissions("dataservice:qualityRule:view")
	@RequestMapping(value = "getDevice")
	@ResponseBody
	public List<DataserviceDeviceStructure> getDevice(HttpServletRequest request, HttpServletResponse response) {
		return dataserviceDeviceStructureService.list();
	}

	/**
	 * 返回设备ID列表
	 */
	@RequiresPermissions("dataservice:qualityRule:view")
	@RequestMapping(value = "getDeviceID")
	@ResponseBody
	public List<Map<String, String>> getDeviceID(DataserviceDeviceStructure dataserviceDeviceStructure) {
//		if (dataserviceDeviceStructure.getDataType() != null) {
//			List<DataserviceDeviceStructure> list = dataserviceDeviceStructureService.findList(dataserviceDeviceStructure);
//		}
		List<DataserviceDeviceStructure> list = dataserviceDeviceStructureService.findList(dataserviceDeviceStructure);

		// 转换成前端需要的格式
		List<Map<String, String>> result = list.stream().map(device -> {
			Map<String, String> map = new HashMap<>();
			map.put("label", device.getStructureDeviceId()); // 设备编号作为 label
			map.put("value", device.getStructureDeviceId()); // 设备编号作为 value
			return map;
		}).collect(Collectors.toList());

		return result;
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

	/**
	 * 获取数据量
	 * 正常数据，可疑数据，异常数据
	 */
	@RequiresPermissions("dataservice:qualityRule:")
	@RequestMapping(value = "getDataCount")
	@ResponseBody
	public Map<String, Integer> getDataCount() {

		return new HashMap<>();
	}
	
}