package com.sun.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.dao.EmployeeDao;
import com.sun.pojo.Employee;

import net.sf.json.JSONArray;


@WebServlet("/listServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=utf-8");

		String who = request.getParameter("who");
		if (who == null) {
			showList(request, response);
		} else if ("add1".equals(who)) {
			add1(request, response);
		} else if ("delete".equals(who)) {
			delete(request, response);

		} else if ("add".equals(who)) {
			add(request, response);
		} else if ("update1".equals(who)) {
			update1(request, response);
		} else if ("update2".equals(who)) {
			update2(request, response);
		} else if ("update".equals(who)) {
			update(request, response);
		} else if ("update2All".equals(who)) {
			update2All(request, response);
		} else if ("update3All".equals(who)) {
			update3All(request, response);
		} else if ("ddd".equals(who)) {
			searchByOne(request, response);
		}
	}

	protected void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDao employeeDao = new EmployeeDao();
		List<Employee> list = employeeDao.selectAll();
		request.setAttribute("userList", list);
		request.getRequestDispatcher("WEB-INF/list.jsp").forward(request, response);

	}

	protected void add1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/insert.jsp").forward(request, response);

	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Employee employee = new Employee();
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		// String age = request.getParameter("age");
		// System.out.println(name);
		int age = -1;
		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		employee.setName(name);
		employee.setSex(sex);
		employee.setAge(age);
		EmployeeDao employeeDao = new EmployeeDao();
		boolean flag = employeeDao.add(employee);
		if (flag) {

			response.sendRedirect("listServlet");

		} else {

			request.getRequestDispatcher("/insert.jsp").forward(request, response);
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ids = request.getParameter("ids");

		EmployeeDao dao = new EmployeeDao();
		boolean flag = dao.deleteEmployee1(ids);
		if (flag) {
			response.sendRedirect("listServlet");
		} else {
			System.out.println("111");
		}
	}

	protected void update1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		EmployeeDao dao = new EmployeeDao();
		List<Employee> list = dao.selectByChoose(ids);
		Employee emp = list.get(0);
		System.out.println(emp.getName());
		request.setAttribute("ids", ids);
		request.setAttribute("emp", emp);
		request.getRequestDispatcher("WEB-INF/updateAll.jsp").forward(request, response);

	}

	protected void update2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		EmployeeDao dao = new EmployeeDao();
		List<Employee> list = dao.selectByChoose(ids);

		request.setAttribute("ids", ids);
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/update.jsp").forward(request, response);

	}

	protected void update2All(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Employee> list=(List<Employee>)request.getAttribute("list");
		// System.out.println(list.size());

		String array = request.getParameter("array");
		String[] arr = array.split(";");
		
		List<Employee> list = new ArrayList<>();
		
		for (int i = 0; i < arr.length; i++) {
			String[] arra = arr[i].split(",");
			Employee emp = new Employee();
			emp.setId(Integer.parseInt(arra[0]));
			emp.setName(arra[1]);
			emp.setSex(arra[2]);
			emp.setAge(Integer.parseInt(arra[3]));
			list.add(emp);
			
		}
		EmployeeDao dao = new EmployeeDao();
		boolean flag = dao.updateAll(list);
		if (flag) {
			response.sendRedirect("listServlet");
		} else {
			System.out.println("11111");
		}

	}
	protected void update3All(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Employee> list=(List<Employee>)request.getAttribute("list");
		// System.out.println(list.size());

		String str = request.getParameter("array");

		new JSONArray();
		JSONArray obj = JSONArray.fromObject(str);//转为json对象
		List<Employee> list = (List<Employee>) JSONArray.toCollection(obj, Employee.class);
		EmployeeDao dao = new EmployeeDao();
		boolean flag = dao.updateAll(list);
		if (flag) {
			response.sendRedirect("listServlet");
		} else {
			System.out.println("11111");
		}

	}
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Employee> list=(List<Employee>)request.getAttribute("list");
		// System.out.println(list.size());
		Employee employee = new Employee();
		String ids = request.getParameter("ids");
		String name = request.getParameter("name1");
		String sex = request.getParameter("sex1");
		String age = request.getParameter("age1");
		employee.setName(name);
		employee.setSex(sex);
		employee.setAge(Integer.parseInt(age));
		EmployeeDao dao = new EmployeeDao();
		boolean flag = dao.updateEmployees1(ids, employee);
		if (flag) {
			response.sendRedirect("listServlet");
		} else {
			System.out.println("11111");
		}

	}

	protected void searchByOne(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sousuo = request.getParameter("sousuo");
		if (sousuo == null) {
			request.getRequestDispatcher("listServlet").forward(request, response);

		}
		EmployeeDao dao = new EmployeeDao();
		Employee employee = dao.selectOne(sousuo);
		System.out.println(employee.getName());
		request.setAttribute("user", employee);
		request.getRequestDispatcher("/WEB-INF/searchOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
