package com.jeesite.modules.dataservice.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jeesite.modules.dataservice.entity.DataserviceRealData;
import com.jeesite.modules.dataservice.service.DataserviceRealDataService;

/**
 * 实测数据Controller
 * @author wangcm
 * @version 2025-02-25
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/realData")
public class DataserviceRealDataController extends BaseController {

	@Autowired
	private DataserviceRealDataService dataserviceRealDataService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataserviceRealData get(String id, boolean isNewRecord) {
		return dataserviceRealDataService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:realData:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataserviceRealData dataserviceRealData, Model model) {
		model.addAttribute("dataserviceRealData", dataserviceRealData);
		return "modules/dataservice/dataserviceRealDataList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:realData:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<DataserviceRealData> listData(DataserviceRealData dataserviceRealData, HttpServletRequest request, HttpServletResponse response) {
		dataserviceRealData.setPage(new Page<>(request, response));
		Page<DataserviceRealData> page = dataserviceRealDataService.findPage(dataserviceRealData);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:realData:view")
	@RequestMapping(value = "form")
	public String form(DataserviceRealData dataserviceRealData, Model model) {
		model.addAttribute("dataserviceRealData", dataserviceRealData);
		return "modules/dataservice/dataserviceRealDataForm";
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:realData:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataserviceRealData dataserviceRealData) {
		dataserviceRealDataService.save(dataserviceRealData);
		return renderResult(Global.TRUE, text("保存实测数据成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:realData:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataserviceRealData dataserviceRealData) {
		dataserviceRealDataService.delete(dataserviceRealData);
		return renderResult(Global.TRUE, text("删除实测数据成功！"));
	}
	
}