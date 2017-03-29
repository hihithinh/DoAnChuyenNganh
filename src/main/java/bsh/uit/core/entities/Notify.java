package bsh.uit.core.entities;

import java.io.Serializable;

public class Notify implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Notify ID
	private String id;
	
	//User ma Notify duoc gui den
	private User user;
	
	//Mo ta cho Notify
	private String description;
	
	//Project duoc gui den cung Notify
	private Project project;
	
	//Trang thai cua Notify
	private int status;
	
	//Ngay Notify duoc tao
	private Datetime created_day;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Datetime getCreated_day() {
		return created_day;
	}

	public void setCreated_day(Datetime created_day) {
		this.created_day = created_day;
	}
}
