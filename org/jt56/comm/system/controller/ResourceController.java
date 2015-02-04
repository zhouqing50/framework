package jt56.comm.system.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jt56.comm.system.model.DicType;
import jt56.comm.system.model.Tresource;
import jt56.comm.system.pageModel.Json;
import jt56.comm.system.pageModel.Resource;
import jt56.comm.system.pageModel.SessionInfo;
import jt56.comm.system.pageModel.Tree;
import jt56.comm.system.service.DicTypeServiceI;
import jt56.comm.system.service.ResourceServiceI;
import jt56.comm.system.util.ConfigUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 资源控制器
 * @author zhouq 
 * @create 2014-6-16 下午3:37:25
 */
@Controller
@RequestMapping("/resourceController")
public class ResourceController extends BaseController {

	@Autowired
	private ResourceServiceI resourceService;
	
	@Autowired
	private DicTypeServiceI dicTypeService;

	/**
	 * 获得资源树(资源类型为菜单类型)
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return resourceService.tree(sessionInfo);
	}

	/**
	 * 获得资源树(包括所有资源类型)
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return resourceService.allTree(sessionInfo);
	}

	/**
	 * 跳转到资源管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/system/resource/resource";
	}

	/**
	 * 跳转到资源添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		List<DicType> resourceTypeList = dicTypeService.getDicTypeListByPid("zylx");
		request.setAttribute("resourceTypeList", resourceTypeList);
		Resource r = new Resource();
		r.setId(UUID.randomUUID().toString());
		request.setAttribute("resource", r);
		return "/system/resource/resourceAdd";
	}

	/**
	 * 添加资源
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Resource resource, HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Json j = new Json();
		resourceService.add(resource, sessionInfo);
		j.setSuccess(true);
		j.setMsg("添加成功！");
		return j;
	}

	/**
	 * 跳转到资源编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		List<DicType> resourceTypeList = dicTypeService.getDicTypeListByPid("zylx");
		request.setAttribute("resourceTypeList", resourceTypeList);
		Resource r = resourceService.get(id);
		request.setAttribute("resource", r);
		return "/system/resource/resourceEdit";
	}

	/**
	 * 编辑资源
	 * 
	 * @param resource
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Tresource resource) {
		Json j = new Json();
		resourceService.edit(resource);
		j.setSuccess(true);
		j.setMsg("编辑成功！");
		return j;
	}

	/**
	 * 获得资源列表
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @return
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Resource> treeGrid(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return resourceService.treeGrid(sessionInfo);
	}

	/**
	 * 删除资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		resourceService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
