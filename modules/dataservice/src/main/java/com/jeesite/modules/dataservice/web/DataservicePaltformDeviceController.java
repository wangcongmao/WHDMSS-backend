package com.jeesite.modules.dataservice.web;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

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
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.dataservice.entity.DataservicePaltformDevice;
import com.jeesite.modules.dataservice.service.DataservicePaltformDeviceService;

/**
 * platformDeviceController
 * @author wangcm
 * @version 2024-12-27
 */
@Controller
@RequestMapping(value = "${adminPath}/dataservice/paltformDevice")
public class DataservicePaltformDeviceController extends BaseController {

	@Autowired
	private DataservicePaltformDeviceService dataservicePaltformDeviceService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public DataservicePaltformDevice get(String id, boolean isNewRecord) {
		return dataservicePaltformDeviceService.get(id, isNewRecord);
	}

	/**
	 * 管理主页
	 */
	@RequiresPermissions("dataservice:paltformDevice:view")
	@RequestMapping(value = "index")
	public String index(DataservicePaltformDevice dataservicePaltformDevice, Model model) {
		model.addAttribute("dataservicePaltformDevice", dataservicePaltformDevice);
		return "modules/dataservice/dataservicePaltformDeviceIndex";
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("dataservice:paltformDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(DataservicePaltformDevice dataservicePaltformDevice, Model model) {
		model.addAttribute("dataservicePaltformDevice", dataservicePaltformDevice);
		return "modules/dataservice/dataservicePaltformDeviceList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("dataservice:paltformDevice:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<DataservicePaltformDevice> listData(DataservicePaltformDevice dataservicePaltformDevice) {
		if (StringUtils.isBlank(dataservicePaltformDevice.getParentCode())) {
			dataservicePaltformDevice.setParentCode(DataservicePaltformDevice.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(dataservicePaltformDevice.getDeviceName())){
			dataservicePaltformDevice.setParentCode(null);
		}
		if (StringUtils.isNotBlank(dataservicePaltformDevice.getDeviceType())){
			dataservicePaltformDevice.setParentCode(null);
		}
		if (StringUtils.isNotBlank(dataservicePaltformDevice.getDeviceLongitude())){
			dataservicePaltformDevice.setParentCode(null);
		}
		if (StringUtils.isNotBlank(dataservicePaltformDevice.getDeviceDimension())){
			dataservicePaltformDevice.setParentCode(null);
		}
		if (dataservicePaltformDevice.getDeviceMaketime() != null){
			dataservicePaltformDevice.setParentCode(null);
		}
		if (dataservicePaltformDevice.getDeviceRepairtime() != null){
			dataservicePaltformDevice.setParentCode(null);
		}
		if (StringUtils.isNotBlank(dataservicePaltformDevice.getDeviceMapposition())){
			dataservicePaltformDevice.setParentCode(null);
		}
		List<DataservicePaltformDevice> list = dataservicePaltformDeviceService.findList(dataservicePaltformDevice);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("dataservice:paltformDevice:view")
	@RequestMapping(value = "form")
	public String form(DataservicePaltformDevice dataservicePaltformDevice, Model model) {
		// 创建并初始化下一个节点信息
		dataservicePaltformDevice = createNextNode(dataservicePaltformDevice);
		model.addAttribute("dataservicePaltformDevice", dataservicePaltformDevice);
		return "modules/dataservice/dataservicePaltformDeviceForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("dataservice:paltformDevice:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public DataservicePaltformDevice createNextNode(DataservicePaltformDevice dataservicePaltformDevice) {
		if (StringUtils.isNotBlank(dataservicePaltformDevice.getParentCode())){
			dataservicePaltformDevice.setParent(dataservicePaltformDeviceService.get(dataservicePaltformDevice.getParentCode()));
		}
		if (dataservicePaltformDevice.getIsNewRecord()) {
			DataservicePaltformDevice where = new DataservicePaltformDevice();
			where.setParentCode(dataservicePaltformDevice.getParentCode());
			DataservicePaltformDevice last = dataservicePaltformDeviceService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				dataservicePaltformDevice.setTreeSort(last.getTreeSort() + 30);
			}
		}
		// 以下设置表单默认数据
		if (dataservicePaltformDevice.getTreeSort() == null){
			dataservicePaltformDevice.setTreeSort(DataservicePaltformDevice.DEFAULT_TREE_SORT);
		}
		return dataservicePaltformDevice;
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("dataservice:paltformDevice:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated DataservicePaltformDevice dataservicePaltformDevice) {
		dataservicePaltformDeviceService.save(dataservicePaltformDevice);
		return renderResult(Global.TRUE, text("保存平台与设备成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("dataservice:paltformDevice:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(DataservicePaltformDevice dataservicePaltformDevice) {
		dataservicePaltformDeviceService.delete(dataservicePaltformDevice);
		return renderResult(Global.TRUE, text("删除平台与设备成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param parentCode 设置父级编码返回一级
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("dataservice:paltformDevice:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String parentCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		DataservicePaltformDevice where = new DataservicePaltformDevice();
		where.setStatus(DataservicePaltformDevice.STATUS_NORMAL);
		if (StringUtils.isNotBlank(parentCode)){
			where.setParentCode(parentCode);
		}
		List<DataservicePaltformDevice> list = dataservicePaltformDeviceService.findList(where);
		for (int i=0; i<list.size(); i++){
			DataservicePaltformDevice e = list.get(i);
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", e.getDeviceName());
			map.put("isParent", !e.getIsTreeLeaf());
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("dataservice:paltformDevice:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(DataservicePaltformDevice dataservicePaltformDevice){
		if (!dataservicePaltformDevice.currentUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		dataservicePaltformDeviceService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}

	/**
	 * 获取所有数据
	 */
	@RequiresPermissions("dataservice:paltformDevice:view")
	@RequestMapping(value = {"getAll", ""})
	public List<DataservicePaltformDevice> getAll() {
		List<DataservicePaltformDevice> all = dataservicePaltformDeviceService.getAll();
		return all;
	}
	
}