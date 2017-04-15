package bsh.uit.Action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private File fileDoc;
	private String fileDocFileName;
	private String fileDocContentType;
        
	@Override
	public String execute() {
		// Location to store the uploaded file in our desired path
		String targetPath = "E:/NPT";
		File fileToCreate = new File(targetPath, "abc.pdf");
    	try {
    		FileUtils.copyFile(this.fileDoc, fileToCreate);
    	} catch (IOException e)  {
    		e.printStackTrace();
    		addActionError(e.getMessage());
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
}
