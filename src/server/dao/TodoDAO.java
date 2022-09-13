package server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TodoDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "system";
	String password = "oracle";
	
	public TodoDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int insertTodo(String todo) {
		int result = 0;
		
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = DriverManager.getConnection(url, id, password);
			String sql = "INSERT INTO todos(todo) VALUES (?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, todo);
			result = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
