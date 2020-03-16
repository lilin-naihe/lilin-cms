package com.lilin.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lilin.cms.domain.User;
import com.lilin.cms.service.UserService;
import com.lilin.cms.util.CMSException;
import com.lilin.cms.util.Result;

@RequestMapping("passport")
@Controller
public class PassportController {

	@Resource
	private UserService service;
	@GetMapping("reg")
	public String reg(){
		
		return "passport/reg";
	}
	
	@PostMapping("reg")
	@ResponseBody
	public Result<User> reg(User user,Model model){
		Result<User> result=new Result<User>();
		try {
			if(service.insert(user)>0){
				result.setCode(200);
				result.setMsg("注册成功");
			}
		} catch (CMSException e) {
			e.printStackTrace();
			result.setCode(300);
			result.setMsg("注册失败:"+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(500);
			result.setMsg("注册失败,请联系管理员");
		}
		return result;
	}
	
	@GetMapping("login")
	public String login(){
		return "passport/login";
	}
	
	@GetMapping("admin/login")
	public String adminLogin(){
		return "passport/adminLogin";
	}
	
	@PostMapping("login")
	@ResponseBody
	public Result<User> login(User formUser,Model model,HttpSession session){
		Result<User> result=new Result<User>(); 
		try {
			User user = service.login(formUser);
			if(null!=user){
				result.setCode(200);
				result.setMsg("登录成功");
				if(user.getRole()==0){
					session.setAttribute("user", user);
				}else{
					session.setAttribute("admin", user);
				}
			}
		} catch (CMSException e) {
			e.printStackTrace();
			result.setCode(300);
			result.setMsg("登录失败"+e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(500);
			result.setMsg("登录异常,请联系管理员");
		}
		return result;
	}
	/**
	 * 注销
	 */
	@GetMapping("logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
	/**
	 * 检查用户是否存在
	 */
	@ResponseBody
	@PostMapping("checkName")
	public boolean checkName(String username){
		return service.selectByUsername(username)==null;
	}
	
}
