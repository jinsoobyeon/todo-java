<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="server.dao.TodoDAO" %>
<%@ page import="server.dto.TodoDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>To Do</title>
</head>
<body>
	<h1>TODO it!</h1>
	<hr />
	<form action="InsertTodo">
		<input name="todo" placeholder="Type what you have to do" maxlength="20" required />
		<button>+</button>
	</form>
	<%
		TodoDAO todoDAO = new TodoDAO();
		ArrayList<TodoDTO> todos = todoDAO.selectTodos();
		
		if (todos != null) {
			for (int i = 0; i < todos.size(); i++) {
				TodoDTO todoDTO = todos.get(i);
				String todo = todoDTO.getTodo();
				out.print(todo + "<br />");
			}
		}
	%>
	<form action="DeleteAll">
		<button>Clear All</button>
	</form>
</body>
</html>