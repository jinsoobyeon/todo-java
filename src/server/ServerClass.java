package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.dao.TodoDAO;

@WebServlet("/ServerClass")
public class ServerClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServerClass() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String todo = request.getParameter("todo");
		
		TodoDAO todoDAO = new TodoDAO();
		int result = todoDAO.insertTodo(todo);
		
		if (result == 1) {
			response.getWriter().append("Served at: ").append("INSERT success!!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
