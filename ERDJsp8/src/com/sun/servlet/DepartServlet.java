package com.sun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.dao.DandPDao;
import com.sun.dao.DepartDao;
import com.sun.dao.ProjectDao;
import com.sun.pojo.DandP;
import com.sun.pojo.Dept;
import com.sun.pojo.Employee;
import com.sun.pojo.Project;
import com.sun.utils.ChangLiang;
import com.sun.utils.PaginiTion;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/deptServlet")
public class DepartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String path = "WEB-INF/dept/";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html,charset=utf-8");

		String who = request.getParameter("who");
		if (who == null) {
			select(request, response);
		} else if ("add1".equals(who)) {
			add1(request, response);
		} else if ("delete".equals(who)) {
			delete(request, response);

		} else if ("111".equals(who)) {
			select(request, response);
		} else if ("add".equals(who)) {
			add(request, response);
		} else if ("update1".equals(who)) {
			update1(request, response);
		} else if ("update2".equals(who)) {
			update2(request, response);
		}else if ("update4".equals(who)) {
			update4(request, response);
		}else if ("update41".equals(who)) {
			update41(request, response);
		} else if ("update".equals(who)) {
			update(request, response);
		} else if ("update2All".equals(who)) {
			update2All(request, response);
		} else if ("xm".equals(who)) {
			xm(request, response);
		} 
		else if ("xm2".equals(who)) {
			xm2(request, response);
		}else if ("xm3".equals(who)) {
			xm3(request, response);
		}else if ("xm4".equals(who)) {
			xm4(request, response);
		} else if ("xm5".equals(who)) {
			xm5(request, response);
		}else if ("addPro".equals(who)) {
			addPro(request, response);
		} else if ("addPro2".equals(who)) {
			addPro2(request, response);
		}else if ("addPro3".equals(who)) {
			addPro3(request, response);
		} else if ("deletePro".equals(who)) {
			deletePro(request, response);
		} else if ("deletePro2".equals(who)) {
			deletePro2(request, response);
		} else if ("deletePro3".equals(who)) {
			deletePro3(request, response);
		}
	}

	private void addPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int deptId = -1;
		if (request.getParameter("deptId") != null && !" ".equals(request.getParameter("depetId"))) {
			deptId = Integer.parseInt(request.getParameter("deptId"));
		}
		int proId = -1;
		if (request.getParameter("proId") != null && !" ".equals(request.getParameter("proId"))) {
			proId = Integer.parseInt(request.getParameter("proId"));
		}
		ProjectDao projectDao = new ProjectDao();
		boolean flag = projectDao.addProject(deptId, proId);
		if (flag) {
			response.sendRedirect("deptServlet?who=xm&deptId=" + deptId);
		}

	}
	private void addPro2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		  
		
		int deptId = Integer.parseInt(request.getParameter("deptId"));
		int proId = Integer.parseInt(request.getParameter("proId"));
		ProjectDao projectDao = new ProjectDao();
		boolean flag = projectDao.addProject(deptId, proId);
		out.print(flag);

	}
	private void addPro3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Object array = request.getParameter("array");
		JSONArray jsonArray = JSONArray.fromObject(array);
	    List<DandP> dandPs = (List<DandP>) JSONArray.toCollection(jsonArray, DandP.class);
		DandPDao dandPDao = new DandPDao();
		boolean flag = dandPDao.addAll(dandPs);
		out.print(flag);

	}
	private void deletePro3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Object array = request.getParameter("array");
		JSONArray jsonArray = JSONArray.fromObject(array);
	    List<DandP> dandPs = (List<DandP>) JSONArray.toCollection(jsonArray, DandP.class);
		DandPDao dandPDao = new DandPDao();
		boolean flag = dandPDao.deleteAll(dandPs);
		out.print(flag);

	}

	private void deletePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int deptId = -1;
		if (request.getParameter("deptId") != null && !" ".equals(request.getParameter("depetId"))) {
			deptId = Integer.parseInt(request.getParameter("deptId"));
		}
		int proId = -1;
		if (request.getParameter("proId") != null && !" ".equals(request.getParameter("proId"))) {
			proId = Integer.parseInt(request.getParameter("proId"));
		}
		ProjectDao projectDao = new ProjectDao();
		boolean flag = projectDao.deleteProject(deptId, proId);
		if (flag) {
			response.sendRedirect("deptServlet?who=xm&deptId=" + deptId);
		}

	}
	private void deletePro2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int deptId = -1;
		if (request.getParameter("deptId") != null && !" ".equals(request.getParameter("depetId"))) {
			deptId = Integer.parseInt(request.getParameter("deptId"));
		}
		int proId = -1;
		if (request.getParameter("proId") != null && !" ".equals(request.getParameter("proId"))) {
			proId = Integer.parseInt(request.getParameter("proId"));
		}
		ProjectDao projectDao = new ProjectDao();
		boolean flag = projectDao.deleteProject(deptId, proId);
		out.print(flag);
	

	}
	private void xm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectDao projectDao = new ProjectDao();
		DepartDao departDao = new DepartDao();
		int deptId = -1;
		if (request.getParameter("deptId") != null && !" ".equals(request.getParameter("depetId"))) {
			deptId = Integer.parseInt(request.getParameter("deptId"));
		}
		int nowPage = 1;
		if (request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		int count = projectDao.count(deptId);
		PaginiTion p = new PaginiTion(nowPage, count, ChangLiang.SIZE_PAGE, ChangLiang.NUM_PAGE);
		List<Project> proList = projectDao.selectAll(deptId, p.getBegin(), ChangLiang.SIZE_PAGE);
		List<Project> proNoList = projectDao.selectNotIn(deptId);
		Dept dept = departDao.selectAll(deptId);
		request.setAttribute("dept", dept);
		request.setAttribute("p", p);
		request.setAttribute("proNoList", proNoList);
		request.setAttribute("proList", proList);
		request.getRequestDispatcher(path + "proAdept.jsp").forward(request, response);

	}
	private void xm2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectDao projectDao = new ProjectDao();
		DepartDao departDao = new DepartDao();
		int deptId = -1;
		if (request.getParameter("deptId") != null && !" ".equals(request.getParameter("depetId"))) {
			deptId = Integer.parseInt(request.getParameter("deptId"));
		}
		
		List<Project> proList = projectDao.selectAll2(deptId);
		List<Project> proNoList = projectDao.selectNotIn(deptId);
		Dept dept = departDao.selectAll(deptId);
		request.setAttribute("dept", dept);
		request.setAttribute("proNoList", proNoList);
		request.setAttribute("proList", proList);
		request.getRequestDispatcher(path + "proAdept2.jsp").forward(request, response);

	}
	private void xm3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectDao projectDao = new ProjectDao();
		DepartDao departDao = new DepartDao();
		int deptId = -1;
		if (request.getParameter("deptId") != null && !" ".equals(request.getParameter("depetId"))) {
			deptId = Integer.parseInt(request.getParameter("deptId"));
		}
		
		List<Project> proList = projectDao.selectAll2(deptId);
		List<Project> proNoList = projectDao.selectNotIn(deptId);
		Dept dept = departDao.selectAll(deptId);
		request.setAttribute("dept", dept);
		request.setAttribute("proNoList", proNoList);
		request.setAttribute("proList", proList);
		request.getRequestDispatcher(path + "proAdept3.jsp").forward(request, response);

	}
	private void xm4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectDao projectDao = new ProjectDao();
		DepartDao departDao = new DepartDao();
		int deptId = -1;
		if (request.getParameter("deptId") != null && !" ".equals(request.getParameter("depetId"))) {
			deptId = Integer.parseInt(request.getParameter("deptId"));
		}
		
		List<Project> proList = projectDao.selectAll2(deptId);
		List<Project> proNoList = projectDao.selectNotIn(deptId);
		Dept dept = departDao.selectAll(deptId);
		request.setAttribute("dept", dept);
		request.setAttribute("proNoList", proNoList);
		request.setAttribute("proList", proList);
		request.getRequestDispatcher(path + "proAdept4.jsp").forward(request, response);

	}private void xm5(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectDao projectDao = new ProjectDao();
		DepartDao departDao = new DepartDao();
		int deptId = -1;
		if (request.getParameter("deptId") != null && !" ".equals(request.getParameter("depetId"))) {
			deptId = Integer.parseInt(request.getParameter("deptId"));
		}
		
		List<Project> proList = projectDao.selectAll2(deptId);
		List<Project> proNoList = projectDao.selectNotIn(deptId);
		Dept dept = departDao.selectAll(deptId);
		request.setAttribute("dept", dept);
		request.setAttribute("proNoList", proNoList);
		request.setAttribute("proList", proList);
		request.getRequestDispatcher(path + "proAdept5.jsp").forward(request, response);

	}
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepartDao departDao = new DepartDao();
		Dept condition = new Dept();
		String name = request.getParameter("name");
		int emp_count = -1;
		if (request.getParameter("emp_count") != null && !"".equals(request.getParameter("emp_count"))) {
			emp_count = Integer.parseInt(request.getParameter("emp_count"));

		}
		condition.setName(name);
		condition.setEmp_count(emp_count);

		int nowPage = 1;
		if (request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		int count = departDao.count(condition);

		PaginiTion p = new PaginiTion(nowPage, count, ChangLiang.SIZE_PAGE, ChangLiang.NUM_PAGE);
		List<Dept> list = departDao.selectByCondition1(condition, p.getBegin(), ChangLiang.SIZE_PAGE);
		request.setAttribute("p", p);
		request.setAttribute("userList", list);
		request.setAttribute("c", condition);
		request.getRequestDispatcher(path + "deptList.jsp").forward(request, response);
	}

	protected void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DepartDao departDao = new DepartDao();
		int nowPage = 1;
		if (request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		int count = departDao.count();
		PaginiTion p = new PaginiTion(nowPage, count, ChangLiang.SIZE_PAGE, ChangLiang.NUM_PAGE);
		List<Dept> list = departDao.selectAll(p.getBegin(), ChangLiang.SIZE_PAGE);
		request.setAttribute("p", p);
		request.setAttribute("userList", list);

		request.getRequestDispatcher(path + "deptList.jsp").forward(request, response);

	}

	protected void add1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(path + "dept_insert.jsp").forward(request, response);

	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Dept dept = new Dept();
		String name = request.getParameter("name");

		// String age = request.getParameter("age");
		// System.out.println(name);

		dept.setName(name);

		DepartDao departDao = new DepartDao();
		boolean flag = departDao.add(dept);
		if (flag) {

			response.sendRedirect("deptServlet");

		} else {

			request.getRequestDispatcher(path + "dept_insert.jsp").forward(request, response);
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ids = request.getParameter("ids");

		DepartDao departDao = new DepartDao();
		boolean flag = departDao.deleteDept1(ids);
		if (flag) {
			response.sendRedirect("deptServlet");
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
		request.getRequestDispatcher(path + "updateAll.jsp").forward(request, response);

	}

	protected void update2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		DepartDao dao = new DepartDao();
		List<Dept> deptList = dao.selectByChoose(ids);

		request.setAttribute("ids", ids);
		request.setAttribute("list", deptList);
		request.getRequestDispatcher(path + "update.jsp").forward(request, response);

	}
	protected void update4(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");;
		PrintWriter out = response.getWriter();
		int ids = Integer.parseInt(request.getParameter("ids"));
		DepartDao dao = new DepartDao();
		Dept dept = dao.selectAll(ids);
		/*JSONArray jsonArray = new JSONArray();*/
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("id", dept.getId());
		jsonObject.put("name", dept.getName());
		/*jsonArray.add(jsonObject);*/
		out.println(jsonObject);
		

	}

	protected void update2All(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Employee> list=(List<Employee>)request.getAttribute("list");
		// System.out.println(list.size());

		String array = request.getParameter("array");
		JSONArray array2 = JSONArray.fromObject(array);
		List<Dept> list = (List<Dept>) JSONArray.toCollection(array2, Dept.class);
		DepartDao dao = new DepartDao();
		boolean flag = dao.updateAll(list);
		if (flag) {
			response.sendRedirect("deptServlet");
		} else {
			System.out.println("11111");
		}

	}
	protected void update41(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Employee> list=(List<Employee>)request.getAttribute("list");
		// System.out.println(list.size());
		PrintWriter out = response.getWriter();
		Object dept = request.getParameter("dept");
		JSONObject array2 = JSONObject.fromObject(dept);
		Dept list = (Dept) JSONObject.toBean(array2, Dept.class);
		DepartDao dao = new DepartDao();
		boolean flag = dao.updateDepts1(list);
		
		out.print(flag);

	}
	
	

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// List<Employee> list=(List<Employee>)request.getAttribute("list");
		// System.out.println(list.size());
		Dept dept = new Dept();
		String ids = request.getParameter("ids");
		String name = request.getParameter("name");

		int emp_count = -1;
		if (request.getParameter("emp_count") != null && !"".equals(request.getParameter("emp_count"))) {
			emp_count = Integer.parseInt(request.getParameter("emp_count"));

		}
		dept.setName(name);
		dept.setEmp_count(emp_count);

		DepartDao dao = new DepartDao();
		boolean flag = dao.updateDepts1(ids, dept);
		if (flag) {
			response.sendRedirect("deptServlet");
		} else {
			System.out.println("11111");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
