package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bsh.uit.core.entities.Type;
import bsh.uit.core.sql.MySQL;

public class TypeDAO {
	private MySQL mysql;
	
	public TypeDAO() {
		mysql = new MySQL();
	}
	
	public Type SQLtoType(ResultSet resultSet) throws SQLException{
		Type type = new Type();
		while (resultSet.next()){
			type.setCode(resultSet.getString("type_code"));
			type.setDescription(resultSet.getString("description"));
			type.setId(resultSet.getString("type_id"));
			type.setName(resultSet.getString("type_name"));
			type.setStatus(resultSet.getInt("status"));
		}
		return type;
	}
	
	public Type getTypebyId (String id) throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from type where type_id=?");
	        params.add(id);
	        
	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoType(resultSet);
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
	public Type getLastType() throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			
			StringBuilder sql = new StringBuilder();
			sql.append("select * from type "
					+ "order by type_id desc "
					+ "limit 1");
			
		  //execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());

			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoType(resultSet);
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

	public Type addType(Type type) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("insert int type "
					+ "(`TYPE_ID`, `TYPE_CODE`, `TYPE_NAME`, `STATUS`, `DESCRIPTION`) "
					+ "VALUES (?, ?, ?, ?, ?)");
			
			//Get last ID then +1
			Type vid = new Type();
			vid = getLastType();
			String lastId = "T" + String.valueOf(Integer.parseInt(vid.getId().substring(1)) + 1);
			
			params.add(lastId);
			params.add(type.getCode());
			params.add(type.getName());
			params.add(type.getStatus());
			params.add(type.getDescription());

			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			Success = preparedStatement.executeUpdate();
			
			if(Success == 0)
				return null;
			return getTypebyId(lastId);
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
	
	public Type updateType(Type type) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("update type set `TYPE_CODE`=?, `TYPE_NAME`=?, `STATUS`=?, `DESCRIPTION`=? "
					+ "WHERE `TYPE_ID`=?;");

			params.add(type.getCode());
			params.add(type.getName());
			params.add(type.getStatus());
			params.add(type.getDescription());
			params.add(type.getId());
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			Success = preparedStatement.executeUpdate();
			if(Success == 0)
				return null;
			return getTypebyId(type.getId());
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
	
	public String deleteType(String id) throws Exception {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("delete from type where `TYPE_ID`=?");
			params.add(id);
			
			//execute querry with sql and params
			preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
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
