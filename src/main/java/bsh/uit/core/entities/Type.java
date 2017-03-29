package bsh.uit.core.entities;

import java.io.Serializable;

public class Type implements Serializable{

	private static final long serialVersionUID = 1L;

	//Type ID
	private String id;
	
	//Ma Type
	private String code;
	
	//Ten Type
	private String name;
	
	//Trang thai Type
	private int status;
	
	//Mo ta cho Type
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
