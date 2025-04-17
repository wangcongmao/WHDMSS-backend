package com.jeesite.modules.dataservice.web;

import com.alibaba.excel.EasyExcel;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dataservice.entity.DataserviceTidalForecast;
import com.jeesite.modules.dataservice.entity.support.TidalData;
import com.jeesite.modules.dataservice.entity.support.WaveData;
import com.jeesite.modules.dataservice.service.DataserviceTidalForecastService;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.modules.dataservice.service.support.TidalDataListener;
import com.jeesite.modules.dataservice.service.support.WaveDataListener;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
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
			TidalDataListener listener = new TidalDataListener();

			EasyExcel.read(inputStream, TidalData.class, listener)
					.sheet()
					.doRead();

			List<TidalData> tidalDataList = listener.getDataList();

			// 计算理论功率 + 效率
			double totalEfficiency = 0;
			int validCount = 0;

			for (TidalData data : tidalDataList ) {
				if (data.getP() == null || data.getPflux() == null || data.getV() == null || data.getActualPower() == null) {
					continue; // 跳过空行或无效行
				}
				double pTheory = 0.5 * data.getP() * data.getV() * data.getV() * data.getV(); // kW/m
				pTheorys.add(pTheory+"");

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
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:tidalForecast:view")
	@RequestMapping(value = "form")
	public String form(DataserviceTidalForecast dataserviceTidalForecast, Model model) {
		model.addAttribute("dataserviceTidalForecast", dataserviceTidalForecast);
		return "modules/dataservice/dataserviceTidalForecastForm";
	}
}