package com.sun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.pojo.User;
import com.sun.utils.DButils;

/**
* @author 作者:Chaoguo Sun
* @createDate 创建时间：2018年8月21日 上午10:31:53
*/
public class UserDao {
	
	public boolean selcet1(String username,String password) {
		boolean flag= false;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user = new User();
		try {
		conn = DButils.getConn();
		String sql = "select * from user where username=? and password = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		rs =ps.executeQuery();
		while (rs.next()) {
		flag=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DButils.realse(conn, ps, rs);
		}
		return flag;
		
	}

	public boolean insert(User user) {
		boolean flag=false;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
		conn=DButils.getConn();
		
		String sql1="select * from user where username=?";
		ps=conn.prepareStatement(sql1);
		ps.setString(1, user.getUsername());
		rs=ps.executeQuery();
		boolean boo=rs.next();
		if (!boo) {
			ps.close();
			rs.close();
			String sql ="insert into user(username,password) values(?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			int row =ps.executeUpdate();
			if (row>0) {
				flag=true;
			}
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DButils.realse(conn, ps, null);
		}
		return flag;
	}

}
