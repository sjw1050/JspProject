package dbcp;
// connection pool 생성 및 초기화 및 설정

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

//@WebServlet(loadOnStartup = 1)
public class DBCPinit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/world?"
					+ "useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
			String user = "root";
			String pw = "admin";
//			String sql = "select d.date, w.weather, d.title, d.content from diary d, weather w\r\n" + 
//					"where d.weather_id = w.weather_id order by date desc;";

			// connection 생성 객체
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(url, user, pw);

			// 커넥션을 풀에 담기 위한 객체 생성
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1");

			// 커넥션 풀에 적용할 설정 객체 생성
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);
			
			//풀러블 커넥션과 설정 파일을 이용하여 커넥션 풀 생성
	         GenericObjectPool<PoolableConnection> connectionPool = 
	               new GenericObjectPool<>(poolableConnFactory, poolConfig);
	         poolableConnFactory.setPool(connectionPool);
	         
	       //커넥션 풀 제공 드라이버 객체 동적 생성
	         Class.forName("org.apache.commons.dbcp2.PoolingDriver");
	         
	       //커넥션 풀 드라이버 생성 
	         PoolingDriver driver = 
	               (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
	         
	       //드라이버에 커넥션 풀 이름으로 등록
	         driver.registerPool("pool", connectionPool);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
