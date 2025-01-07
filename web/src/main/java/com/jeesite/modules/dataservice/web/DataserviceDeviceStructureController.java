//package com.jeesite.modules.dataservice.web;
//
//import java.util.Arrays;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.jeesite.common.config.Global;
//import com.jeesite.common.entity.Page;
//import com.jeesite.common.web.BaseController;
//import com.jeesite.modules.dataservice.entity.DataserviceDeviceStructure;
//import com.jeesite.modules.dataservice.service.DataserviceDeviceStructureService;
//
///**
// * deviceStructureController
// * @author wangcm
// * @version 2025-01-06
// */
//// todo 前段界面每次点击新增按钮表单重置
//@Controller
//@RequestMapping(value = "${adminPath}/dataservice/deviceStructure")
//public class DataserviceDeviceStructureController extends BaseController {
//
//	@Autowired
//	private DataserviceDeviceStructureService dataserviceDeviceStructureService;
//
//	/**
//	 * 获取数据
//	 */
//	@ModelAttribute
//	public DataserviceDeviceStructure get(String id, boolean isNewRecord) {
//		return dataserviceDeviceStructureService.get(id, isNewRecord);
//	}
//
//	/**
//	 * 查询列表
//	 */
//	@RequiresPermissions("dataservice:deviceStructure:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(DataserviceDeviceStructure dataserviceDeviceStructure, Model model) {
//		model.addAttribute("dataserviceDeviceStructure", dataserviceDeviceStructure);
//		return "modules/dataservice/dataserviceDeviceStructureList";
//	}
//
//	/**
//	 * 查询列表数据
//	 */
//	@RequiresPermissions("dataservice:deviceStructure:view")
//	@RequestMapping(value = "listData")
//	@ResponseBody
//	public Page<DataserviceDeviceStructure> listData(DataserviceDeviceStructure dataserviceDeviceStructure, HttpServletRequest request, HttpServletResponse response) {
//		dataserviceDeviceStructure.setPage(new Page<>(request, response));
//		Page<DataserviceDeviceStructure> page = dataserviceDeviceStructureService.findPage(dataserviceDeviceStructure);
//		List<DataserviceDeviceStructure> list = page.getList();
//		for (int i = 0; i < list.size(); i++) {
//			DataserviceDeviceStructure dataserviceDeviceStructure1 = list.get(i);
//			String structureData = dataserviceDeviceStructure1.getStructureData();
//			String[] strings = structureData.split("prm");
//			for (int i1 = 1; i1 < strings.length; i1++) {
//				// ":"a","type":"number"},{"
//				String[] strings1 = strings[i1].split("type");
//				// ":"a","
//				String substring1 = strings1[0].substring(3, strings1[0].lastIndexOf(",")-1);
//				// ":"number"},{"
//				String substring2 = strings1[1].substring(3, strings1[1].lastIndexOf("}") - 1);
//				strings[i1] = substring1 + ":" + substring2;
//				if (i1 != strings.length-1) {
//					strings[i1] += " | ";
//				}
//			}
//			String a = "";
//			for (int i1 = 1; i1 < strings.length; i1++) {
//				a += strings[i1];
//			}
//			dataserviceDeviceStructure1.setStructureData(a.substring(0, a.lastIndexOf("\"")));
//		}
//		return page;
//	}
//
//	/**
//	 * 查询列表数据
//	 */
//	@RequiresPermissions("dataservice:deviceStructure:view")
//	@RequestMapping(value = "listDataForTable")
//	@ResponseBody
//	public Page<DataserviceDeviceStructure> listDataForTable(DataserviceDeviceStructure dataserviceDeviceStructure, HttpServletRequest request, HttpServletResponse response) {
//		dataserviceDeviceStructure.setPage(new Page<>(request, response));
//		Page<DataserviceDeviceStructure> page = dataserviceDeviceStructureService.findPage(dataserviceDeviceStructure);
//		List<DataserviceDeviceStructure> list = page.getList();
//		for (int i = 0; i < list.size(); i++) {
//			DataserviceDeviceStructure dataserviceDeviceStructure1 = list.get(i);
//			String structureData = dataserviceDeviceStructure1.getStructureData();
//			String[] strings = structureData.split("prm");
//			for (int i1 = 1; i1 < strings.length; i1++) {
//				// ":"a","type":"number"},{"
//				String[] strings1 = strings[i1].split("type");
//				// ":"a","
//				String substring1 = strings1[0].substring(3, strings1[0].lastIndexOf(",")-1);
//				// ":"number"},{"
//				String substring2 = strings1[1].substring(3, strings1[1].lastIndexOf("}") - 1);
//				strings[i1] = substring1 + ":" + substring2;
//				if (i1 != strings.length-1) {
//					strings[i1] += " | ";
//				}
//			}
//			String a = new String(Arrays.toString(strings));
//			dataserviceDeviceStructure1.setStructureData(a);
//		}
//
//		System.out.println(0);
//		return page;
//	}
//
//	/**
//	 * 查看编辑表单
//	 */
//	@RequiresPermissions("dataservice:deviceStructure:view")
//	@RequestMapping(value = "form")
//	public String form(DataserviceDeviceStructure dataserviceDeviceStructure, Model model) {
//		model.addAttribute("dataserviceDeviceStructure", dataserviceDeviceStructure);
//		return "modules/dataservice/dataserviceDeviceStructureForm";
//	}
//
//	/**
//	 * 保存数据
//	 */
//	@RequiresPermissions("dataservice:deviceStructure:edit")
//	@PostMapping(value = "save")
//	@ResponseBody
//	public String save(@Validated DataserviceDeviceStructure dataserviceDeviceStructure) {
//		dataserviceDeviceStructureService.save(dataserviceDeviceStructure);
//		return renderResult(Global.TRUE, text("保存设备数据结构成功！"));
//	}
//
//	/**
//	 * 删除数据
//	 */
//	@RequiresPermissions("dataservice:deviceStructure:edit")
//	@RequestMapping(value = "delete")
//	@ResponseBody
//	public String delete(DataserviceDeviceStructure dataserviceDeviceStructure) {
//		dataserviceDeviceStructureService.delete(dataserviceDeviceStructure);
//		return renderResult(Global.TRUE, text("删除设备数据结构成功！"));
//	}
//
//}