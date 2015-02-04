package jt56.comm.system.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import jt56.comm.system.annotation.Auth;
import jt56.comm.system.model.Tlog;
import jt56.comm.system.pageModel.DataGrid;
import jt56.comm.system.pageModel.Json;
import jt56.comm.system.pageModel.PageHelper;
import jt56.comm.system.service.TlogServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
/**
 * 
 * <br>
 * <b>功能：</b>TlogController<br>
 * <b>作者：</b>zhouq<br>
 * <b>版权所有：<b>版权所有(C) 2013，www.jt56.org<br>
 */ 
@Controller
@RequestMapping("/tlogController") 
public class TlogController  extends BaseController {
	


	@Autowired
	private TlogServiceI tlogService; 
	
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	 @RequestMapping("/manager")
	public String manager() {
		return "/system/log/tlog";
	}
	
	
	/**
	 * 获取用户数据表格
	 * 
	 * @param user
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(Tlog entity, PageHelper ph) {
		return tlogService.dataGrid(entity, ph);
	}
	
	
	
	/**
	 * 跳转到新增页面
	 * 
	 * @param request
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		String id = UUID.randomUUID().toString();
		request.setAttribute("id", id);
		return "/system/log/tlogAdd";
	}

	/**
	 * 新增保存
	 * 
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Tlog entity) {
		Json j = new Json();
		try {
			tlogService.add(entity);
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
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		Tlog entity = tlogService.get(id);
		request.setAttribute("entity", entity);
		return "/system/log/tlogEdit";
	}

	/**
	 * 修改保存
	 * 
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Tlog entity) {
		Json j = new Json();
		try {
			tlogService.update(entity);
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
	@Auth(verifyLogin=false,verifyURL=false)
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		tlogService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
}
