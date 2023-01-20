package user_board.service;

import java.sql.SQLException;
import java.util.List;

import user_board.dao.UserDao;
import user_board.dto.User;

public class UserService {
	UserDao userDao = UserDao.getInstance();

		// 싱글톤
	private static UserService instance = new UserService();

	public static UserService getInstance() {
		return instance;
	}
	
	private UserService() {
	}

	public User findUserById(String user_id) throws SQLException {
		
		return userDao.selectOneById(user_id);
	}

	public void registUser(String user_id, String user_pw, String user_name, String user_age, String user_phone,
			String user_gender) throws SQLException {
		userDao.registUser(user_id, user_pw, user_name, user_age, user_phone, user_gender);
		
	}
	
	public List<User> allUser() throws SQLException {
		return userDao.allUser();
	}

}
