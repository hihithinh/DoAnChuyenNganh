package bsh.uit.core.mgr;

import java.util.List;

import bsh.uit.core.dao.VideoDAO;
import bsh.uit.core.entities.Video;

public class VideoMgr {
	private VideoDAO videoDao;
	private List<Video> lstVideo;
	
	public VideoMgr() {
		videoDao = new VideoDAO();
	}
	
	public Video getVideoById(String id) throws Exception {
		return videoDao.getVideobyId(id);
	}
	
	public List<Video> getVideobyUserId(String uid) throws Exception {
		return videoDao.getVideobyUserId(uid);
	}
	
	public Video addVideo(Video video) throws Exception {
		return videoDao.addVideo(video);
	}
	
	public Video updateVideo(Video video) throws Exception {
		return videoDao.updateVideo(video);
	}
	
	public String deleteVideo(String id) throws Exception {
		return videoDao.deleteVideo(id);
	}
}
