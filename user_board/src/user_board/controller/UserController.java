package user_board.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user_board.dto.User;
import user_board.service.UserService;

@WebServlet("/user/*")
//회원 가입,로그인 
public class UserController extends HttpServlet {
	UserService userService;

	/*
	 * @Override public void init() throws ServletException { us =
	 * UserService.getInstance(); }
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		userService = UserService.getInstance();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String serv = request.getPathInfo();
		String nextPage = "";
		System.out.println(serv);

		// 로그인폼
		if (serv.equals("/loginForm")) {
			nextPage = "/views/user/loginForm.jsp";

			// 로그인
		} else if (serv.equals("/login")) {
			HttpSession session = request.getSession();

			String user_id = request.getParameter("user_id");
			String user_pw = request.getParameter("user_pw");
			String remember_me = request.getParameter("remember_me");
			System.out.println("remember_me>>> " + remember_me);
			try {
				User user = userService.findUserById(user_id);
				if (user != null && user.getUser_pw().equals(user_pw)) {
					user.setUser_pw(null);
					session.setAttribute("user", user);
					nextPage = "/menu.jsp";
				} else {
					String result = "fail";
					request.setAttribute("result", result);
					nextPage = "/views/user/loginForm.jsp";
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 로그아웃
		} else if (serv.equals("/logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			nextPage = "/menu.jsp";

		// 회원가입폼
		} else if (serv.equals("/registForm")) {
			nextPage = "/views/user/registForm.jsp";
		// 회원가입
		} else if (serv.equals("/regist")) {
				String user_id = request.getParameter("user_id");
				String user_pw = request.getParameter("user_pw");
				String user_name = request.getParameter("user_name");
				String user_age = request.getParameter("user_age");
				String user_phone = request.getParameter("user_phone");
				String user_gender = request.getParameter("user_gender");
//				System.out.println("user_id>>> " + user_id);
//				System.out.println("user_pw>>> " + user_pw);
//				System.out.println("user_name>>> " + user_name);
//				System.out.println("user_age>>> " + user_age);
//				System.out.println("user_phone>>> " + user_phone);
				//System.out.println("user_gender>>> " + user_gender);
				try {
					userService.registUser(user_id, user_pw, user_name, user_age, user_phone, user_gender);
					List<User> userList = userService.allUser();
					System.out.println(userList);
					request.setAttribute("users", userList);
					nextPage = "/menu.jsp";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		} else {
			nextPage = "/menu.jsp";
		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
