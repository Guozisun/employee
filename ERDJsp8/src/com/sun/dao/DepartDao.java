package com.sun.dao;
/**
* @author 作者:Chaoguo Sun
* @createDate 创建时间：2018年8月7日 上午10:23:14
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import com.sun.pojo.Dept;

import com.sun.utils.DButils;


public class DepartDao {


//联合查询 查询出来所有
	/*
	 * 问题 将List<Dept> list = new ArrayList<>();共享的话
	 * 不管添加还是刷新都不会清空前面的数据 
	 */
//	List<Dept> list = new ArrayList<>();
	//查询所有部门
	
	public Dept selectAll(int id) {
		Dept dept = new Dept();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			String sql = "select * from dept1 where id="+id;
			ps = conn.prepareStatement(sql);// 预处理
			rs = ps.executeQuery();// 结果集
			while (rs.next()) {// 处理结果集
				
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setEmp_count(rs.getInt("emp_count"));;
				
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, null, rs);
		}

		return dept;

	}
	public List<Dept> selectAll() {
		List<Dept> deptList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			String sql = "select * from dept1";
			ps = conn.prepareStatement(sql);// 预处理
			rs = ps.executeQuery();// 结果集
			while (rs.next()) {// 处理结果集
				Dept dept = new Dept();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setEmp_count(rs.getInt("emp_count"));;
				
				deptList.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, null, rs);
		}

		return deptList;

	}
//	根据页面查询数据
	public List<Dept> selectAll(int begin,int size) {
		List<Dept> deptList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			String sql = "select * from dept1 limit "+begin+","+size;
			ps = conn.prepareStatement(sql);// 预处理
			rs = ps.executeQuery();// 结果集
			while (rs.next()) {// 处理结果集
				Dept dept = new Dept();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setEmp_count(rs.getInt("emp_count"));;
				
				deptList.add(dept);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, null, rs);
		}

		return deptList;

	}
	//查询一共多少条数据
	public int count() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			String sql = "select count(*) from dept1";
			ps = conn.prepareStatement(sql);// 预处理
			rs = ps.executeQuery();// 结果集
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
//	根据条件查询有多少条数据
	
	public int count(Dept dept) {
		
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
			if (dept.getName()!=null&&!dept.getName().equals("")) {
				where += " and name='" + dept.getName() + "'";

			}
			
			if (dept.getEmp_count() != -1) {
				where += " and emp_count='" + dept.getEmp_count() + "'";

			}
			String sql = "select count(*) from dept1 "+ where;
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
//	根据条减查询返回页面
	public List<Dept> selectByCondition1(Dept dept,int begin,int size) {
		List<Dept> deptList = new ArrayList<>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;

		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			ps = conn.createStatement();
			String where = "where 1=1";
			if (dept.getName()!=null&&!dept.getName().equals("")) {
				where += " and name='" + dept.getName() + "'";

			}
			
			if (dept.getEmp_count()!= -1) {
				where += " and emp_count='" + dept.getEmp_count() + "'";

			}

			
			String sql = "select * from dept1  "
					+ where +" limit "+begin+","+size;
			rs = ps.executeQuery(sql);// 结果集
			while (rs.next()) {// 处理结果集
				Dept dept1 = new Dept();
				dept1.setId(rs.getInt("id"));
				dept1.setName(rs.getString("name"));
				dept1.setEmp_count(rs.getInt("emp_count"));;
				
				deptList.add(dept1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, null, rs);
		}

		return deptList;

	}
	
	public boolean add(Dept dept) {
		
		boolean flag=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn = DButils.getConn();
		String sql= "insert into dept1(name,emp_count) values(?,?)";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, dept.getName());
			ps.setInt(2, 0);
			
			int row =ps.executeUpdate();
			if (row>0) {
				flag=true;
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DButils.realse(conn, ps,null );
		}
		return flag;
		
	}

//	public boolean addDepts(Dept emp) {
//		boolean flag = true;
//		Connection conn = null;
//		PreparedStatement ps = null;
//		
//			// 连接数据库
//		conn = DButils.getConn();
//			// 查询语句
//		String sql = "insert into dept1(name,sex,age) values(?,?,?)";
//		try {
////				    String sql1 = "select * from dept1 where name =?";
////				    ps= conn.prepareStatement(sql1);
////				    ps.setString(1, emp.getName());
////				    
////				    ResultSet rs= ps.executeQuery();
////				   
////				    boolean boo = rs.next();
////			if (!boo) {
////			ps.close();
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, emp.getName());
//			ps.setString(2, emp.getSex());
//			ps.setInt(3, emp.getAge());
//			ps.setInt(4, emp.getD_id());
//			ps.executeUpdate();
//			flag=false;
////			JOptionPane.showMessageDialog(null,"数据增加成功");
////			  }else{
//////			    JOptionPane.showMessageDialog(null,"姓名已存在,请重新输入");
////			    flag=false;
////			   }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
////			JOptionPane.showMessageDialog(null,"有相同的姓名！");
//			
//		}
//		finally {
//			DButils.realse(conn, ps,null );
//		}
//		
//		return flag;
//	}// 预处理

	public boolean updateDepts(Dept dept) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			String sql = "update dept1 set name=?,emp_count=? where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dept.getName());
			ps.setInt(2, dept.getEmp_count());
			
			ps.setInt(3, dept.getId());
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
	public boolean updateDepts1(Dept dept) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			String sql = "update dept1 set name=? where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dept.getName());
			ps.setInt(2, dept.getId());
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
	//多选
	public List<Dept> selectByChoose(String ids) {
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<Dept> deptList = new ArrayList<>(); 
		
		try {
			conn = DButils.getConn();
			

			ps = conn.createStatement();
			String sql = "select * from dept1 where id in("+ids+")";
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				Dept dept = new Dept();
				dept.setId(rs.getInt("id"));
				dept.setName(rs.getString("name"));
				dept.setEmp_count(rs.getInt("emp_count"));
				
				deptList.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, rs);
		}
		return deptList;

	}

	public boolean deleteDept(int id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DButils.getConn();
			String sql = "delete from dept1 where id =?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int row = ps.executeUpdate();
			if (row > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, null);
		}

		return flag;
	}

	public boolean deleteDept1(String ids) {
		boolean flag = false;
		Connection conn = null;
		Statement ps = null;

		try {
			conn = DButils.getConn();
			conn.setAutoCommit(false);
			ps = conn.createStatement();
			String sql = "delete from dept1 where id in(" + ids + ")";
			ps.executeUpdate(sql);
			ps.close();
			ps = conn.createStatement();
			sql = "update empl1 set d_id = null where d_id in(" + ids + ")";
			ps.executeUpdate(sql);
			ps.close();
			ps = conn.createStatement();
			sql = "delete from r_p_d where d_id in(" + ids + ")";
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

	public boolean updateDepts1(String ids, Dept dept) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			String sql = "update dept1 set name=?,emp_count=? where id in ("+ids+") ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dept.getName());
			ps.setInt(2, dept.getEmp_count());
			
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
	public boolean updateAll(List<Dept> deptList) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// 连接数据库
			conn = DButils.getConn();
			// 查询语句
			for(int i=0;i<deptList.size();i++) {
				Dept dept = deptList.get(i);
			String sql = "update dept1 set name=? where id =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dept.getName());
			ps.setInt(2, dept.getId());
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

}
