package com.jeesite.modules.dataservice.web;

import com.alibaba.excel.EasyExcel;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dataservice.entity.DataserviceTidalForecast;
import com.jeesite.modules.dataservice.entity.support.WaveData;
import com.jeesite.modules.dataservice.service.DataserviceTidalForecastService;
import com.jeesite.modules.dataservice.service.support.WaveDataListener;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 潮流预报存储表Controller
 *
 * @author wangcm
 * @version 2025-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/wavesEnergy")
public class DataserviceWavesEnergyController extends BaseController {

	@Autowired
	private DataserviceTidalForecastService tidalForecastService;

	@Autowired
	private StandardServletMultipartResolver multipartResolver;


//	@PostMapping(value = "uploadExcel")
//	@RequiresPermissions("dataservice:deviceData:view")
//	@ResponseBody
//	public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) {
//		try {
//			System.out.println(123);
//			return null;
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("分析失败：" + e.getMessage());
//		}
//	}

	@PostMapping("/uploadExcel")
	@ResponseBody
	public ResponseEntity<List<String>> uploadExcel(
			@RequestParam("file") MultipartFile file,
			@RequestParam("experiment") String experiment,
			@RequestParam("deviceName") String deviceName) {
		try {
			// 打印接收到的参数
			System.out.println("文件名: " + file.getOriginalFilename());
			System.out.println("文件大小: " + file.getSize());
			System.out.println("文件类型: " + file.getContentType());
			System.out.println("试验: " + experiment);
			System.out.println("装置名称: " + deviceName);

			List<String> pTheorys = new ArrayList<>();

			InputStream inputStream = file.getInputStream();
			WaveDataListener listener = new WaveDataListener();

			EasyExcel.read(inputStream, WaveData.class, listener)
					.sheet()
					.doRead();

			List<WaveData> waveDataList = listener.getDataList();

			// 计算理论功率 + 效率
			double totalEfficiency = 0;
			int validCount = 0;

			for (WaveData data : waveDataList) {
				if (data.getHs() == null || data.getTe() == null || data.getActualPower() == null) {
					continue; // 跳过空行或无效行
				}
				double pTheory = 0.49 * Math.pow(data.getHs(), 2) * data.getTe(); // kW/m
				pTheorys.add(pTheory+"");
//				double efficiency = data.getActualPower() / pTheory;
//				totalEfficiency += efficiency;
				validCount++;
			}

			double avgEfficiency = validCount > 0 ? totalEfficiency / validCount : 0;

			return ResponseEntity.ok(pTheorys);



			// 这里可以添加文件处理逻辑
			// 例如：保存文件、解析Excel等


		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ArrayList<>());
		}
	}

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