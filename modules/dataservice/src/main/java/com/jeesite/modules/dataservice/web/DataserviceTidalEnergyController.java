package com.jeesite.modules.dataservice.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dataservice.entity.DataserviceTidalForecast;
import com.jeesite.modules.dataservice.service.DataserviceTidalForecastService;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.common.utils.excel.ExcelExport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 潮流预报存储表Controller
 *
 * @author wangcm
 * @version 2025-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/tidalEnergy")
public class DataserviceTidalEnergyController extends BaseController {

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
	 * 导出Excel数据
	 */
	@GetMapping("/exportExcel")
	public void exportExcel(DataserviceTidalForecast tidalForecast, HttpServletResponse response) {
		try {
			List<DataserviceTidalForecast> list = tidalForecastService.findList(tidalForecast);

			String fileName = "潮流预报数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			ExcelExport ee = new ExcelExport("潮流预报数据", DataserviceTidalForecast.class);

			ee.setDataList(list).write(response, fileName).close();

		} catch (Exception e) {
			logger.error("导出Excel失败", e);
		}
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
}