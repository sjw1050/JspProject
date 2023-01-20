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
import user_board.dto.UserBoard;

public class BoardDao {
	List<UserBoard> boardList = null;
	UserBoard ub;
	User user;
	// 싱글톤
	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	private BoardDao() {
	}

	public List<UserBoard> viewAll() throws SQLException {
		boardList = new ArrayList<UserBoard>();
		Connection conn = ConnectionProvider.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select board_num, ub.user_num, board_title, board_content, picture_path,  board_date, u.user_name from userboard ub, user u where ub.user_num = u.user_num order by board_num";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			UserBoard bd = makeBoard(rs);
			boardList.add(bd);
		}
		stmt.close();
		return boardList;
	}

	public UserBoard makeBoard(ResultSet rs) throws SQLException {
		UserBoard board = new UserBoard();
		board.setBoard_num(rs.getInt("board_num"));
		board.setUser_name(rs.getString("user_name"));
		board.setBoard_title(rs.getString("board_title"));
		board.setBoard_content(rs.getString("board_content"));
		board.setPicture_path(rs.getString("picture_path"));
		board.setBoard_date(rs.getDate("board_date"));
		board.setUser_num(rs.getInt("user_num"));

		return board;
	}
	
	public User makeUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUser_id(rs.getString("user_id"));
		user.setUser_pw(rs.getString("user_pw"));
		user.setUser_name(rs.getString("user_name"));
		user.setUser_age(rs.getInt("user_age"));
		user.setUser_gender(rs.getString("user_gender"));
		user.setUser_phone(rs.getString("user_phone"));
		user.setUser_num(rs.getInt("user_num"));

		return user;
	}

	public UserBoard viewBoard(String board_num) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
			String sql = "select board_num, ub.user_num, board_title, board_content, picture_path, board_date, u.user_name from userboard ub inner join user u using(user_num) where ub.board_num = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_num);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {			
				ub = makeBoard(rs);
				pstmt.close();
				return ub;
			}else {
				pstmt.close();
				return null;
			}	

	}
	

	public List<UserBoard> searchDate(String date) throws SQLException {
		boardList = new ArrayList<UserBoard>();
		Connection conn = ConnectionProvider.getConnection();
		String sql = "select board_num, ub.user_num, board_title, board_content, picture_path, board_date, u.user_name from userboard ub inner join user u using(user_num) where ub.board_date = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, date);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			UserBoard bd = makeBoard(rs);
			boardList.add(bd);
		}
		pstmt.close();
		return boardList;
	}

	public List<UserBoard> searchWriter(String writer) throws SQLException {
		boardList = new ArrayList<UserBoard>();
		Connection conn = ConnectionProvider.getConnection();
		String sql = "select board_num, ub.user_num, board_title, board_content, picture_path, board_date, u.user_name from userboard ub inner join user u  using(user_num) where u.user_name like ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%"+writer+"%");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			UserBoard bd = makeBoard(rs);
			boardList.add(bd);
		}
		pstmt.close();
		return boardList;
	}

	public List<UserBoard> searchTC(String tc) throws SQLException {
		boardList = new ArrayList<UserBoard>();
		Connection conn = ConnectionProvider.getConnection();
		String sql = "select board_num, ub.user_num, board_title, board_content, picture_path, board_date, u.user_name from userboard ub inner join user u using(user_num) where board_title like ? or board_content like ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%"+tc+"%");
		pstmt.setString(2, "%"+tc+"%");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			UserBoard bd = makeBoard(rs);
			boardList.add(bd);
		}
		pstmt.close();
		return boardList;
	}
	
	public List<User> allUser () throws SQLException{
		List<User> ulist = new ArrayList<User>();
		Connection conn = ConnectionProvider.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select * from user";		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			User user = makeUser(rs);
			ulist.add(user);
		}
		stmt.close();
		return ulist;
		
	}

	public void modiBoard(UserBoard userboard) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		String sql = "update userboard set board_title = ?, board_content = ?, picture_path = ? where board_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		conn.setAutoCommit(false);
		pstmt.setString(1, userboard.getBoard_title());
		pstmt.setString(2, userboard.getBoard_content());
		pstmt.setString(3, userboard.getPicture_path());
		pstmt.setInt(4, userboard.getBoard_num());
		int result = pstmt.executeUpdate();
		System.out.println(result);
		if(result > 0) {
			conn.setAutoCommit(true);
		}
		pstmt.close();
	}

	public void deleteBoard(String board_num) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		String sql = "delete from userboard where board_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		conn.setAutoCommit(false);
		pstmt.setString(1, board_num);
		int result = pstmt.executeUpdate();
		if(result > 0) {
			conn.setAutoCommit(true);
		}
		pstmt.close();
		
	}

	public void saveBoard(UserBoard userboard) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		System.out.println(userboard.getPicture_path());
		if(userboard.getPicture_path().equals("upload/null")) {
			String sql = "insert into userboard(user_num, board_title,board_content) values(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userboard.getUser_num());
			pstmt.setString(2, userboard.getBoard_title());
			pstmt.setString(3, userboard.getBoard_content());
			
			pstmt.executeUpdate();
			pstmt.close();
			
		}else {
		String sql = "insert into userboard(user_num, board_title,board_content, picture_path) values(?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, userboard.getUser_num());
		pstmt.setString(2, userboard.getBoard_title());
		pstmt.setString(3, userboard.getBoard_content());
		pstmt.setString(4, userboard.getPicture_path());
		
		pstmt.executeUpdate();
		pstmt.close();
		}
	}

	public User selectUser(int user_num) throws SQLException {
		System.out.println("user_num>>>>" + user_num);
		Connection conn = ConnectionProvider.getConnection();
		String sql = "select * from user where user_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user_num);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {			
			user = makeUser(rs);
			pstmt.close();
			return user;
		}else {
			pstmt.close();
			return null;
		}	
	}

}
