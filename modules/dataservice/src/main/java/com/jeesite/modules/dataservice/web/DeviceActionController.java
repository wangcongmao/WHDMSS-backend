package com.jeesite.modules.dataservice.web;
import java.util.Date;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.json.JSONUtil;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dataservice.entity.*;
import com.jeesite.modules.dataservice.entity.support.ConditionType;
import com.jeesite.modules.dataservice.service.*;
import com.jeesite.modules.dataservice.service.support.WeatherService;
import lombok.Data;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * deviceDataController
 * @author wangcm
 * @version 2025-01-06
 */
// todo 现在是前端分页后端不分页，后面要改为前后端分页
// todo 数据加单位
@Controller
@RequestMapping(value = "/deviceAction")
public class DeviceActionController extends BaseController {

	@Autowired
	private DataserviceDeviceDataService dataserviceDeviceDataService;

	@Resource
	private DataserviceQualityRuleService dataserviceQualityRuleService;

	@Resource
	private DataserviceDeviceStructureService dataserviceDeviceStructureService;

	@Autowired
	private DataserviceDeviceDataConditionService dataserviceDeviceDataConditionService;

	@Resource
	private WeatherService weatherService;

	@Resource DataserviceDeviceakskService dataserviceDeviceakskService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceDeviceData get(String id, boolean isNewRecord) {
		return dataserviceDeviceDataService.get(id, isNewRecord);
	}

	/**
	 * 保存数据
	 */
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceDeviceData dataserviceDeviceData, HttpServletRequest request) {

		if (dataserviceDeviceData == null) {
			return "params error, dataserviceDeviceData can't be null";
		}
		if (dataserviceDeviceData.getDataDeviceId() == null) {
			return "params error, DataDeviceId can't be null";
		}
		DataserviceDeviceStructure structureDevice = dataserviceDeviceStructureService.getByStructureDeviceId(dataserviceDeviceData.getDataDeviceId());
		if (structureDevice.getStructureData() == null) {
			return "params error, StructureData can't be null";
		}
		String structureData = structureDevice.getStructureData();

		try {

			structureData = structureData.substring(11, structureData.length() - 2);
			String[] stringsa = structureData.split("\"prm\":\"");
			for (int i = 1; i < stringsa.length; i++) {
				String paramName = stringsa[i].substring(0, stringsa[i].indexOf('"'));
				dataserviceDeviceDataConditionService.incrementNums(dataserviceDeviceData.getDataDeviceId(), paramName, 0);
			}

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
				data.put(strings1[0], strings1[1].substring(1, strings1[1].length() - 1));
			}

			// 遍历每条质量规则进行验证
			for (DataserviceQualityRule dataserviceQualityRule : ruleByDeviceId) {
				String qualityRule = dataserviceQualityRule.getQualityRule();
				String status = "0";

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
								dataserviceDeviceData.setStatus(ConditionType.ABNORMAL.getCode());
								dataserviceDeviceDataConditionService.incrementNums(dataserviceDeviceData.getDataDeviceId(), param, 2);
								dataserviceDeviceDataConditionService.decrementNums(dataserviceDeviceData.getDataDeviceId(), param, 0);
							}

						} catch (NumberFormatException e) {
							// 解析数字失败，标记为无效并保存
							dataserviceDeviceDataConditionService.incrementNums(dataserviceDeviceData.getDataDeviceId(), param, 2);
							dataserviceDeviceDataConditionService.decrementNums(dataserviceDeviceData.getDataDeviceId(), param, 0);
							dataserviceDeviceData.setStatus(ConditionType.ABNORMAL.getCode());

						}
					}
				}
			}
		} catch (Exception e) {
			return "params error, StructureData don't match Device Data Structure";
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
}