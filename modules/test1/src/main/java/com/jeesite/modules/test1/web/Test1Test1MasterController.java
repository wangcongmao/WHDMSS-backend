package com.jeesite.modules.test1.web;

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
import com.jeesite.modules.test1.entity.Test1Test1Master;
import com.jeesite.modules.test1.service.Test1Test1MasterService;

/**
 * test1_masterController
 * @author wangcm
 * @version 2024-12-27
 */
@Controller
@RequestMapping(value = "${adminPath}/test1/test1Master")
public class Test1Test1MasterController extends BaseController {

	@Autowired
	private Test1Test1MasterService test1Test1MasterService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Test1Test1Master get(String id, boolean isNewRecord) {
		return test1Test1MasterService.get(id, isNewRecord);
	}

	/**
	 * 管理主页
	 */
	@RequiresPermissions("test1:test1Master:view")
	@RequestMapping(value = "index")
	public String index(Test1Test1Master test1Test1Master, Model model) {
		model.addAttribute("test1Test1Master", test1Test1Master);
		return "modules/test1/test1Test1MasterIndex";
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test1:test1Master:view")
	@RequestMapping(value = {"list", ""})
	public String list(Test1Test1Master test1Test1Master, Model model) {
		model.addAttribute("test1Test1Master", test1Test1Master);
		return "modules/test1/test1Test1MasterList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test1:test1Master:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<Test1Test1Master> listData(Test1Test1Master test1Test1Master) {
		if (StringUtils.isBlank(test1Test1Master.getParentCode())) {
			test1Test1Master.setParentCode(Test1Test1Master.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(test1Test1Master.getOfficeCode())){
			test1Test1Master.setParentCode(null);
		}
		if (StringUtils.isNotBlank(test1Test1Master.getOfficeName())){
			test1Test1Master.setParentCode(null);
		}
		if (StringUtils.isNotBlank(test1Test1Master.getRemarks())){
			test1Test1Master.setParentCode(null);
		}
		List<Test1Test1Master> list = test1Test1MasterService.findList(test1Test1Master);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test1:test1Master:view")
	@RequestMapping(value = "form")
	public String form(Test1Test1Master test1Test1Master, Model model) {
		// 创建并初始化下一个节点信息
		test1Test1Master = createNextNode(test1Test1Master);
		model.addAttribute("test1Test1Master", test1Test1Master);
		return "modules/test1/test1Test1MasterForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("test1:test1Master:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public Test1Test1Master createNextNode(Test1Test1Master test1Test1Master) {
		if (StringUtils.isNotBlank(test1Test1Master.getParentCode())){
			test1Test1Master.setParent(test1Test1MasterService.get(test1Test1Master.getParentCode()));
		}
		if (test1Test1Master.getIsNewRecord()) {
			Test1Test1Master where = new Test1Test1Master();
			where.setParentCode(test1Test1Master.getParentCode());
			Test1Test1Master last = test1Test1MasterService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				test1Test1Master.setTreeSort(last.getTreeSort() + 30);
			}
		}
		// 以下设置表单默认数据
		if (test1Test1Master.getTreeSort() == null){
			test1Test1Master.setTreeSort(Test1Test1Master.DEFAULT_TREE_SORT);
		}
		return test1Test1Master;
	}

	/**
	 * 保存数据
	 */
	@RequiresPermissions("test1:test1Master:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Test1Test1Master test1Test1Master) {
		test1Test1MasterService.save(test1Test1Master);
		return renderResult(Global.TRUE, text("保存测试成功！"));
	}
	
	/**
	 * 删除数据
	 */
	@RequiresPermissions("test1:test1Master:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Test1Test1Master test1Test1Master) {
		test1Test1MasterService.delete(test1Test1Master);
		return renderResult(Global.TRUE, text("删除测试成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param parentCode 设置父级编码返回一级
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("test1:test1Master:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String parentCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		Test1Test1Master where = new Test1Test1Master();
		where.setStatus(Test1Test1Master.STATUS_NORMAL);
		if (StringUtils.isNotBlank(parentCode)){
			where.setParentCode(parentCode);
		}
		List<Test1Test1Master> list = test1Test1MasterService.findList(where);
		for (int i=0; i<list.size(); i++){
			Test1Test1Master e = list.get(i);
			// 过滤非正常的数据
			if (!Test1Test1Master.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
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
			map.put("name", e.getOfficeName());
			map.put("isParent", !e.getIsTreeLeaf());
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("test1:test1Master:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(Test1Test1Master test1Test1Master){
		if (!test1Test1Master.currentUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		test1Test1MasterService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}