package user_board.service;

import java.sql.SQLException;
import java.util.List;

import user_board.dao.CommentDao;
import user_board.dto.UserComment;

public class CommentService {
	CommentDao cd = CommentDao.getInstance();
	// 싱글톤
	private static CommentService instance = new CommentService();

	public static CommentService getInstance() {
		return instance;
	}
	public List<UserComment> viewaBoardComment(String boardnum) throws SQLException {
		return cd.viewComment(boardnum);
	}
	public void saveComment(String board_num, String user_num, String comment) throws SQLException {
		cd.saveComment(board_num, user_num, comment);
	}
	public void modiComment(String board_num, String comment, String comment_id) throws SQLException {
		cd.modiComment(board_num, comment, comment_id);
	}
	public void deleteComment(String comment_id) throws SQLException {
		cd.deleteComment(comment_id);
	}
}
