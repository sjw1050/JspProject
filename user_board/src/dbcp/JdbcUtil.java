package dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	//db 연결 및 처리를 위해 생성한 IO 객체 닫아주는 메소드들 모음
	public static void close(ResultSet rs) {
	      if (rs != null) {
	         try {
	            rs.close();
	         } catch (SQLException ex) {
	         }
	      }
	   }

	   public static void close(Statement stmt) {
	      if (stmt != null) {
	         try {
	            stmt.close();
	         } catch (SQLException ex) {
	         }
	      }
	   }

	   public static void close(Connection conn) {
	      if (conn != null) {
	         try {
	            conn.close();
	         } catch (SQLException ex) {
	         }
	      }
	   }

	   public static void rollback(Connection conn) {
	      if (conn != null) {
	         try {
	            conn.rollback();
	         } catch (SQLException ex) {
	         }
	      }
	   }
}
