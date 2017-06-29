package bsh.uit.Action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bsh.uit.core.entities.Project;
import bsh.uit.core.entities.Project_Detail;
import bsh.uit.core.entities.User;
import bsh.uit.core.entities.Video;
import bsh.uit.core.mgr.DetailMgr;
import bsh.uit.core.mgr.ProjectMgr;
import bsh.uit.core.mgr.VideoMgr;

public class ProjectAction  extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String user;
	private ProjectMgr projectMgr;
	private DetailMgr detailMgr;
	private VideoMgr videoMgr;
	private ArrayList<Project> lstProject;
	private ArrayList<Project_Detail> lstDetail;
	private String pid;
	private Video backingTrack;
	private Project newProject;
	
	public ProjectAction() {
		projectMgr = new ProjectMgr();
		detailMgr = new DetailMgr();
		videoMgr = new VideoMgr();
	}
	
	public String getNewsFeed() throws Exception {
		lstProject = new ArrayList<Project>();
		List<Project> lstPJ = new ArrayList<Project>();
		lstPJ = projectMgr.getProjectforNews();
		for(int i = 0; i < lstPJ.size(); i++) {
			lstProject.add(lstPJ.get(i));
		}
		
		lstDetail = new ArrayList<Project_Detail>();
		List<Project_Detail> lstPD = new ArrayList<Project_Detail>();
		for(int i = 0; i < lstPJ.size(); i++) {
			lstPD = detailMgr.getProjectDetail(lstPJ.get(i).getId());
			for(int j = 0; j < lstPD.size(); j++) {
				lstDetail.add(lstPD.get(j));
			}
		}
		return SUCCESS;
	}
	
	public String loadProjectStudio() throws Exception {
		lstProject = new ArrayList<Project>();
		Project project = new Project();
		project = projectMgr.getProjectbyId(pid);
		lstProject.add(project);
		
		return SUCCESS;
	}
	
	public String createProject() throws Exception {
		if(backingTrack == null){
			return "new";
		}
		backingTrack = videoMgr.addVideo(backingTrack);
		newProject = projectMgr.addProject(newProject);
		Project_Detail bktDetail = new Project_Detail();
		bktDetail.setOrder(0);
		bktDetail.setProject(newProject);
		bktDetail.setTime(0);
		bktDetail.setVideo(backingTrack);
		bktDetail.setVideo_type(0);
		bktDetail.setVolume(100);
		detailMgr.addProject_Detail(bktDetail);
		pid = newProject.getId();
		return SUCCESS;
	}
	
	public String doCombile() {
		//Giao tiep giua shell va java
		//su dung bien pid
		try {
            String command = "cd /home/tomcat/upload/scripts/ && ./combine.sh " + pid + " > log_combile 2>&1 &";
            Process p = Runtime.getRuntime().exec(new String[]{"bash","-c",command});    
        } 

        catch (Exception e) {
            e.printStackTrace();
        }
		System.out.println("do combile");
		return SUCCESS;
	}

	public ArrayList<Project> getLstProject() {
		return lstProject;
	}
	
	public ArrayList<Project_Detail> getLstDetail() {
		return lstDetail;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return pid;
	}

	public Video getBackingTrack() {
		return backingTrack;
	}

	public void setBackingTrack(Video backingTrack) {
		this.backingTrack = backingTrack;
	}

	public Project getNewProject() {
		return newProject;
	}

	public void setNewProject(Project newProject) {
		this.newProject = newProject;
	}
	
}
