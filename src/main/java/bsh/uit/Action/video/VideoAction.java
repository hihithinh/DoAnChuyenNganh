package bsh.uit.Action.video;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bsh.uit.core.entities.Project;
import bsh.uit.core.entities.Project_Detail;
import bsh.uit.core.mgr.DetailMgr;
import bsh.uit.core.mgr.VideoMgr;

public class VideoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private VideoMgr videoMgr;
	private DetailMgr detailMgr;
	private String PID;
	private int Type;
	private ArrayList<Project_Detail> lstDetail;
	
	public VideoAction() {
		videoMgr = new VideoMgr();
		detailMgr = new DetailMgr();
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

	public void setPID(String pID) {
		PID = pID;
	}

	public void setType(int type) {
		Type = type;
	}
	
	public ArrayList<Project_Detail> getLstDetail() {
		return lstDetail;
	}
}
