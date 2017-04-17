package bsh.uit.core.mgr;

import java.util.List;

import bsh.uit.core.dao.NotifyDAO;
import bsh.uit.core.entities.Notify;

public class NotifyMgr {
	
	NotifyDAO notifyDao;
	
	public NotifyMgr() {
		notifyDao = new NotifyDAO();
	}
	
	public Notify getNotifybyId(String id) throws Exception {
		return notifyDao.getNotifybyId(id);
	}
	
	public List<Notify> getNotifybyProjectId(String pid) throws Exception {
		return notifyDao.getNotifybyProjectId(pid);
	}
	
	public Notify addNotify(Notify notify) throws Exception {
		return notifyDao.addNotify(notify);
	}
	
	public Notify updateNotify(Notify notify) throws Exception {
		return notifyDao.updateNotify(notify);
	}
	
	public String deleteNotify(String id) throws Exception {
		return notifyDao.deleteNotify(id);
	}
}
