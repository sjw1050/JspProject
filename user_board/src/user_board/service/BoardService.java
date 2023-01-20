package user_board.service;

import java.sql.SQLException;
import java.util.List;

import user_board.dao.BoardDao;
import user_board.dto.User;
import user_board.dto.UserBoard;

public class BoardService {
	BoardDao bd = BoardDao.getInstance();

	// 싱글톤
	private static BoardService instance = new BoardService();

	public static BoardService getInstance() {
		return instance;
	}

	private BoardService() {
	}

	public void writeBoard() {
		
	}

	public List<UserBoard> viewAll() throws SQLException {
		return bd.viewAll();
	}

	public UserBoard viewBoard(String board_num) throws SQLException {
		return bd.viewBoard(board_num);
	}

	public List<UserBoard> searchDate(String date) throws SQLException {
		return bd.searchDate(date);
	}

	public List<UserBoard> searchWriter(String writer) throws SQLException {
		return bd.searchWriter(writer);
	}

	public List<UserBoard> searchTC(String tc) throws SQLException {
		return bd.searchTC(tc);
	}	
	

	public void modiBoard(UserBoard userboard) throws SQLException {
		bd.modiBoard(userboard);
	}

	public void deleteBoard(String board_num) throws SQLException {
		bd.deleteBoard(board_num);
	}

	public void saveBoard(UserBoard userboard) throws SQLException {
		bd.saveBoard(userboard);
	}

	public User selectboard(int user_num) throws SQLException {
		return bd.selectUser(user_num);
	}
}
