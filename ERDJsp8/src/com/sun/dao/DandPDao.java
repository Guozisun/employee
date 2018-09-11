package com.sun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.sun.pojo.DandP;
import com.sun.utils.DButils;

/**
* @author 作者:Chaoguo Sun
* @createDate 创建时间：2018年8月31日 下午1:56:40
*/
public class DandPDao {
     public boolean addAll(List<DandP> dpList) {
    	 Connection conn = null;
    	 PreparedStatement ps=null;
    	 int rs=0;
    		 try {
    			
    	    	 for(int i=0;i<dpList.size();i++) {
    	    		 conn=DButils.getConn();
    	    		 DandP dandP = dpList.get(i);
    	    		 String sql="insert into r_p_d(d_id,p_id) values (?,?)";
    	    		 ps=conn.prepareStatement(sql);
    	    		 ps.setInt(1, dandP.getD_id());
    	    		 ps.setInt(2, dandP.getP_id());
    	    		 rs=ps.executeUpdate();
    	    	 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DButils.realse(conn, ps, null);
			}
    		 return rs>0;
     
     }
     public boolean deleteAll(List<DandP> dpList) {
    	 Connection conn = null;
    	 PreparedStatement ps=null;
    	 int rs=0;
    		 try {
    			
    	    	 for(int i=0;i<dpList.size();i++) {
    	    		 conn=DButils.getConn();
    	    		 DandP dandP = dpList.get(i);
    	    		 String sql="delete from r_p_d where d_id=? and p_id=?";
    	    		 ps=conn.prepareStatement(sql);
    	    		 ps.setInt(1, dandP.getD_id());
    	    		 ps.setInt(2, dandP.getP_id());
    	    		 rs=ps.executeUpdate();
    	    	 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DButils.realse(conn, ps, null);
			}
    		 return rs>0;
     
     }
}
