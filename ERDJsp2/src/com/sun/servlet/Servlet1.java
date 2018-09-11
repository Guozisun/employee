package com.sun.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.dao.UserDao;

@WebServlet("/user")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 通过隐藏域判断是进入登录页面还是回显数据
		String type = request.getParameter("hedden");
		// 通过url进入判断type
		if (type == null) {
			showLogin(request, response);
		} else if ("login".equals(type)) {
			dologin(request, response);

		}

	}

	protected void showLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

	protected void dologin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");

		UserDao userDao = new UserDao();
		boolean flag = userDao.selcet1(name, pwd);
		if (flag) {
			request.getRequestDispatcher("/listServlet").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
