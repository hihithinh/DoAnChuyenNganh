package bsh.uit.Action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bsh.uit.core.entities.Project;
import bsh.uit.core.entities.Project_Detail;
import bsh.uit.core.entities.User;
import bsh.uit.core.entities.Video;
import bsh.uit.core.mgr.DetailMgr;
import bsh.uit.core.mgr.UserMgr;
import bsh.uit.core.mgr.VideoMgr;

public class VideoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private VideoMgr videoMgr;
	private DetailMgr detailMgr;
	private UserMgr userMgr;
	private String PID;
	private int Type;
	private String gettingUser;
	private String currUser;
	private ArrayList<Project_Detail> lstDetail;
	private ArrayList<Video> lstVideo;
	
	public VideoAction() throws ClassNotFoundException, SQLException {
		videoMgr = new VideoMgr();
		detailMgr = new DetailMgr();
		userMgr = new UserMgr();
	}
	
	public String getProjectVideo() throws Exception {
		lstDetail = new ArrayList<Project_Detail>();
		List<Project_Detail> lstPD = new ArrayList<Project_Detail>();
		lstPD = detailMgr.getProjectDetail(PID);
		for(int i = 0; i < lstPD.size(); i++) {
			if(lstPD.get(i).getVideo_type()== Type)
				lstDetail.add(lstPD.get(i));
		}
		return SUCCESS;
	}
	
	public String getProjectbyUserId() throws Exception {
		lstVideo = new ArrayList<Video>();
		List<Video> lstVI = new ArrayList<Video>();
		User usr = userMgr.getUserbyAccount(gettingUser);
		lstVI = videoMgr.getVideobyUserId(usr.getId());
		for(int i = 0; i < lstVI.size(); i++) {
			if(currUser == gettingUser) {
				lstVideo.add(lstVI.get(i));
			} else if(lstVI.get(i).getStatus() != -1) {
				lstVideo.add(lstVI.get(i));
			}
		}
		return SUCCESS;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public void setType(int type) {
		Type = type;
	}
	
	public ArrayList<Project_Detail> getLstDetail() {
		return lstDetail;
	}

	public ArrayList<Video> getLstVideo() {
		return lstVideo;
	}

	public void setGettingUser(String gettingUser) {
		this.gettingUser = gettingUser.split("\\?")[0];;
	}

	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}
	
	
}
