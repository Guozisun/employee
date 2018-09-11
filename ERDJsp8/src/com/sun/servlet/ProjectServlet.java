package com.sun.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.dao.DepartDao;
import com.sun.dao.ProjectDao;
import com.sun.pojo.Dept;
import com.sun.pojo.Employee;
import com.sun.pojo.Project;
import com.sun.utils.ChangLiang;
import com.sun.utils.PaginiTion;

import net.sf.json.JSONArray;

@WebServlet("/proServlet")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String path="WEB-INF/project/";
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=utf-8");

		String who = request.getParameter("who");
		if (who == null) {
			select(request, response);
		} else if ("add1".equals(who)) {
			add1(request, response);
		}else if ("add".equals(who)) {
			add(request, response);
		} else if ("delete".equals(who)) {
			delete(request, response);
		}  else if ("update1".equals(who)) {
			update1(request, response);
		} else if ("update2".equals(who)) {
			update2(request, response);
		} else if ("update".equals(who)) {
			update(request, response);
			//页面修改
		} else if ("update2All".equals(who)) {
			update2All(request, response);
		}
	}
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectDao projectDao = new ProjectDao();
		Project condition = new Project();
		String name = request.getParameter("name");
		condition.setName(name);
		int nowPage = 1;
		if (request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		int count = projectDao.count(condition);
		
		PaginiTion p = new PaginiTion(nowPage, count, ChangLiang.SIZE_PAGE, ChangLiang.NUM_PAGE);
		List<Project> list = projectDao.selectByCondition(condition,p.getBegin(), ChangLiang.SIZE_PAGE);
		request.setAttribute("p", p);
		request.setAttribute("userList", list);
		request.setAttribute("c", condition);
		request.getRequestDispatcher(path+"projectList.jsp").forward(request, response);
	}
	protected void add1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(path+"project_insert.jsp").forward(request, response);

	}
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Project project = new Project();
		String name = request.getParameter("name");
		project.setName(name);
		
		ProjectDao projectDao = new ProjectDao();
		boolean flag = projectDao.addProject(project);
		if (flag) {
			response.sendRedirect("proServlet");

		} else {

			request.getRequestDispatcher(path+"project_insert.jsp").forward(request, response);
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ids = request.getParameter("ids");
		ProjectDao projectDao = new ProjectDao();
		
		boolean flag = projectDao.deleteProjects(ids);
		if (flag) {
			response.sendRedirect("proServlet");
		} else {
			System.out.println("111");
		}
	}

	protected void update1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		DepartDao dao = new DepartDao();
		List<Dept> deptList = dao.selectByChoose(ids);
		Dept dept = deptList.get(0);
		
		request.setAttribute("ids", ids);
		request.setAttribute("emp", dept);
		request.getRequestDispatcher(path+"updateAll.jsp").forward(request, response);

	}

	protected void update2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		DepartDao dao = new DepartDao();
		List<Dept> deptList = dao.selectByChoose(ids);

		request.setAttribute("ids", ids);
		request.setAttribute("list", deptList);
		request.getRequestDispatcher(path+"update.jsp").forward(request, response);

	}
//页面修改
	protected void update2All(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Employee> list=(List<Employee>)request.getAttribute("list");
		// System.out.println(list.size());

		String array = request.getParameter("array");
		JSONArray array2 = JSONArray.fromObject(array);
		List<Project> list = (List<Project>) JSONArray.toCollection(array2, Project.class);
		ProjectDao projectDao = new ProjectDao();
		boolean flag = projectDao.updateProject(list);
		if (flag) {
			response.sendRedirect("proServlet");
		} else {
			System.out.println("11111");
		}

	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Employee> list=(List<Employee>)request.getAttribute("list");
		// System.out.println(list.size());
		Dept dept = new Dept();
		String ids = request.getParameter("ids");
		String name = request.getParameter("name");
		
		int emp_count=-1;
		if (request.getParameter("emp_count")!=null&&!"".equals(request.getParameter("emp_count"))) {
			emp_count = Integer.parseInt(request.getParameter("emp_count"));
			
		}
		dept.setName(name);
		dept.setEmp_count(emp_count);
		
		DepartDao dao = new DepartDao();
		boolean flag = dao.updateDepts1(ids, dept);
		if (flag) {
			response.sendRedirect("proServlet");
		} else {
			System.out.println("11111");
		}

	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
