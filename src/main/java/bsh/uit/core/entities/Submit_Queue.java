package bsh.uit.core.entities;

import java.io.Serializable;

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
	private Datetime created_day;

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

	public Datetime getCreated_day() {
		return created_day;
	}

	public void setCreated_day(Datetime created_day) {
		this.created_day = created_day;
	}
}
