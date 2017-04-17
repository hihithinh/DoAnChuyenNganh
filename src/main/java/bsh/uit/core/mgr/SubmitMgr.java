package bsh.uit.core.mgr;

import java.util.List;

import bsh.uit.core.dao.SubmitDAO;
import bsh.uit.core.entities.Submit_Queue;

public class SubmitMgr {
	
	SubmitDAO submitDao;
	
	public SubmitMgr() {
		submitDao = new SubmitDAO();
	}
	
	public List<Submit_Queue> getSubmitbyId(String project_id, String video_id) throws Exception {
		return submitDao.getSubmitbyId(project_id, video_id);
	}
	
	public Submit_Queue addSubmit_Queue(Submit_Queue submit) throws Exception {
		return submitDao.addSubmit_Queue(submit);
	}
	
	public Submit_Queue updateSubmit_Queue(Submit_Queue submit) throws Exception {
		return submitDao.updateSubmit_Queue(submit);
	}
	
	public String deleteSubmitbyProjectId(String pid) throws Exception {
		return submitDao.deleteSubmitbyProjectId(pid);
	}
	
	public String deleteSubmitbyVideoId(String vid) throws Exception {
		return submitDao.deleteSubmitbyVideoId(vid);
	}
}
