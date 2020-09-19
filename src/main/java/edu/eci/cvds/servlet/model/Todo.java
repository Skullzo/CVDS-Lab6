package edu.eci.cvds.servlet.model;

public class Todo {
	public int userId;
	public int id;
	public String title;
	public Object completed;
	public Todo() {
		
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public Object getCompleted() {
		return completed;
	}
	public void setCompleted(Object completed) {
		this.completed = completed;
	}
	
	@Override
	public String toString() {
		return "Todo [userId=" + userId + ", id=" + id + ", title=" + title + ", completed=" + completed + "]";
	}
}