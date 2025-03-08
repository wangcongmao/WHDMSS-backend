package com.jeesite.modules.dataservice.web;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.dataservice.entity.DataserviceDeviceDataCondition;
import com.jeesite.modules.dataservice.service.*;
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
import com.jeesite.modules.dataservice.entity.DataserviceDeviceStructure;

/**
 * deviceStructureController
 * @author wangcm
 * @version 2025-01-06
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/deviceStructure")
public class DataserviceDeviceStructureController extends BaseController {

	@Autowired
	private DataserviceDeviceStructureService dataserviceDeviceStructureService;

	@Resource
	private DataserviceDeviceDataConditionService dataserviceDeviceDataConditionService;

	@Resource
	private DataserviceDeviceDataEverydayCountsService dataserviceDeviceDataEverydayCountsService;

	@Resource
	private DataserviceDeviceDataService dataserviceDeviceDataService;

	@Resource
	private DataserviceQualityRuleService dataserviceQualityRuleService;


	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceDeviceStructure get(String id, boolean isNewRecord) {
		return dataserviceDeviceStructureService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:deviceStructure:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceDeviceStructure dataserviceDeviceStructure, Model model) {
		model.addAttribute("dataserviceDeviceStructure", dataserviceDeviceStructure);
		return "modules/dataservice/dataserviceDeviceStructureList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:deviceStructure:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceDeviceStructure> listData(DataserviceDeviceStructure dataserviceDeviceStructure, HttpServletRequest request, HttpServletResponse response) {
		dataserviceDeviceStructure.setPage(new Page<>(request, response));
		Page<DataserviceDeviceStructure> page = dataserviceDeviceStructureService.findPage(dataserviceDeviceStructure);
		List<DataserviceDeviceStructure> list = page.getList();
		for (int i = 0; i < list.size(); i++) {
			DataserviceDeviceStructure dataserviceDeviceStructure1 = list.get(i);
			String structureData = dataserviceDeviceStructure1.getStructureData();
			String[] strings = structureData.split("prm");
			for (int i1 = 1; i1 < strings.length; i1++) {
				// ":"a","type":"number"},{"
				String[] strings1 = strings[i1].split("\"type\"");
				// ":"a","
				String substring1 = strings1[0].substring(3, strings1[0].lastIndexOf(",")-1);
				// ":"number"},{"
				String substring2 = strings1[1].substring(2, strings1[1].lastIndexOf("}") - 1);
				strings[i1] = substring1 + ":" + substring2;
				if (i1 != strings.length-1) {
					strings[i1] += " | ";
				}
			}
			String a = "";
			for (int i1 = 1; i1 < strings.length; i1++) {
				a += strings[i1];
			}
			dataserviceDeviceStructure1.setStructureData(a.substring(0, a.lastIndexOf("\"")));
		}
		return page;
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:deviceStructure:view")
	@RequestMapping(value = "listDataForTable")
	@ResponseBody
	public Page<DataserviceDeviceStructure> listDataForTable(DataserviceDeviceStructure dataserviceDeviceStructure, HttpServletRequest request, HttpServletResponse response) {
		dataserviceDeviceStructure.setPage(new Page<>(request, response));
		Page<DataserviceDeviceStructure> page = dataserviceDeviceStructureService.findPage(dataserviceDeviceStructure);
		List<DataserviceDeviceStructure> list = page.getList();
		for (int i = 0; i < list.size(); i++) {
			DataserviceDeviceStructure dataserviceDeviceStructure1 = list.get(i);
			String structureData = dataserviceDeviceStructure1.getStructureData();
			String[] strings = structureData.split("prm");
			for (int i1 = 1; i1 < strings.length; i1++) {
				// ":"a","type":"number"},{"
				String[] strings1 = strings[i1].split("type");
				// ":"a","
				String substring1 = strings1[0].substring(3, strings1[0].lastIndexOf(",")-1);
				// ":"number"},{"
				String substring2 = strings1[1].substring(3, strings1[1].lastIndexOf("}") - 1);
				strings[i1] = substring1 + ":" + substring2;
				if (i1 != strings.length-1) {
					strings[i1] += " | ";
				}
			}
			String a = new String(Arrays.toString(strings));
			dataserviceDeviceStructure1.setStructureData(a);
		}

		System.out.println(0);
		return page;
	}

	@RequiresPermissions("dataservice:deviceStructure:view")
	@RequestMapping(value = "getOne")
	@ResponseBody
	public DataserviceDeviceStructure getOne(DataserviceDeviceStructure dataserviceDeviceStructure) {
		DataserviceDeviceStructure dataserviceDeviceStructure1 = dataserviceDeviceStructureService.getOne(dataserviceDeviceStructure);

		return dataserviceDeviceStructure1;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:deviceStructure:view")
	@RequestMapping(value = "form")
	public String form(DataserviceDeviceStructure dataserviceDeviceStructure, Model model) {
		model.addAttribute("dataserviceDeviceStructure", dataserviceDeviceStructure);
		return "modules/dataservice/dataserviceDeviceStructureForm";
	}

	/**
	 * 保存数据
	 * 新增数据结构：需要解析然后创建保存 dataservice_device_data_condition
	 * 每一个 param 对应有三个结构初始化 nums 为 0
	 * {"structs":[{"prm":"a","type":"number"},{"prm":"b","type":"string"},{"prm":"c","type":"date"}]}
	 * {"prm":"a","type":"number"},{"prm":"b","type":"string"},{"prm":"c","type":"date"}
	 */
	@RequiresPermissions("dataservice:deviceStructure:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceDeviceStructure dataserviceDeviceStructure) {
		// 如果修改数据规则名称 数据结构重新保存，数据每日质量？，数据参数质量？重新统计？，为了方便，不允许修改
		dataserviceDeviceStructureService.save(dataserviceDeviceStructure);
		String structureData = dataserviceDeviceStructure.getStructureData();
		structureData = structureData.substring(11, structureData.length()-2);
		String[] strings = structureData.split("\"prm\":\"");
		for (int i = 1; i < strings.length; i++) {
			String paramName = strings[i].substring(0, strings[i].indexOf('"'));

			DataserviceDeviceDataCondition dataserviceDeviceDataCondition = new DataserviceDeviceDataCondition();
			dataserviceDeviceDataCondition.setStructureDeviceId(dataserviceDeviceStructure.getStructureDeviceId());
			dataserviceDeviceDataCondition.setDeviceParamName(paramName);
			dataserviceDeviceDataCondition.setNums(0);
			dataserviceDeviceDataCondition.setConditionType(0);
//			dataserviceDeviceDataCondition.setId(generateRandomId()+"");
			dataserviceDeviceDataConditionService.insertDeviceDataCondition(dataserviceDeviceDataCondition);

			DataserviceDeviceDataCondition dataserviceDeviceDataCondition1 = new DataserviceDeviceDataCondition();
			dataserviceDeviceDataCondition1.setStructureDeviceId(dataserviceDeviceStructure.getStructureDeviceId());
			dataserviceDeviceDataCondition1.setDeviceParamName(paramName);
			dataserviceDeviceDataCondition1.setNums(0);
			dataserviceDeviceDataCondition1.setConditionType(1);
//			dataserviceDeviceDataCondition1.setId(generateRandomId()+"");
			dataserviceDeviceDataConditionService.insertDeviceDataCondition(dataserviceDeviceDataCondition1);

			DataserviceDeviceDataCondition dataserviceDeviceDataCondition2 = new DataserviceDeviceDataCondition();
			dataserviceDeviceDataCondition2.setStructureDeviceId(dataserviceDeviceStructure.getStructureDeviceId());
			dataserviceDeviceDataCondition2.setDeviceParamName(paramName);
			dataserviceDeviceDataCondition2.setNums(0);
			dataserviceDeviceDataCondition2.setConditionType(2);
//			dataserviceDeviceDataCondition2.setId(generateRandomId()+"");
			dataserviceDeviceDataConditionService.insertDeviceDataCondition(dataserviceDeviceDataCondition2);
		}
		return renderResult(Global.TRUE, text("保存设备数据结构成功！"));
	}

	// 生成一个8位的随机ID
	public static int generateRandomId() {
		Random random = new Random();
		return 10000000 + random.nextInt(90000000);  // 生成一个范围在10000000到99999999之间的随机数
	}

	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:deviceStructure:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceDeviceStructure dataserviceDeviceStructure) {
		String deviceId = dataserviceDeviceStructure.getStructureDeviceId();
		dataserviceDeviceStructureService.delete(dataserviceDeviceStructure);
		dataserviceDeviceDataConditionService.deleteByStructureDeviceId(dataserviceDeviceStructure.getStructureDeviceId());
		dataserviceDeviceDataEverydayCountsService.deleteByDeviceId(deviceId);
		dataserviceDeviceDataService.deleteByDeviceId(deviceId);
		dataserviceQualityRuleService.deleteByDeviceId(deviceId);
		return renderResult(Global.TRUE, text("删除设备数据结构成功！"));
	}

}