package bsh.uit.core.entities;

import java.io.Serializable;
import java.util.Date;

public class Submit_Queue implements Serializable{

	private static final long serialVersionUID = 1L;

	//Project ma Submit gui den
	private Project project;
	
	//Video ma Submit gui di
	private Video video;
	
	//Mo ta cua Submit
	private String description;
	
	//Am luong cua Video
	private int volume;
	
	//Ngay gui Submit
	private Date created_day;

	private int status;
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public Date getCreated_day() {
		return created_day;
	}

	public void setCreated_day(Date created_day) {
		this.created_day = created_day;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
