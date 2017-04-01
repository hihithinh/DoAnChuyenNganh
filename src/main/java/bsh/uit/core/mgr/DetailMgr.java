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
}
