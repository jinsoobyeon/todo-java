package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServerClass")
public class ServerClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServerClass() {
    	super();
    }
    
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "system";
	String password = "oracle";
	
	Connection connection;
	PreparedStatement preparedStatement;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String todo = request.getParameter("todo");
		
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, id, password);
			String sql = "INSERT INTO todos(todo) VALUES (?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, todo);
			int result = preparedStatement.executeUpdate();
			
			if (result == 1) {
				response.getWriter().append("Served at: ").append("INSERT success!!");
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
