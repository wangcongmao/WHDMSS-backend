/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.dataservice.web;

import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.entity.Log;
import com.jeesite.modules.sys.service.LogService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志Controller
 * @author ThinkGem
 * @version 2013-6-2
 */
@Controller
@Api(tags = "Log - 访问日志")
@RequestMapping(value = "${adminPath}/dateservice/log")
@ConditionalOnProperty(name={"user.enabled","web.core.enabled"}, havingValue="true", matchIfMissing=true)
public class DataserviceLogController extends BaseController {

	@Autowired
	private LogService logService;
	
	/**
     * 获取数据
     */
    @ModelAttribute
    public Log get(String id, boolean isNewRecord) {
        return logService.get(id, isNewRecord);
    }
    
	/**
     * 查询列表
     */
    @RequiresPermissions("sys:log:view")
    @RequestMapping(value = "list")
    public String list(Log log, Model model) {
//        // 设置默认时间范围，默认当前月
//        if (log.getCreateDate_gte() == null){
//            log.setCreateDate_gte(DateUtils.setDays(new Date(), 1));
//        }
//        if (log.getCreateDate_lte() == null){
//            log.setCreateDate_lte(DateUtils.addDays(DateUtils.addMonths(log.getCreateDate_gte(), 1), -1));
//        }
        model.addAttribute("log", log);
        return "modules/sys/logList";
    }
    
    /**
     * 查询列表数据
     */
    @RequiresPermissions("sys:log:view")
    @RequestMapping(value = "listData")
    @ResponseBody
    public Page<Log> listData(Log log, HttpServletRequest request, HttpServletResponse response) {

        // todo 删除
        String requestUri = log.getRequestUri();
        if (requestUri.equals("")) {
            requestUri = "/js/a/dataservice" + requestUri;
            log.setRequestUri(requestUri);
        } else {
            if (!requestUri.contains("/js/a/dataservice")) {

                log.setRequestUri("/js/a/dataservice%"+requestUri);
                log.setPage(new Page<>(request, response));
                Page<Log> page = logService.findPage(log);
//                List<Log> list1 = logService.findList(log);
//                List<Log> collect2 = list1.stream().filter(log1 -> log1.getRequestUri().contains("finalRequestUri")).collect(Collectors.toList());
//
//
//                // 获取当前页的日志列表 todo 删除
//                String finalRequestUri = requestUri;
//
//                List<Log> list = page.getList();
//                List<Log> collect = list.stream().filter(log1 -> log1.getRequestUri().startsWith("/js/a/dataservice")).collect(Collectors.toList());
//                List<Log> collect1 = collect.stream().filter(log1 -> log1.getRequestUri().contains("finalRequestUri")).collect(Collectors.toList());
//
//
//                List<Log> filteredLogs = page.getList().stream()
//                        // 过滤符合条件的日志
//                        .filter(log1 -> log1.getRequestUri() != null &&
//                                log1.getRequestUri().startsWith("/js/a/dataservice") &&
//                                log1.getRequestUri().contains(finalRequestUri))
//                        .collect(Collectors.toList());
//
//                // 设置过滤后的日志列表
//                page.setList(filteredLogs);
                return page;
            }
        }

        log.setPage(new Page<>(request, response));
        Page<Log> page = logService.findPage(log);
        return page;
    }
    
    /**
     * 查看编辑表单
     */
    @RequiresPermissions("sys:log:view")
    @RequestMapping(value = "form")
    public String form(Log log, Model model) {
        model.addAttribute("log", log);
        return "modules/sys/logForm";
    }
	
}
