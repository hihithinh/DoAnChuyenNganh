package bsh.uit.core.entities;

import java.io.Serializable;

public class Rating implements Serializable{

	private static final long serialVersionUID = 1L;

	//Rating ID
	private String id;
	
	//User dang Rating
	private User user;
	
	//Project duoc Rating
	private Project project;
	
	//So sao Project duoc Rating
	private int point;
	
	//Mo ta cho Rating
	private String description;
	
	//Cha cua Rating do
	private Rating parent;
	
	//Loai Rating
	private int type;
	
	//Ngay dang Rating
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Rating getParent() {
		return parent;
	}

	public void setParent(Rating parent) {
		this.parent = parent;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Datetime getCreated_day() {
		return created_day;
	}

	public void setCreated_day(Datetime created_day) {
		this.created_day = created_day;
	}
}
