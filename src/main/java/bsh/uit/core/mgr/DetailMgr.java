package bsh.uit.core.mgr;

import java.util.List;


import bsh.uit.core.dao.DetailDAO;
import bsh.uit.core.entities.Project_Detail;

public class DetailMgr {
	
	private DetailDAO detailDao;
	private List<Project_Detail> lstDetail;
	
	public DetailMgr() {
		detailDao = new DetailDAO();
	}
	
	public List<Project_Detail> getProjectDetail(String PID) throws Exception {
		return detailDao.getDetailbyId(PID, null);
	}
	
	public Project_Detail addProject_Detail(Project_Detail detail) throws Exception {
		return detailDao.addProject_Detail(detail);
	}
	
	public Project_Detail updateProject_Detail(Project_Detail detail) throws Exception {
		return detailDao.updateProject_Detail(detail);
	}
	
	public String deleteDetailbyProjectId(String pid) throws Exception {
		return detailDao.deleteDetailbyProjectId(pid);
	}
	
	public String deleteDetailbyVideoId(String vid) throws Exception {
		return detailDao.deleteDetailbyVideoId(vid);
	}
}
