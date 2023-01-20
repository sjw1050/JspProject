package user_board.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import dbcp.ConnectionProvider;
import user_board.dto.UserComment;

public class CommentDao {

	UserComment uc;
	// 싱글톤
	private static CommentDao instance = new CommentDao();

	public static CommentDao getInstance() {
		return instance;
	}

	private CommentDao() {
	}

	public List<UserComment> viewComment(String boardnum) throws SQLException {
		List<UserComment> commentList = new ArrayList<UserComment>();
		Connection conn = ConnectionProvider.getConnection();
		String sql = "select c.cm_content, u.user_name, c.comment_id, c.board_num, c.cm_date from comment c inner join user u using(user_num) where c.board_num = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardnum);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			UserComment usercommnet = makeBoardComment(rs);
			commentList.add(usercommnet);
		}
		return commentList;
	}

	private UserComment makeBoardComment(ResultSet rs) throws SQLException {
		UserComment comment = new UserComment();
		comment.setComment_id(rs.getInt("comment_id"));
		comment.setBoard_num(rs.getInt("board_num"));
		comment.setCm_content(rs.getString("cm_content"));
		comment.setCm_date(rs.getDate("cm_date"));
		comment.setUser_name(rs.getString("user_name"));
	
		return comment;
	}

	public void saveComment(String board_num, String user_num, String comment) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		String sql = "insert into comment(board_num, cm_content, user_num)  values(?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board_num);
		pstmt.setString(2, comment);
		pstmt.setString(3, user_num);
		int result = pstmt.executeUpdate();
		System.out.println(result);
		
		pstmt.close();
	}

	public void modiComment(String board_num, String comment, String comment_id) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		String sql = "update comment set cm_content = ? where board_num = ? and comment_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, comment);
		pstmt.setString(2, board_num);
		pstmt.setString(3, comment_id);
		pstmt.executeUpdate();
		pstmt.close();
	}

	public void deleteComment(String comment_id) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		String sql = "delete from comment where comment_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, comment_id);
		pstmt.executeUpdate();
		pstmt.close();
		
	}
}
