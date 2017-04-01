package bsh.uit.core.mgr;

import java.util.List;

import bsh.uit.core.dao.ProjectDAO;
import bsh.uit.core.entities.Project;

public class ProjectMgr {
	private ProjectDAO projectDao;
	private List<Project> lstProject;
	
	public ProjectMgr() {
		projectDao = new ProjectDAO();
	}
	
	public List<Project> getProjectforNews() throws Exception {
		return projectDao.getAllProject();
	}
	
	public Project getProjectbyId(String id) throws Exception {
		return projectDao.getProjectbyId(id);
	}
}
