package bsh.uit.core.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ObjectVO<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<T> lstObject;
	private Integer count;
	
	public List<T> getLstObject() {
		return lstObject == null ? new ArrayList<T>() : lstObject;
	}
	public void setLstObject(List<T> lstObject) {
		this.lstObject = lstObject;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
