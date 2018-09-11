package com.sun.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.dao.DepartDao;
import com.sun.dao.EmployeeDao;
import com.sun.dao.ProjectDao;
import com.sun.dao.ScoreDao1;
import com.sun.pojo.Dept;
import com.sun.pojo.Employee;
import com.sun.pojo.Project;
import com.sun.pojo.Score;
import com.sun.utils.ChangLiang;
import com.sun.utils.PaginiTion;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/scoreServlet2")
public class SoreServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String path="WEB-INF/score/";
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=utf-8");

		String who = request.getParameter("who");
		if (who == null) {
			select(request, response);
		}else if ("update2All".equals(who)) {
			update2All(request, response);
		} 
	}
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Score condition = new Score();
		String empName =request.getParameter("name");
		int deptId =-1;
		if (request.getParameter("deptId")!=null&&!"".equals(request.getParameter("deptId"))) {
			deptId = Integer.parseInt(request.getParameter("deptId"));
			
		}
		int proId =-1;
		if (request.getParameter("proId")!=null&&!"".equals(request.getParameter("proId"))) {
			proId = Integer.parseInt(request.getParameter("proId"));
			
		}
		int value = -1;
		if (request.getParameter("value")!=null&&!"".equals(request.getParameter("value"))) {
			value = Integer.parseInt(request.getParameter("value"));
			
		}
		
		
		int gradeId =-1;
		if (request.getParameter("gradeId")!=null&&!"".equals(request.getParameter("gradeId"))) {
			gradeId = Integer.parseInt(request.getParameter("gradeId"));
			
		}
		String grade="";
		String []gradeName= {"优秀","良好","中等","及格","不及格"};
		if (gradeId!=-1) {
			
			 grade=gradeName[gradeId];
			 condition.setGrade(grade);
		}else {
			 condition.setGrade("-1");
		}
		
		
		Employee emp = new Employee();
		emp.setName(empName);
		
		condition.setEmployee(emp);
		Dept dept = new Dept();
		dept.setId(deptId);
		emp.setDept(dept);
		
		Project project = new Project();
		project.setId(proId);
		
		condition.setValue(value);
		
		condition.setProject(project);
		ScoreDao1 scoreDao1 = new ScoreDao1();
		DepartDao departDao = new DepartDao();
		List<Dept> depts =departDao.selectAll();
		ProjectDao projectDao = new ProjectDao();
		List<Project> projects = projectDao.selectAll();
		
		
		int nowPage = 1;
		if (request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		int count = scoreDao1.selectByCondition(condition);
		
		PaginiTion p = new PaginiTion(nowPage, count, ChangLiang.SIZE_PAGE, ChangLiang.NUM_PAGE);
		List<Score> list = scoreDao1.selectByCondition(condition,p.getBegin(), ChangLiang.SIZE_PAGE);
		request.setAttribute("depts", depts);
		request.setAttribute("projects", projects);
		request.setAttribute("gradeName", gradeName);
		request.setAttribute("p", p);
		request.setAttribute("userList", list);
		request.setAttribute("c", condition);
		request.getRequestDispatcher(path+"scoreList2.jsp").forward(request, response);
	}
	
	protected void update2All(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Employee> list=(List<Employee>)request.getAttribute("list");
		// System.out.println(list.size());

		String array = request.getParameter("array");
		JSONArray array2 = JSONArray.fromObject(array);
		
		List<Score> set = (List<Score>) JSONArray.toCollection(array2, Score.class);
		ScoreDao1 dao = new ScoreDao1();
		 dao.save(set);
			response.sendRedirect("scoreServlet2");
		
		

	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
