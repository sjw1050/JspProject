package user_board.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import user_board.dto.UserBoard;

public class Upload {
	
	public UserBoard savePhoto(HttpServletRequest request) throws IOException {
		String upload_path = "upload/";
		String saveDir = request.getServletContext().getRealPath(upload_path);
		int board_num = 0;
		//File file = new File(saveDir);
//		if(!file.exists()) {
//			file.mkdir();
//		}
		int maxSize = 1024*1024*100;
		String encType= "utf-8";
		System.out.println(saveDir);
		
		MultipartRequest multipartRequest = 
				new MultipartRequest(request, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
		System.out.println("------------------------------------------------------------------------");
		System.out.println("유저번호" + multipartRequest.getParameter("user_num"));
		System.out.println("제목 : " + multipartRequest.getParameter("title") + "<br>"); //null 값을 갖는다.
		System.out.println("내용 : " + multipartRequest.getParameter("content") + "<br>"); //null 값을 갖는다.
		
		
		System.out.println("업로드파일명 : " + multipartRequest.getFilesystemName("file") + "<br>");
		System.out.println("원래파일명 : " + multipartRequest.getOriginalFileName("file") + "<br>");
	    
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		if(multipartRequest.getParameter("num") != null) {
		board_num = Integer.parseInt(multipartRequest.getParameter("num"));
		}
		int user_num = 0;
		user_num = Integer.parseInt(multipartRequest.getParameter("user_num"));
		String filename = multipartRequest.getFilesystemName("file");
	    request.setAttribute("fileName", filename);
	    
	    if(board_num > 0) {
	    	UserBoard board = new UserBoard(board_num, user_num, null, title, content, upload_path+filename, null);
	    	return board;
	    }else {
	    	UserBoard board = new UserBoard(0, user_num, null, title, content, upload_path+filename, null);
	    	return board;
	    }		
	    
	}
}
