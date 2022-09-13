<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>To Do</title>
</head>
<body>
	<h1>TODO it!</h1>
	<hr />
	<form action="ServerClass">
		<input name="todo" placeholder="Type what you have to do" maxlength="20" required />
		<button>+</button>
	</form>
</body>
</html>