package com.jeesite.modules.dataservice.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jeesite.modules.dataservice.entity.DataserviceQualityRule;
import com.jeesite.modules.dataservice.service.DataserviceQualityRuleService;
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
// todo 数据加单位
@Controller
@RequestMapping(value = "${adminPath}/dataservice/deviceData")
public class DataserviceDeviceDataController extends BaseController {

	@Autowired
	private DataserviceDeviceDataService dataserviceDeviceDataService;

	@Resource
	private DataserviceQualityRuleService dataserviceQualityRuleService;

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

		// 获取该设备的所有质量规则
		List<DataserviceQualityRule> ruleByDeviceId = dataserviceQualityRuleService.getRuleByDeviceId(dataserviceDeviceData.getDataDeviceId());
		Map<String, String> data = new HashMap<>();

		// 解析设备数据
		String dataDeviceData = dataserviceDeviceData.getDataDeviceData();
		dataDeviceData = dataDeviceData.substring(1, dataDeviceData.length() - 1); // 去掉首尾的括号
		String[] strings = dataDeviceData.split(",");

		// 将设备数据的各项参数存入 Map
		for (String string : strings) {
			String[] strings1 = string.split(": ");
			data.put(strings1[0], strings1[1].substring(1, strings1[1].length()-1));
		}

		// 遍历每条质量规则进行验证
		for (DataserviceQualityRule dataserviceQualityRule : ruleByDeviceId) {
			String qualityRule = dataserviceQualityRule.getQualityRule();

			// 分割 qualityRule，得到结构体部分
			String[] ruleParts = qualityRule.split("\\{");

			// 规则从第一个 "{" 后开始，逐条解析每个参数
			for (int i = 2; i < ruleParts.length; i++) {
				// 只解析从第2部分开始的内容
				String rulePart = ruleParts[i];

				// 提取param、comparison、value值
				String param = extractValue(rulePart, "param");
				String comparison = extractValue(rulePart, "comparison");
				String value = extractValue(rulePart, "value");

				// 确保获取到有效的param值
				if (data.containsKey(param)) {
					String s = data.get(param);

					// 根据comparison进行相应的判断
					boolean isValid = false;
					try {
						Double paramValue = Double.parseDouble(s); // 转换为数字进行比较
						Double intValue = Double.parseDouble(value);

						switch (comparison) {
							case ">":
								isValid = paramValue > intValue;
								break;
							case "<":
								isValid = paramValue < intValue;
								break;
							case "=":
								isValid = paramValue == intValue;
								break;
							default:
								// 如果遇到不支持的比较符号
								throw new IllegalArgumentException("不支持的比较符号: " + comparison);
						}

						// 如果校验不通过，标记为无效并保存
						if (!isValid) {
							dataserviceDeviceData.setStatus("6");
							dataserviceDeviceDataService.save(dataserviceDeviceData);
							return renderResult(Global.TRUE, text("保存deviceData成功！"));
						}

					} catch (NumberFormatException e) {
						// 解析数字失败，标记为无效并保存
						dataserviceDeviceData.setStatus("6");
						dataserviceDeviceDataService.save(dataserviceDeviceData);
						return renderResult(Global.TRUE, text("保存deviceData成功！"));
					}
				}
			}
		}

		// 所有规则验证通过后保存数据
		dataserviceDeviceDataService.save(dataserviceDeviceData);
		return renderResult(Global.TRUE, text("保存deviceData成功！"));
	}

	private String extractValue(String input, String key) {
		String startDelimiter = key + "\":\"";  // 找到对应键的开始部分
		int startIndex = input.indexOf(startDelimiter);
		if (startIndex == -1) return "";  // 如果找不到，返回空字符串

		int endIndex = input.indexOf("\"", startIndex + startDelimiter.length());  // 找到值的结束部分
		if (endIndex == -1) return "";  // 如果没有找到结束位置，返回空字符串

		// 返回提取的值
		return input.substring(startIndex + startDelimiter.length(), endIndex);
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