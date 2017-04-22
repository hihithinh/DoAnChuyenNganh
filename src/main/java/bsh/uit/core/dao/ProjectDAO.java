package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
			project.setCreated_day(resultSet.getTimestamp("Created_day"));
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
			project.setUpdate_day(resultSet.getTimestamp("update_day"));
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
	
	public Project getLastProject() throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from project "
					+ "order by project_id desc "
					+ "limit 1");
			
		  //execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());

			resultSet = preparedStatement.executeQuery();
			
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

	public Project addProject(Project project) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("insert into project "
					+ "(`PROJECT_ID`, `PROJECT_NAME`, `USER_ID`, `ARTIST_NAME`, `STATUS`, `MUSIC_TYPE`, `needPiano`, "
					+ "`needOrgan`, `needVocal`, `needGuitar`, `needViolon`, `needDrum`, `needUkulele`, `needHarmonica`, "
					+ "`CREATED_DAY`, `UPDATE_DAY`, `DESCRIPTION`) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			//Get last ID then +1
			Project vid = new Project();
			vid = getLastProject();
			String lastId = "PRO" + String.valueOf(Integer.parseInt(vid.getId().substring(1)) + 1);
			
			params.add(lastId);
			params.add(project.getName());
			params.add(project.getUser().getId());
			params.add(project.getArtist_name());
			params.add(project.getStatus());
			params.add(project.getMusic_type().getId());
			params.add(project.getNeedPiano());
			params.add(project.getNeedOrgan());
			params.add(project.getNeedVocal());
			params.add(project.getNeedGuitar());
			params.add(project.getNeedViolon());
			params.add(project.getNeedDrum());
			params.add(project.getNeedUkulele());
			params.add(project.getNeedHarmonica());
			params.add(new Date());
			params.add(new Date());
			params.add(project.getDescription());

			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			Success = preparedStatement.executeUpdate();
			
			if(Success == 0)
				return null;
			return getProjectbyId(lastId);
		} catch (Exception e) {
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
	}
	
	public Project updateProject(Project project) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("update project set "
					+ "`PROJECT_NAME`=?, `USER_ID`=?, `ARTIST_NAME`=?, `STATUS`=?, `MUSIC_TYPE`=?, `needPiano`=?, "
					+ "`needOrgan`=?, `needVocal`=?, `needGuitar`=?, `needViolon`=?, `needDrum`=?, `needUkulele`=?, "
					+ "`needHarmonica`=?, `CREATED_DAY`=?, `UPDATE_DAY`=?, `DESCRIPTION`=? "
					+ "WHERE `PROJECT_ID`=?");

			params.add(project.getName());
			params.add(project.getUser().getId());
			params.add(project.getArtist_name());
			params.add(project.getStatus());
			params.add(project.getMusic_type().getId());
			params.add(project.getNeedPiano());
			params.add(project.getNeedOrgan());
			params.add(project.getNeedVocal());
			params.add(project.getNeedGuitar());
			params.add(project.getNeedViolon());
			params.add(project.getNeedDrum());
			params.add(project.getNeedUkulele());
			params.add(project.getNeedHarmonica());
			params.add(project.getCreated_day());
			params.add(new Date());
			params.add(project.getDescription());
			params.add(project.getId());
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			Success = preparedStatement.executeUpdate();
			if(Success == 0)
				return null;
			return getProjectbyId(project.getId());
		} catch (Exception e) {
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
	}
	
	public String deleteProject(String id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("delete from project where `PROJECT_ID`=?");
			params.add(id);
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			Success = preparedStatement.executeUpdate();
			if(Success == 0)
				return "error";
			return "success";
		} catch (Exception e) {
			throw e;
		} finally {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connect != null) {
				connect.close();
			}
		}
	}
}
