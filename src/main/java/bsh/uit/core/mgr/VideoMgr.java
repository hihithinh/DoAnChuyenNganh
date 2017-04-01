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
}
