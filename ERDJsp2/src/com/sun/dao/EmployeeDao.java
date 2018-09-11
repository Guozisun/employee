package com.sun.dao;
/**
* @author ����:Chaoguo Sun
* @createDate ����ʱ�䣺2018��8��7�� ����10:23:14
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.pojo.Dept;
import com.sun.pojo.Employee;
import com.sun.utils.DButils;


public class EmployeeDao {


//���ϲ�ѯ ��ѯ��������
	/*
	 * ���� ��List<Employee> list = new ArrayList<>();����Ļ�
	 * ������ӻ���ˢ�¶��������ǰ������� 
	 */
//	List<Employee> list = new ArrayList<>();
	public List<Employee> selectAll() {
		List<Employee> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			String sql = "select * from empl1";
			ps = conn.prepareStatement(sql);// Ԥ����
			rs = ps.executeQuery();// �����
			while (rs.next()) {// ��������
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, null, rs);
		}

		return list;

	}

	public List<Employee> selectByCondition(Employee conditon) {
		List<Employee> list = new ArrayList<>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;

		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			ps = conn.createStatement();
			String where = "where 1=1";
			if (!conditon.getName().equals("")) {
				where += " and e.name='" + conditon.getName() + "'";

			}
			if (!conditon.getSex().equals("")) {
				where += " and e.sex='" + conditon.getSex() + "'";

			}
			if (conditon.getAge() != -1) {
				where += " and e.age='" + conditon.getAge() + "'";

			}
			if (conditon.getDept().getId() != -1) {
				where += " and e.d_id='" + conditon.getDept().getId() + "'";

			}
			String sql = "select e.*,d.name as dName,emp_count from empl1 as e left join dept1 as d on d.id=e.d_id "
					+ where;
			rs = ps.executeQuery(sql);// �����
			while (rs.next()) {// ��������
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));

				Dept dept = new Dept();
				dept.setId(rs.getInt("d_id"));
				dept.setName(rs.getString("dName"));
				dept.setE_count(rs.getInt("emp_count"));
				emp.setDept(dept);
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, null, rs);
		}

		return list;

	}
	public boolean add(Employee employee) {
		
		boolean flag=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn = DButils.getConn();
		String sql= "insert into empl1(name,sex,age)values(?,?,?)";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getSex());
			ps.setInt(3, employee.getAge());
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

//	public boolean addEmployees(Employee emp) {
//		boolean flag = true;
//		Connection conn = null;
//		PreparedStatement ps = null;
//		
//			// �������ݿ�
//		conn = DButils.getConn();
//			// ��ѯ���
//		String sql = "insert into empl1(name,sex,age) values(?,?,?)";
//		try {
////				    String sql1 = "select * from empl1 where name =?";
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
////			JOptionPane.showMessageDialog(null,"�������ӳɹ�");
////			  }else{
//////			    JOptionPane.showMessageDialog(null,"�����Ѵ���,����������");
////			    flag=false;
////			   }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
////			JOptionPane.showMessageDialog(null,"����ͬ��������");
//			
//		}
//		finally {
//			DButils.realse(conn, ps,null );
//		}
//		
//		return flag;
//	}// Ԥ����

	public boolean updateEmployees(Employee emp) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			String sql = "update empl1 set name=?,sex=?,age=? where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getSex());
			ps.setInt(3, emp.getAge());
			ps.setInt(4, emp.getId());
			int row = ps.executeUpdate();
			if (row > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Ԥ����
		finally {
			DButils.realse(conn, ps, null);
		}
		return flag;
	}

	public Employee selectOne(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = new Employee();
		try {
			conn = DButils.getConn();
			String sql = "select * from empl1 where name =?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {

				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, rs);
		}
		return emp;

	}
	public List<Employee> selectByChoose(String ids) {
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<>(); 
		
		try {
			conn = DButils.getConn();
			

			ps = conn.createStatement();
			String sql = "select * from empl1 where id in("+ids+")";
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DButils.realse(conn, ps, rs);
		}
		return list;

	}

	public boolean deleteEmployee(int id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DButils.getConn();
			String sql = "delete from empl1 where id =?";
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

	public boolean deleteEmployee1(String ids) {
		boolean flag = false;
		Connection conn = null;
		Statement ps = null;

		try {
			conn = DButils.getConn();

			ps = conn.createStatement();
			String sql = "delete from empl1 where id in(" + ids + ")";
			int row = ps.executeUpdate(sql);
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

	public boolean updateEmployees1(String ids, Employee emp) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			String sql = "update empl1 set name=?,sex=?,age=? where id in ("+ids+") ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getSex());
			ps.setInt(3, emp.getAge());
			int row = ps.executeUpdate();
			if (row > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Ԥ����
		finally {
			DButils.realse(conn, ps, null);
		}
		return flag;
	}
	public boolean updateAll(List<Employee> list) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			for(int i=0;i<list.size();i++) {
				Employee emp = list.get(i);
			String sql = "update empl1 set name=?,sex=?,age=? where id =? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getSex());
			ps.setInt(3, emp.getAge());
			ps.setInt(4, emp.getId());
			int row = ps.executeUpdate();
			if (row > 0) {
				flag = true;
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Ԥ����
		finally {
			DButils.realse(conn, ps, null);
		}
		return flag;
	}

}
