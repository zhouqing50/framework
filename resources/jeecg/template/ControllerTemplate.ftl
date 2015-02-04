package ${bussPackage}.${entityPackage}.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jt56.comm.system.pageModel.DataGrid;
import jt56.comm.system.pageModel.Json;
import jt56.comm.system.pageModel.PageHelper;
import jt56.comm.system.pageModel.SessionInfo;
import jt56.comm.system.util.ConfigUtil;
import jt56.comm.system.util.IpUtil;
import jt56.comm.system.controller.BaseController;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
 
import ${bussPackage}.${entityPackage}.entity.${className};
import ${bussPackage}.${entityPackage}.service.${className}ServiceI;
 
/**
 * 
 * <br>
 * <b>功能：</b>${className}Controller<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */ 
@Controller
@RequestMapping("/${lowerName}Controller") 
public class ${className}Controller  extends BaseController {
	


	@Autowired
	private ${className}ServiceI ${lowerName}Service; 
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	 
	 @RequestMapping("/manager")
	public String manager() {
		return "${bussPackage}/${entityPackage}/${lowerName}";
	}
	
	
	/**
	 * 获取用户数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(${className} entity, PageHelper ph) {
		return ${lowerName}Service.dataGrid(entity, ph);
	}
	
	
	
	/**
	 * 跳转到新增页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		String id = UUID.randomUUID().toString();
		request.setAttribute("id", id);
		return "${bussPackage}/${entityPackage}/${lowerName}Add";
	}

	/**
	 * 新增保存
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(${className} entity) {
		Json j = new Json();
		try {
			${lowerName}Service.add(entity);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(entity);
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		${className} entity = ${lowerName}Service.get(id);
		request.setAttribute("entity", entity);
		return "${bussPackage}/${entityPackage}/${lowerName}Edit";
	}

	/**
	 * 修改保存
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(${className} entity) {
		Json j = new Json();
		try {
			${lowerName}Service.update(entity);
			j.setSuccess(true);
			j.setMsg("修改成功！");
			j.setObj(entity);
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		${lowerName}Service.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
}
