package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bsh.uit.core.entities.User;
import bsh.uit.core.sql.MySQL;

public class UserDAO {
    private TypeDAO typeDao;
    private MySQL mysql;
    
    public UserDAO () {
    	mysql = new MySQL();
		typeDao = new TypeDAO();
    }
    
    public User SQLtoUser(ResultSet resultSet) throws Exception {
    	User user = new User();
    	try{
    		while (resultSet.next()){
		    	user.setAccount(resultSet.getString("user_account"));
		    	user.setAddress(resultSet.getString("address"));
		    	user.setAvatar(resultSet.getString("avatar"));
		    	//user.setCreated_day(resultSet.getDate("user_account"));
		    	user.setFbtoken(resultSet.getString("fb_token"));
		    	user.setGgtoken(resultSet.getString("gg_token"));
		    	user.setId(resultSet.getString("user_id"));
		    	user.setName(resultSet.getString("user_name"));
		    	user.setPassword(resultSet.getString("password"));
		    	user.setStatus(resultSet.getInt("status"));
		    	user.setUser_type(typeDao.getTypebyId(resultSet.getString("user_type")));
    		}
    	} catch (Exception e) {
    		throw e;
    	}
    	return user;
    }
    
    public User loginUser(String account, String password) throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
	    	StringBuilder sql = new StringBuilder();
	    	List<Object> params = new ArrayList<Object>();
	        sql.append("select * from user where user_account=?");
	        sql.append(" and password=?");
	        params.add(account);
	        params.add(password);
	        
	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoUser(resultSet);
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
    
    public User getUserbyId(String id) throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			
    		StringBuilder sql = new StringBuilder();
	    	List<Object> params = new ArrayList<Object>();
	        sql.append("select * from user where user_id=?");
	        params.add(id);
	        
	      //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoUser(resultSet);
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
