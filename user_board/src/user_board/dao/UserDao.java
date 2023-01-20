package user_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbcp.ConnectionProvider;
import user_board.dto.User;

public class UserDao {
	List<User> UserList = null;
	User user;
	// 싱글톤
	private static UserDao instance = new UserDao();
	
	public static UserDao getInstance() {
		return instance;
	}
	
	private UserDao() {
	}
	
	public List<User> allUser() throws SQLException {
		 List<User> UserList = new ArrayList<User>();
		Connection conn = ConnectionProvider.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select * from user";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			User user = makeUser(rs);
			UserList.add(user);
		}
		stmt.close();
		return UserList;
	}

	public User makeUser(ResultSet rs) throws SQLException {
		User user = new User();
			user.setUser_num(rs.getInt("user_num"));
			user.setUser_id(rs.getString("user_id"));
			user.setUser_pw(rs.getString("user_pw"));
			user.setUser_name(rs.getString("user_name"));
			user.setUser_age(rs.getInt("user_age"));
			user.setUser_phone(rs.getString("user_phone"));
			user.setUser_gender(rs.getString("user_gender"));
			user.setReg_date(rs.getDate("reg_date"));
			
			 return user; 
		}
		
		
		public User viewTitle(String title) throws SQLException {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "insert into user(user_id,user_pw,user_name,user_age,user_phone,user_gender) " + 
					" values (?, ?, ?, ?, ?, ?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "user_id");
			pstmt.setString(2, "user_pw" );
			pstmt.setString(3, "user_name" );
			pstmt.setString(4, "user_age" );
			pstmt.setString(5, "user_phone" );
			pstmt.setString(6, "user_gender" );
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {			
				user = makeUser(rs);
				pstmt.close();
				return user;
			}else {
			return null;
		}
		
	}

	public User selectOneById(String user_id) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		
		String sql = "select * from user where user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
		User user = makeUser(rs);
		
		return user;
		}else {
			return null;
		}
	}

	public void registUser(String user_id, String user_pw, String user_name, String user_age, String user_phone,
			String user_gender) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		String sql = "insert into user(user_id,user_pw,user_name,user_age,user_phone,user_gender) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user_id);
		pstmt.setString(2, user_pw);
		pstmt.setString(3, user_name);
		pstmt.setString(4, user_age);
		pstmt.setString(5, user_phone);
		pstmt.setString(6, user_gender);
		int result = pstmt.executeUpdate();
		if(result > 0) {
			conn.setAutoCommit(true);
		}
		pstmt.close();		
	}

}
