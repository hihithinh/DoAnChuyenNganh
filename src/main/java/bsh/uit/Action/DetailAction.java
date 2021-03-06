package bsh.uit.Action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bsh.uit.core.entities.Project_Detail;
import bsh.uit.core.mgr.DetailMgr;

public class DetailAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private DetailMgr detailMgr;
	private ArrayList<Project_Detail> lstDetail;
	private String pid;
	private Project_Detail detail;
	
	public DetailAction() {
		detailMgr = new DetailMgr();
	}
	
	public String getProjectDetail() throws Exception {
		lstDetail = new ArrayList<Project_Detail>();
		List<Project_Detail> lstPD = new ArrayList<Project_Detail>();
		lstPD = detailMgr.getProjectDetail(pid);
		for(int j = 0; j < lstPD.size(); j++) {
			lstDetail.add(lstPD.get(j));
		}
		return SUCCESS;
	}
	
	public String addYoutubeVideo() throws Exception {
		detailMgr.addProject_Detail(detail);
		return SUCCESS;
	}
	
	public ArrayList<Project_Detail> getLstDetail() {
		return lstDetail;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setDetail(Project_Detail detail) {
		this.detail = detail;
	}

}
