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
import user_board.dto.UserBoard;
import user_board.dto.UserComment;
import user_board.service.BoardService;
import user_board.service.CommentService;
import user_board.util.Upload;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	BoardService bs;
	CommentService cs;
	
	@Override
	public void init() throws ServletException {
		bs = BoardService.getInstance();
		cs = CommentService.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		bs = BoardService.getInstance();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String serv = request.getPathInfo();
		String nextPage = "";
		System.out.println(serv);
		if(serv.equals("/writeBoardForm")) {
			int user_num = Integer.parseInt(request.getParameter("user_num"));
			try {
				User user = bs.selectboard(user_num);
				System.out.println(user);
				request.setAttribute("board", user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(user_num);			
			
			nextPage = "/views/writeform.jsp";
		} else if (serv.equals("/writeBoard")) {
			Upload upload = new Upload();
			UserBoard userboard = upload.savePhoto(request);
			System.out.println(userboard);
			try {
				bs.saveBoard(userboard);
				nextPage = "/board/listAll";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (serv.equals("/listAll")) {
			try {
				List<UserBoard> ubList = bs.viewAll();
				System.out.println(ubList);
				request.setAttribute("list", ubList);
				nextPage = "/views/viewAll.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(serv.equals("/selectTitle")) {
			String board_num = request.getParameter("board_num");
			try {
				UserBoard ub = bs.viewBoard(board_num);
				request.setAttribute("ub", ub);
				
				List<UserComment> commentList = cs.viewaBoardComment(board_num);
				System.out.println(commentList);
				request.setAttribute("list", commentList);
				nextPage = "/views/viewTitle.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (serv.equals("/searchDate")) {
			String date = request.getParameter("date");
			try {
				List<UserBoard> list = bs.searchDate(date);
				request.setAttribute("list", list);
				nextPage = "/views/viewDate.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(serv.equals("/searchWriter")) {
			String writer = request.getParameter("writer");
			try {
				List<UserBoard> list = bs.searchWriter(writer);
				request.setAttribute("list", list);
				nextPage = "/views/viewWriter.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} else if (serv.equals("/searchTC")) {
			String tc = request.getParameter("tc");
			System.out.println(tc);
			try {
				List<UserBoard> list = bs.searchTC(tc);
				request.setAttribute("list", list);
				nextPage = "/views/viewTC.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(serv.equals("/modiBoardForm")) {
			String board_num = request.getParameter("num");
			try {
				UserBoard ub = bs.viewBoard(board_num);
				request.setAttribute("ub", ub);
				System.out.println(ub);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("/views/modiform.jsp");
			dispatch.forward(request, response);
			return;
			
		} else if(serv.equals("/modiBoard")) {
			Upload upload = new Upload();
			UserBoard userboard = upload.savePhoto(request);
			try {
				System.out.println(userboard);
				bs.modiBoard(userboard);
				nextPage = "/board/listAll";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} else if (serv.equals("/deleteBoard")) {
			String board_num = request.getParameter("num");
			try {
				bs.deleteBoard(board_num);
				List<UserBoard> ubList = bs.viewAll();
				request.setAttribute("list", ubList);
				nextPage = "/views/viewAll.jsp";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (serv.equals("/comment_insert")) {
			String board_num = request.getParameter("board_num");
			String user_num = request.getParameter("user_num");
			String comment = request.getParameter("comment");
//			System.out.println("board_num >>>" +  board_num);
			System.out.println("user_num >>>" + user_num);
//			System.out.println("comment >>>"  + comment);

			try {
				cs.saveComment(board_num, user_num, comment);
				UserBoard ub = bs.viewBoard(board_num);
				request.setAttribute("ub", ub);
				
				List<UserComment> commentList = cs.viewaBoardComment(board_num);
				System.out.println(commentList);
				if(commentList != null) {	
					request.setAttribute("list", commentList);
				}		
				
				nextPage = "/views/viewTitle.jsp";				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (serv.equals("/modiComment") ) {
			String board_num = request.getParameter("board_num");
			String comment = request.getParameter("comment");
			String comment_id = request.getParameter("comment_id");
			try {
				cs.modiComment(board_num, comment, comment_id);
				UserBoard ub = bs.viewBoard(board_num);
				request.setAttribute("ub", ub);
				
				List<UserComment> commentList = cs.viewaBoardComment(board_num);
				System.out.println(commentList);
				if(commentList != null) {	
					request.setAttribute("list", commentList);
				}		
				
				nextPage = "/views/viewTitle.jsp";					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(serv.equals("/deleteComment")) {
			String comment_id = request.getParameter("comment_id");
			try {
				cs.deleteComment(comment_id);		
				
				nextPage = "/board/selectTitle";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}

}
