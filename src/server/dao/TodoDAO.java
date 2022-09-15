package server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import server.dto.TodoDTO;

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
			String sql = "INSERT INTO todos(id, todo) VALUES (sequence.NEXTVAL, ?)";
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
	
	public ArrayList<TodoDTO> selectTodos() {
		ArrayList<TodoDTO> todos = new ArrayList<TodoDTO>();

		Connection connection;
		Statement statement;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String sql = "SELECT * FROM todos";
			resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				String todo = resultSet.getString("todo");
				TodoDTO todoDTO = new TodoDTO(todo);
				todos.add(todoDTO);
			}
			
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return todos;
	}
}
