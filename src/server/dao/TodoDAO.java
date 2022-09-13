package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TodoDAO {
	DataSource dataSource;
	
	public TodoDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public int insertTodo(String todo) {
		int result = 0;
		
		Connection connection;
		PreparedStatement preparedStatement;
		
		try {
			connection = dataSource.getConnection();
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
