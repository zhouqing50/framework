package jt56.comm.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jt56.comm.system.model.DicType;
import jt56.comm.system.pageModel.Json;
import jt56.comm.system.pageModel.SessionInfo;
import jt56.comm.system.pageModel.Tree;
import jt56.comm.system.service.DicTypeServiceI;
import jt56.comm.system.service.InitServiceI;
import jt56.comm.system.service.ResourceServiceI;
import jt56.comm.system.util.ConfigUtil;
import jt56.comm.system.util.StringUtil;
import jt56.comm.system.util.SysUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





/**
 * 数据字典控制器
 * @author zhouq 
 * @create 2014-6-16 下午3:37:44
 */
@Controller
@RequestMapping("/dicController")
public class DicTypeController extends BaseController {

	@Autowired
	private ResourceServiceI resourceService;
	
	@Autowired
	private DicTypeServiceI dicTypeService;
	
	@Autowired
	private InitServiceI initService;

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
	public List<DicType> tree(HttpSession session) {
		return dicTypeService.tree();
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
		return "/system/dictype/dicType";
	}

	/**
	 * 跳转到资源添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		String uid = UUID.randomUUID().toString();
		request.setAttribute("uid", uid);
		return "/system/dictype/dicTypeAdd";
	}

	/**
	 * 添加资源
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(DicType dic, HttpSession session) {
		Json j = new Json();
		if (StringUtil.isBlank(dic.getPid())) {
			dic.setPid("no");//如果为空，则给默认值'no'
		}
		dicTypeService.add(dic);
		//刷新缓存中数据字典
		FlushSysDicThread smsThread = new FlushSysDicThread(initService);
		Thread thread = new Thread(smsThread);
		thread.start();
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
	public String editPage(HttpServletRequest request, String id, String text) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("text", text);
		DicType dic  = dicTypeService.getDicTypeByPar(map);
		request.setAttribute("dicType", dic);
		return "/system/dictype/dicTypeEdit";
	}

	/**
	 * 编辑资源
	 * 
	 * @param resource
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(DicType dic) {
		Json j = new Json();
		try {
			if (StringUtil.isBlank(dic.getPid())) {
				dic.setPid("no");//如果为空，则给默认值'no'
			}
			dicTypeService.update(dic);
			//刷新缓存中数据字典
			FlushSysDicThread smsThread = new FlushSysDicThread(initService);
			Thread thread = new Thread(smsThread);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("编辑失败！");
		}
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
	public List<DicType> treeGrid(HttpSession session) {
		return dicTypeService.treeGrid();
	}

	/**
	 * 删除资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id, String text) {
		Json j = new Json();
		//如果parentId为空，则包含子字典项，需要一起删除	
		dicTypeService.delete(id, text);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 通过父数据字典的CODE获取子数据项
	 * @author zhouq
	 * @create 2014-6-10 上午10:32:54
	 * @param code
	 * @param response
	 * @param request
	 * @return 
	 */
	@RequestMapping("/getDicTypeById")
	@ResponseBody
	public List<DicType> getDicTypeByCode(String id,HttpServletRequest request) {
		return SysUtil.dicMap.get(id);
	}

}
