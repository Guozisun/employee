package com.sun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.sun.pojo.Project;

import com.sun.utils.DButils;


/**
 * @author 作者:Chaoguo Sun
 * @createDate 创建时间：2018年8月7日 上午10:23:14
 */
public class ProjectDao {
	public int count( int dId) {
	
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		int count =0;
		try {
			conn = DButils.getConn();
			ps = conn.createStatement();
			String sql = "select count(p_id) from v_p_d where d_id=" + dId;

			rs = ps.executeQuery(sql);
			while (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, rs);
		}
		return count;

	}
	//根据部门ID查询项目
	public List<Project> selectAll(int dId,int begin,int size) {
		List<Project> listProject = new ArrayList<>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;

		try {
			conn = DButils.getConn();
			ps = conn.createStatement();
			String sql = "select p_id,p_name from v_p_d where d_id=" +dId+" limit "+begin+","+size;

			rs = ps.executeQuery(sql);
			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("p_id"));
				project.setName(rs.getString("p_name"));
				listProject.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, rs);
		}
		return listProject;

	}//删除项目
	public List<Project> selectAll2(int dId) {
		List<Project> listProject = new ArrayList<>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;

		try {
			conn = DButils.getConn();
			ps = conn.createStatement();
			String sql = "select p_id,p_name from v_p_d where d_id=" +dId;

			rs = ps.executeQuery(sql);
			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("p_id"));
				project.setName(rs.getString("p_name"));
				listProject.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, rs);
		}
		return listProject;

	}//删除项目
	public boolean deleteProject(int dId, int pId) {

		Connection conn = null;
		Statement ps = null;
		int row = 0;
		try {
			// 连接数据库
			conn = DButils.getConn();
			ps = conn.createStatement();
			String sql = "delete from r_p_d where d_id ="+dId+" and p_id="+pId+" ";
			row = ps.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 预处理
		finally {
			DButils.realse(conn, ps, null);
		}
		return row > 0;
	}
	//添加项目
	public boolean addProject(int dId, int pId) {

		Connection conn = null;
		Statement ps = null;
		int row = 0;
		try {
			// 连接数据库
			conn = DButils.getConn();
			ps = conn.createStatement();
			String sql = "insert into r_p_d(d_id,p_id)values (" + dId + "," + pId + ")";
			row = ps.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 预处理
		finally {
			DButils.realse(conn, ps, null);
		}
		return row > 0;
	}
	public List<Project> selectNotIn(int dId) {
		List<Project> listProject1 = new ArrayList<>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;

		try {
			conn = DButils.getConn();
			ps = conn.createStatement();
			String sql = "select * from project where id not in(select p_id from v_p_d where d_id=" + dId + ")";

			rs = ps.executeQuery(sql);
			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("id"));
				project.setName(rs.getString("name"));
				listProject1.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, rs);
		}
		return listProject1;

	}
	public List<Project> selectAll() {
		List<Project> listProject = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DButils.getConn();
			String sql = "select * from project";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("id"));
				project.setName(rs.getString("name"));
				listProject.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, rs);
		}
		return listProject;

	}

	public boolean addProject(Project project) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			String sql = "insert into project(name) values(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, project.getName());

			int row = ps.executeUpdate();
			if (row > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 预处理
		finally {
			DButils.realse(conn, ps, null);
		}
		return flag;
	}

	public boolean updateProject(List<Project> project) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 连接数
			for(int i = 0;i< project.size();i++) {
				Project project2 = project.get(i);
			
			conn = DButils.getConn();
			// 查询语句
			String sql = "update project set name=?where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, project2.getName());

			

			ps.setInt(2, project2.getId());
			int row = ps.executeUpdate();
			if (row > 0) {
				flag = true;
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 预处理
		finally {
			DButils.realse(conn, ps, null);
		}
		return flag;
	}

	public boolean deleteProjects(String ids) {
		boolean flag = false;
		Connection conn = null;
		Statement ps = null;

		try {
			conn = DButils.getConn();
			conn.setAutoCommit(false);
			ps = conn.createStatement();
			String sql = "delete from project where id in(" + ids + ")";
			ps.executeUpdate(sql);
			ps.close();
			ps = conn.createStatement();
			sql="delete from r_p_d where p_id in(" + ids + ")";
			ps.executeUpdate(sql);
			conn.commit();
			conn.setAutoCommit(true);
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, null);
		}

		return flag;
	}
//	根据条件查询有多少条数据
	public int count(Project project) {
		
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			ps = conn.createStatement();
			String where = "where 1=1";
			if (project.getName()!=null&&!project.getName().equals("")) {
				where += " and name='" + project.getName() + "'";

			}
			String sql = "select count(*) from project "+ where;
			// 预处理
			rs = ps.executeQuery(sql);// 结果集
			while (rs.next()) {// 处理结果集
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, null, rs);
		}

		return count;

	}
	public List<Project> selectByCondition(Project conditon,int begin,int size ) {
		List<Project> listProject = new ArrayList<>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;

		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			ps = conn.createStatement();
			String where = "where 1=1";
			if (conditon.getName()!=null&&!conditon.getName().equals("")) {
				where += " and name='" + conditon.getName() + "'";

			}
			String sql = "select * from project " + where +" limit "+begin+","+size;
			rs = ps.executeQuery(sql);// 结果集
			while (rs.next()) {// 处理结果集
				Project project = new Project();
				project.setId(rs.getInt("id"));
				project.setName(rs.getString("name"));
				listProject.add(project);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, null, rs);
		}

		return listProject;

	}
	
}
