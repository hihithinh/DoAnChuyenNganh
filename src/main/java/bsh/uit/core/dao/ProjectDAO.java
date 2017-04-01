package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		List<Project> lstProject = new ArrayList<Project>();
		while (resultSet.next()){
			Project project = new Project();
			project.setArtist_name(resultSet.getString("artist_name"));
			project.setCreated_day(null);
			project.setDescription(resultSet.getString("description"));
			project.setId(resultSet.getString("project_id"));
			project.setMusic_type(typeDao.getTypebyId(resultSet.getString("music_type")));
			project.setName(resultSet.getString("project_name"));
			project.setNeedDrum(resultSet.getInt("needDrum"));
			project.setNeedGuitar(resultSet.getInt("needGuitar"));
			project.setNeedHarmonica(resultSet.getInt("needHarmonica"));
			project.setNeedOrgan(resultSet.getInt("needOrgan"));
			project.setNeedPiano(resultSet.getInt("needPiano"));
			project.setNeedUkulele(resultSet.getInt("needUkulele"));
			project.setNeedViolon(resultSet.getInt("needViolon"));
			project.setNeedVocal(resultSet.getInt("needVocal"));
			project.setStatus(resultSet.getInt("status"));
			project.setUpdate_day(null);
			project.setUser(userDao.getUserbyId(resultSet.getString("user_id")));
			lstProject.add(project);
		}
		return lstProject;
	}
	
	public Project getProjectbyId(String id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from project where project_id=?");
	        params.add(id);
			
	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			
			//Chi lay gia tri dau tien trong List
			return SQLtoProject(resultSet).get(0);
    	} catch (Exception e) {
            throw e;
	    } finally {
			if(resultSet != null) {
				resultSet.close();
			}
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
	}
	
	public List<Project> getAllProject() throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from project where status=? or status=?");
	        params.add(0);
	        params.add(1);
	        sql.append(" order by update_day desc");
	        

	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			resultSet = preparedStatement.executeQuery();

			return SQLtoProject(resultSet);
    	} catch (Exception e) {
            throw e;
	    } finally {
			if(resultSet != null) {
				resultSet.close();
			}
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
	}
}
