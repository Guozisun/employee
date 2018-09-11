package com.sun.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.dao.EmployeeDao;
import com.sun.pojo.Dept;
import com.sun.pojo.Employee;


@WebServlet("/addEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=utf-8");
		Employee employee = new Employee();
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
//		String age = request.getParameter("age");
		int age=-1;
		try {
			age= Integer.parseInt(request.getParameter("age"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		employee.setName(name);
		employee.setSex(sex);
		employee.setAge(age);
		EmployeeDao employeeDao = new EmployeeDao();
		boolean flag=employeeDao.add(employee);
		if (flag) {
			request.getRequestDispatcher("/listServlet").forward(request, response);
		}
		else {
			request.setAttribute("msg", "添加失败，请重新添加！");
			request.getRequestDispatcher("/insert.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
