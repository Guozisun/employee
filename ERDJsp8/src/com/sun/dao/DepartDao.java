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



import com.sun.pojo.Dept;

import com.sun.utils.DButils;


public class DepartDao {


//���ϲ�ѯ ��ѯ��������
	/*
	 * ���� ��List<Dept> list = new ArrayList<>();����Ļ�
	 * ������ӻ���ˢ�¶��������ǰ������� 
	 */
//	List<Dept> list = new ArrayList<>();
	//��ѯ���в���
	
	public Dept selectAll(int id) {
		Dept dept = new Dept();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			String sql = "select * from dept1 where id="+id;
			ps = conn.prepareStatement(sql);// Ԥ����
			rs = ps.executeQuery();// �����
			while (rs.next()) {// ��������
				
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
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			String sql = "select * from dept1";
			ps = conn.prepareStatement(sql);// Ԥ����
			rs = ps.executeQuery();// �����
			while (rs.next()) {// ��������
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
//	����ҳ���ѯ����
	public List<Dept> selectAll(int begin,int size) {
		List<Dept> deptList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			String sql = "select * from dept1 limit "+begin+","+size;
			ps = conn.prepareStatement(sql);// Ԥ����
			rs = ps.executeQuery();// �����
			while (rs.next()) {// ��������
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
	//��ѯһ������������
	public int count() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			String sql = "select count(*) from dept1";
			ps = conn.prepareStatement(sql);// Ԥ����
			rs = ps.executeQuery();// �����
			while (rs.next()) {// ��������
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
//	����������ѯ�ж���������
	
	public int count(Dept dept) {
		
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
			ps = conn.createStatement();
			String where = "where 1=1";
			if (dept.getName()!=null&&!dept.getName().equals("")) {
				where += " and name='" + dept.getName() + "'";

			}
			
			if (dept.getEmp_count() != -1) {
				where += " and emp_count='" + dept.getEmp_count() + "'";

			}
			String sql = "select count(*) from dept1 "+ where;
			// Ԥ����
			rs = ps.executeQuery(sql);// �����
			while (rs.next()) {// ��������
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
//	����������ѯ����ҳ��
	public List<Dept> selectByCondition1(Dept dept,int begin,int size) {
		List<Dept> deptList = new ArrayList<>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;

		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
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
			rs = ps.executeQuery(sql);// �����
			while (rs.next()) {// ��������
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
//			// �������ݿ�
//		conn = DButils.getConn();
//			// ��ѯ���
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

	public boolean updateDepts(Dept dept) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
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
		} // Ԥ����
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
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
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
		} // Ԥ����
		finally {
			DButils.realse(conn, ps, null);
		}
		return flag;
	}
	//��ѡ
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
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
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
		} // Ԥ����
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
			// �������ݿ�
			conn = DButils.getConn();
			// ��ѯ���
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
		} // Ԥ����
		finally {
			DButils.realse(conn, ps, null);
		}
		return flag;
	}

}
