package com.sun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		try {
		conn = DButils.getConn();
		String sql = "select * from user where username=? and password = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		rs =ps.executeQuery();
		if (rs.next()) {
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

}
