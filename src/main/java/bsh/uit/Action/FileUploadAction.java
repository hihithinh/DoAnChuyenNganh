package bsh.uit.Action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;


import bsh.uit.core.entities.Video;
import bsh.uit.core.mgr.TypeMgr;
import bsh.uit.core.mgr.UserMgr;
import bsh.uit.core.mgr.VideoMgr;

public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private File fileDoc;
	private String fileDocFileName;
	private String fileDocContentType;
	private String pid;
	private String uid;
	private String instrument;
	private String description;
	private UserMgr userMgr;
	private TypeMgr typeMgr;
	private VideoMgr videoMgr;
	
	public FileUploadAction() throws ClassNotFoundException, SQLException {
		userMgr = new UserMgr();
		typeMgr = new TypeMgr();
		videoMgr = new VideoMgr();
	}

	@Override
	public String execute() throws Exception {
		// Location to store the uploaded file in our desired path
		String targetPath = "E:/NPT/" + pid;
		
		Video video = new Video();
		video.setUser(userMgr.getUserbyId(uid));
		video.setInstrument(typeMgr.getTypebyId(instrument));
		video.setStatus(0);
		video.setDescription(description);
		Video upload = new Video();
		upload = videoMgr.addVideo(video);
		if(upload == null) {
			return ERROR;
		}
		String file[] = fileDocFileName.split("\\.");
		String newfileName = upload.getId() + "." + file[file.length-1];
		File fileToCreate = new File(targetPath, newfileName);
    	try {
    		FileUtils.copyFile(this.fileDoc, fileToCreate);
    	} catch (IOException e)  {
    		e.printStackTrace();
    		addActionError(e.getMessage());
    		throw e;
    	}
    	
    	return SUCCESS;
	}

	public File getFileDoc() {
		return fileDoc;
	}

	public void setFileDoc(File fileDoc) {
		this.fileDoc = fileDoc;
	}

	public String getFileDocFileName() {
		return fileDocFileName;
	}

	public void setFileDocFileName(String fileDocFileName) {
		this.fileDocFileName = fileDocFileName;
	}

	public String getFileDocContentType() {
		return fileDocContentType;
	}

	public void setFileDocContentType(String fileDocContentType) {
		this.fileDocContentType = fileDocContentType;
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	
}
