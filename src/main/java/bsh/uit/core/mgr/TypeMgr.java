package bsh.uit.core.mgr;

import bsh.uit.core.dao.TypeDAO;
import bsh.uit.core.entities.Type;

public class TypeMgr {
	
	TypeDAO typeDao;
	
	public TypeMgr() {
		typeDao = new TypeDAO();
	}
	
	public Type getTypebyId(String id) throws Exception {
		return typeDao.getTypebyId(id);
	}
	
	public Type addType(Type type) throws Exception {
		return typeDao.addType(type);
	}
	
	public Type updateType(Type type) throws Exception {
		return typeDao.updateType(type);
	}
	
	public String deleteType(String id) throws Exception {
		return typeDao.deleteType(id);
	}
}
