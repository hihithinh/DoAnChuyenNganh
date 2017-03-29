package bsh.uit.core.entities;

import java.io.Serializable;

public class Video implements Serializable{

	private static final long serialVersionUID = 1L;

	//Video ID
	private String id;
	
	//User chu so huu Video
	private User user;
	
	//Youtube code cua Video
	private String link;
	
	//Loai nhac cu cua Video
	private Type instrument;
	
	//Mo ta cho Video
	private String description;
	
	//Trang thai cua Video
	private int status;
	
	//Ngay dang video
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Type getInstrument() {
		return instrument;
	}

	public void setInstrument(Type instrument) {
		this.instrument = instrument;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
