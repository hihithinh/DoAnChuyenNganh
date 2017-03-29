package bsh.uit.project;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import bsh.uit.core.entities.Project;
import bsh.uit.core.entities.User;
import bsh.uit.core.mgr.ProjectMgr;

public class ProjectAction  extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String user;
	private ProjectMgr projectMgr;
	private ArrayList<Project> lstProject;
	
	public ProjectAction() {
		projectMgr = new ProjectMgr();
	}
	
	public String getNewsFeed() throws Exception {
		lstProject = new ArrayList<Project>();
		List<Project> lstPJ = new ArrayList<Project>();
		lstPJ = projectMgr.getProjectforNews();
		for(int i = 0; i < lstPJ.size(); i++) {
			lstProject.add(lstPJ.get(i));
		}
		System.out.println("pjname: "+lstProject.get(0).getName());
		return SUCCESS;
	}

	public ArrayList<Project> getLstProject() {
		return lstProject;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
