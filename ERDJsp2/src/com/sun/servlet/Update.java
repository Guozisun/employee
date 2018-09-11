package com.sun.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.dao.EmployeeDao;
import com.sun.pojo.Employee;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Employee employee= new Employee();
		String id = request.getParameter("id");
		String name = request.getParameter("name1");
		String  sex = request.getParameter("sex1");
		String age = request.getParameter("age1");
		employee.setId(Integer.parseInt(id));
		employee.setName(name);
		employee.setSex(sex);
		employee.setAge(Integer.parseInt(age));
		EmployeeDao dao = new EmployeeDao();
		 boolean flag=dao.updateEmployees(employee);
		 if (flag) {
			request.getRequestDispatcher("/listServlet").forward(request, response);
		}else {
			System.out.println("11111");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
