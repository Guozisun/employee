package com.sun.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.dao.UserDao;
import com.sun.pojo.User;

@WebServlet("/user")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 通过隐藏域判断是进入登录页面还是回显数据
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=utf-8");
		String type = request.getParameter("type");
		// 通过url进入判断type
		if (type == null) {
			showLogin(request, response);
		} else if ("login".equals(type)) {
			dologin(request, response);

		}else if ("loginout".equals(type)) {
			loginout(request, response);

		}else if ("risgter".equals(type)) {
			risgter(request, response);

		}else if ("zhuce".equals(type)) {
			zhuce(request, response);

		}

	}

	protected void showLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    Cookie []cookies =  request.getCookies();
	    String name="";
	    if (cookies!=null) {
	    for(int i=0;i<cookies.length;i++) {
	    	if ("user".equals(cookies[i].getName())) {
				name=cookies[i].getValue();
			}
	    }
	    
	}
	    request.setAttribute("abc", name);
		request.getRequestDispatcher("WEB-INF/main/login.jsp").forward(request, response);
	}
	protected void dologin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("username");
	/*	name =new String(name.getBytes("ISO8859-1"), "UTF-8");*/
		String pwd = request.getParameter("password");
		
		UserDao userDao = new UserDao();
		boolean flag = userDao.selcet1(name, pwd);
		String ckeckCode= request.getParameter("check");
		String checkCode1 = (String) request.getSession().getAttribute("checkcode");
		if (ckeckCode.equals(checkCode1)) {
		if(flag) {
			request.getSession().setAttribute("user", name);
			Cookie  cookie = new Cookie("user", name);
			cookie.setMaxAge(20*60);
			response.addCookie(cookie);
			response.sendRedirect("index");
		}else {
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("WEB-INF/main/login.jsp").forward(request, response);
		}
		}else {
			request.setAttribute("abc", name);
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("WEB-INF/main/login.jsp").forward(request, response);

		}
		

	}
	protected void zhuce(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		String pwd2 =request.getParameter("password2");
		if (!pwd.equals(pwd2)) {
			request.setAttribute("msg", "两次密码输入不一致，请重新输入");
			request.getRequestDispatcher("WEB-INF/main/resiter.jsp").forward(request, response);
			return;
		}
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUsername(name);
		user.setPassword(pwd);
		boolean flag=userDao.insert(user);
		if(flag) {
			request.getSession().setAttribute("user", name);
			Cookie  cookie = new Cookie("user", name);
			cookie.setMaxAge(20*60);
			response.addCookie(cookie);
			response.sendRedirect("user");
			return;
			
		}else {
			request.setAttribute("msg", "用户名重复，请重新输入");
			request.getRequestDispatcher("WEB-INF/main/resiter.jsp").forward(request, response);
		}
		
		

	}
	protected void loginout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			request.getSession().invalidate();
			response.sendRedirect("user");
			
	}protected void risgter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		request.getRequestDispatcher("WEB-INF/main/resiter.jsp").forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
