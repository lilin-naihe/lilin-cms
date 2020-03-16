
package com.lilin.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: MyInterceptor 
 * @Description: 个人中心拦截器
 * @author: asus
 * @date: 2020年3月13日 上午8:40:27
 */
public class MyInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object object = session.getAttribute("user");
        if(null!=object)
        	return true;
            request.setAttribute("msg", "请登录重试");
        	request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		return false;
	}
	
	
	
	
	
	
}
