package jt56.comm.system.controller;

import javax.servlet.http.HttpSession;

import jt56.comm.system.service.InitServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 初始化控制器
 * @author zhouq 
 * @create 2014-6-16 下午3:36:44
 */
@Controller
@RequestMapping("/initController")
public class InitController {

	@Autowired
	private InitServiceI initService;

	/**
	 * 转向到首页
	 * 
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		initService.init();
		return "redirect:/";
	}
	
	/**
	 * 跳转到主页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "/index";
	}
	
	/**
	 * 跳转到主页
	 * 
	 * @return
	 */
	@RequestMapping("/layout/north")
	public String layout() {
		return "/index";
	}

}
