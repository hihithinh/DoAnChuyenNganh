package bsh.uit.core.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bsh.uit.core.entities.Project;
import bsh.uit.core.sql.MySQL;

public class ProjectDAO {
	private MySQL mysql;
	private TypeDAO typeDao;
	private UserDAO userDao;
	
	public ProjectDAO() {
		mysql = new MySQL();
		typeDao = new TypeDAO();
		userDao = new UserDAO();
	}
	
	public List<Project> SQLtoProject(ResultSet resultSet) throws Exception {
		List<Project> project = new ArrayList<Project>();
		int i=0;
		while (resultSet.next()){
			project.get(i).setArtist_name(resultSet.getString("artist_name"));
			project.get(i).setCreated_day(null);
			project.get(i).setDescription(resultSet.getString("description"));
			project.get(i).setId(resultSet.getString("id"));
			project.get(i).setMusic_type(typeDao.getTypebyId(resultSet.getString("music_type")));
			project.get(i).setName(resultSet.getString("project_name"));
			project.get(i).setNeedDrum(resultSet.getInt("needDrum"));
			project.get(i).setNeedGuitar(resultSet.getInt("needGuitar"));
			project.get(i).setNeedHarmonica(resultSet.getInt("needHarmonica"));
			project.get(i).setNeedOrgan(resultSet.getInt("needOrgan"));
			project.get(i).setNeedPiano(resultSet.getInt("needPiano"));
			project.get(i).setNeedUkulele(resultSet.getInt("needUkulele"));
			project.get(i).setNeedViolon(resultSet.getInt("needViolon"));
			project.get(i).setNeedVocal(resultSet.getInt("needVocal"));
			project.get(i).setStatus(resultSet.getInt("status"));
			project.get(i).setUpdate_day(null);
			project.get(i).setUser(userDao.getUserbyId(resultSet.getString("user_id")));
			i++;
		}
		return project;
	}
	
	public Project getProjectbyId(String id) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from project where project_id=?");
	        params.add(id);
			
			return SQLtoProject(mysql.doSQLQuery(sql.toString(), params)).get(0);
		} catch (Exception e) {
            throw e;
		}
	}
	
	public List<Project> getAllProject() throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from project where status=? or status=?");
	        params.add(0);
	        params.add(1);
	        sql.append(" order by update_day desc");
			return SQLtoProject(mysql.doSQLQuery(sql.toString(), params));
		} catch (Exception e) {
            throw e;
		}
	}
}
