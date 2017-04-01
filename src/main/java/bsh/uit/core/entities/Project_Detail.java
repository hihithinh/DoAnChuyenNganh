package bsh.uit.core.entities;

import java.io.Serializable;

public class Project_Detail implements Serializable{

	private static final long serialVersionUID = 1L;

	//Project cua Detail
	private Project project;
	
	//Video cua Detail
	private Video video;
	
	//Am luong cua Video
	private int volume;
	
	//Loai video
	private int video_type;
	
	//Thu tu sap xep video
	private int order;
	

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

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getVideo_type() {
		return video_type;
	}

	public void setVideo_type(int video_type) {
		this.video_type = video_type;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
