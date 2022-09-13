package server.dto;

public class TodoDTO {
	String todo;
	
	public TodoDTO(String todo) {
		this.todo = todo;
	}
	
	public String getTodo() {
		return todo;
	}
}
