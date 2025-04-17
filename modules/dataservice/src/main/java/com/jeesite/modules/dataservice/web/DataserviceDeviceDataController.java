package com.jeesite.modules.dataservice.web;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.StrUtil;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.modules.dataservice.entity.*;
import com.jeesite.modules.dataservice.entity.support.ConditionType;
import com.jeesite.modules.dataservice.service.*;
import com.jeesite.modules.dataservice.service.support.WeatherService;
import lombok.Data;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

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

	@Resource
	private DataserviceDeviceStructureService dataserviceDeviceStructureService;

	@Autowired
	private DataserviceDeviceDataConditionService dataserviceDeviceDataConditionService;

	@Resource
	private WeatherService weatherService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceDeviceData get(String id, boolean isNewRecord) {
		return dataserviceDeviceDataService.get(id, isNewRecord);
	}

	@Autowired
	private DataserviceTidalForecastService tidalForecastService;

	@Autowired
	private StandardServletMultipartResolver multipartResolver;

	/**
	 * 上传Excel文件并导入数据
	 */
	@PostMapping("/importExcel")
	@ResponseBody
	public Map<String, Object> importExcel(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		String contentType = request.getContentType();
		logger.info("请求的Content-Type: " + contentType);

		try {
			// 检查是否是multipart请求
			if (!multipartResolver.isMultipart(request)) {
				result.put("success", false);
				result.put("message", "请求格式错误，请确保正确上传文件");
				return result;
			}

			// 转换为MultipartHttpServletRequest
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multiRequest.getFile("file");

			if (file == null || file.isEmpty()) {
				result.put("success", false);
				result.put("message", "请选择要上传的文件");
				return result;
			}

			// 检查文件类型
			String fileName = file.getOriginalFilename();
			if (fileName == null || !(fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))) {
				result.put("success", false);
				result.put("message", "只支持.xlsx或.xls格式的Excel文件");
				return result;
			}

			// 导入Excel数据
			ExcelImport ei = new ExcelImport(file, 1, 0);
			List<DataserviceTidalForecast> list = ei.getDataList(DataserviceTidalForecast.class);

			// 处理导入的数据
			for (DataserviceTidalForecast forecast : list) {
				tidalForecastService.save(forecast);
			}

			result.put("success", true);
			result.put("message", "成功导入 " + list.size() + " 条数据");

		} catch (Exception e) {
			logger.error("导入Excel失败", e);
			result.put("success", false);
			result.put("message", "导入失败：" + e.getMessage());
		}
		return result;
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
	public List<DataserviceDeviceData> getDeviceDataByDeviceId(PageData pageData) {
		DataserviceDeviceData dataserviceDeviceData = new DataserviceDeviceData();
		dataserviceDeviceData.setDataDeviceId(pageData.getDataDeviceId());
		if (dataserviceDeviceData.getDataDeviceId().equals("")) {
			return dataserviceDeviceDataService.findList(dataserviceDeviceData);
		} else {
			return dataserviceDeviceDataService.pageData(dataserviceDeviceData.getDataDeviceId(), pageData.getPage(), pageData.getPageSize());
		}
	}


	/**
	 * 根据经纬度获取水深
	 * @param dataDeviceId 设备id
	 * @param longitude 经度
	 * @param latitude 维度
	 * @return
	 */
	@RequiresPermissions("dataservice:deviceData:view")
	@RequestMapping(value = "getDepth")
	@ResponseBody
	public Integer getDepth(String dataDeviceId, String longitude, String latitude) {
		if (dataDeviceId.equals("") || latitude.equals("") || longitude.equals("")) {
			return null;
		}
		Integer depth = dataserviceDeviceDataService.getDepth(dataDeviceId, longitude, latitude);

		return depth;
	}

	/**
	 * 试验场-实时监测数据
	 */
	@RequiresPermissions("dataservice:deviceData:view")
	@RequestMapping(value = "listRecentlyData")
	@ResponseBody
	public List<DataserviceDeviceData> getRecentDeviceData(PageData pageData) {
		if (pageData.getOffSite() == null || pageData.getPageSize() == null) {
			return null;
		}
		return dataserviceDeviceDataService.pageData(pageData.getOffSite(), pageData.getPageSize());
	}

	public List<DataserviceDeviceData> getAllByDeviceId(String qualityDeviceId) {
		return dataserviceDeviceDataService.getAllByDeviceId(qualityDeviceId);
	}

	@Data
	class PageData {
		private String dataDeviceId;        // 设备编号
		private Integer page;
		private Integer pageSize;
		private Integer offSite;    // 偏移量，有优先用偏移量，没有的话再用 page 和 pageSize 计算
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

		/**
		 * 删除数据
		 * 默认不能删数据，此接口未使用，若要使用删除接口，需要添加删除数据时对质量数据数量的修改
		 */
		@RequiresPermissions("dataservice:deviceData:edit")
		@RequestMapping(value = "delete")
		@ResponseBody
		public String delete(DataserviceDeviceData dataserviceDeviceData) {
			dataserviceDeviceDataService.delete(dataserviceDeviceData);
			return renderResult(Global.TRUE, text("删除deviceData成功！"));
		}

		/**
		 * 获取正常数据总数
		 */
		@RequiresPermissions("dataservice:deviceData:view")
		@GetMapping(value = {"getNormalDataCount"})
		@ResponseBody
		public Integer getNormalDataCount() {
			return dataserviceDeviceDataService.getNormalDataCount();
		}

		/**
		 * 获取数据总数
		 */
		@RequiresPermissions("dataservice:deviceData:view")
		@GetMapping(value = {"getDataCount"})
		@ResponseBody
		public Integer getDataCount(DataserviceDeviceData dataserviceDeviceData) {
			if (dataserviceDeviceData == null || dataserviceDeviceData.getDataDeviceId() == null) {
				return 0;
			}
			Integer count = dataserviceDeviceDataService.getDataCount(dataserviceDeviceData.getDataDeviceId());
			return count;
		}

		/**
		 * 获取可疑数据总数
		 */
		@RequiresPermissions("dataservice:deviceData:view")
		@GetMapping(value = {"getUncertainDataCount", ""})
		@ResponseBody
		public Integer getUncertainDataCount() {
			return dataserviceDeviceDataService.getUncertainDataCount();
		}

		/**
		 * 获取异常数据总数
		 */
		@RequiresPermissions("dataservice:deviceData:view")
		@GetMapping(value = {"getErrorDataCount", ""})
		@ResponseBody
		public Integer getErrorDataCount() {
			return dataserviceDeviceDataService.getErrorDataCount();
		}

		/**
		 * 获取所有设备数据质量情况
		 */
		@RequiresPermissions("dataservice:deviceData:view")
		@RequestMapping(value = {"getDeviceDataQualityCount", ""})
		@ResponseBody
		public List<DevicesDataQuality> getDeviceDataQualityCount(String dataDeviceId) {
			List<DataserviceDeviceStructure> list = dataserviceDeviceStructureService.list();
			List<DevicesDataQuality> res = new ArrayList<>();
			for (DataserviceDeviceStructure dataserviceDeviceStructure : list) {
				DevicesDataQuality devicesDataQuality = new DevicesDataQuality();
				Integer deviceNormalDataCount = dataserviceDeviceDataService.getDeviceNormalDataCount(dataserviceDeviceStructure.getStructureDeviceId());
				Integer deviceUncertainDataCount = dataserviceDeviceDataService.getDeviceUncertainDataCount(dataserviceDeviceStructure.getStructureDeviceId());
				Integer deviceErrorDataCount = dataserviceDeviceDataService.getDeviceErrorDataCount(dataserviceDeviceStructure.getStructureDeviceId());
				devicesDataQuality.setName(dataserviceDeviceStructure.getStructureDeviceId());
				devicesDataQuality.setNormalCount(deviceNormalDataCount);
				devicesDataQuality.setUncertainCount(deviceUncertainDataCount);
				devicesDataQuality.setErrorCount(deviceErrorDataCount);
				res.add(new DevicesDataQuality(devicesDataQuality));
			}
			return res;
		}

//	/**
//	 * 获取某个设备的各项参数的数据质量情况
//	 * 考虑新建一个表-新建数据结构时在表中加记录   id，参数名，0，正常个数  |  id，参数名，1，可疑个数  |  id，参数名，2，异常个数
//	 */
//	/**
//	 * 根据deviceId查询当前设备各参数数据状况
//	 */
//	@RequiresPermissions("dataservice:deviceData:view")
//	@RequestMapping(value = {"getParamConditionByDeviceId", ""})
//	@ResponseBody
//	public List<ParamDataCondition> getParamConditionByDeviceId(String dataDeviceId) {
//		return dataserviceDeviceDataConditionService.getParamConditionByDeviceId(dataDeviceId);
//	}

		/**
		 * 获取3天天气数据
		 */
		@RequiresPermissions("dataservice:deviceData:view")
		@RequestMapping(value = {"getCurrentWeather", ""})
		@ResponseBody
		public List<String> getCurrentWeather() {
			List<String> currentWeather = weatherService.getCurrentWeather();
			return currentWeather;
		}


		/**
		 * 获取未来24小时数据
		 */
		@RequiresPermissions("dataservice:deviceData:view")
		@RequestMapping(value = {"getFuture24hoursWeather", ""})
		@ResponseBody
		public List<WeatherService.DateTemp> getFuture24hoursWeather() {
			List<WeatherService.DateTemp> future24hoursWeather = weatherService.getFuture24hoursWeather();
			return future24hoursWeather;
		}


		/**
		 * 获取预警数据
		 */
		@RequiresPermissions("dataservice:deviceData:view")
		@RequestMapping(value = {"getWarning", ""})
		@ResponseBody
		public WeatherService.Alert getWarning() {
			WeatherService.Alert warning = weatherService.getWarning();
			return warning;
		}


//	@RequestMapping(value = "uploadExcel")
//	@ResponseBody
//	public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) {
//		try {
//			System.out.println(123);
//			return null;
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("分析失败：" + e.getMessage());
//		}
//	}

	/**
	 * 上传Excel文件
	 */
//	@PostMapping(value = "uploadExcel")
//	@RequiresPermissions("dataservice:deviceData:view")
//	@ResponseBody
//	public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) {
//		try {
//			// 检查文件是否为空
//			if (file == null || file.isEmpty()) {
//				return ResponseEntity.badRequest().body("请选择要上传的文件");
//			}
//
//			// 检查文件类型
//			String fileName = file.getOriginalFilename();
//			if (fileName == null || !(fileName.endsWith(".xlsx") || fileName.endsWith(".xls"))) {
//				return ResponseEntity.badRequest().body("只支持.xlsx或.xls格式的Excel文件");
//			}
//
//			// 检查文件大小（10MB）
//			if (file.getSize() > 10 * 1024 * 1024) {
//				return ResponseEntity.badRequest().body("文件大小不能超过10MB");
//			}
//
//			// 生成唯一文件名
//			String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
//
//			// 保存文件（这里需要根据实际情况修改保存路径）
//			String uploadDir = "D:/upload/";
//			File dir = new File(uploadDir);
//			if (!dir.exists()) {
//				dir.mkdirs();
//			}
//
//			File dest = new File(uploadDir + newFileName);
//			file.transferTo(dest);
//
//			// 返回成功信息
//			return ResponseEntity.ok().body("文件上传成功：" + fileName);
//
//		} catch (IOException e) {
//			logger.error("文件上传失败", e);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("文件上传失败：" + e.getMessage());
//		}
//	}

	/**
	 * 测试接口
	 */
	@GetMapping(value = "test")
	@RequiresPermissions("dataservice:deviceData:view")
	@ResponseBody
	public ResponseEntity<?> test() {
		return ResponseEntity.ok().body("测试成功");
	}

}