package bsh.uit.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bsh.uit.core.entities.Project_Detail;
import bsh.uit.core.entities.User;
import bsh.uit.core.sql.MySQL;

public class UserDAO {
    private TypeDAO typeDao;
    private MySQL mysql;
    
    public UserDAO () {
    	mysql = new MySQL();
		typeDao = new TypeDAO();
    }
    
    public List<User> SQLtoUser(ResultSet resultSet) throws Exception {
    	List<User> lstUser = new ArrayList<User>();;
		while (resultSet.next()){
			User user = new User();
			user.setAbility(resultSet.getString("ability"));
	    	user.setAccount(resultSet.getString("user_account"));
	    	user.setAddress(resultSet.getString("address"));
	    	user.setAvatar(resultSet.getString("avatar"));
	    	user.setBirthday(resultSet.getDate("birthday"));
	    	user.setCreated_day(resultSet.getDate("created_day"));
	    	user.setDescription(resultSet.getString("description"));
	    	user.setEmail(resultSet.getString("email"));
	    	user.setFbtoken(resultSet.getString("fb_token"));
	    	user.setGender(resultSet.getString("gender"));
	    	user.setGgtoken(resultSet.getString("gg_token"));
	    	user.setId(resultSet.getString("user_id"));
	    	user.setName(resultSet.getString("user_name"));
	    	user.setPassword(resultSet.getString("password"));
	    	user.setStatus(resultSet.getInt("status"));
	    	user.setUser_type(typeDao.getTypebyId(resultSet.getString("user_type")));

	    	lstUser.add(user);
		}
    	return lstUser;
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
			
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoUser(resultSet).get(0);
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
			
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoUser(resultSet).get(0);
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
    
    public User getUserbyAccount(String account) throws Exception {
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
	        params.add(account);
	        
	      //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoUser(resultSet).get(0);
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
    
    public User getLastUser() throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
			
    		StringBuilder sql = new StringBuilder();
	        sql.append("select * from user "
	        		+ "order by user_id desc "
	        		+ "limit 1;");
	        
	      //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());

	        System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
			return SQLtoUser(resultSet).get(0);
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
    
    public User addUser(User user) throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
	    	StringBuilder sql = new StringBuilder();
	    	List<Object> params = new ArrayList<Object>();
	        sql.append("insert into user"
	        		+ " (`USER_ID`, `USER_ACCOUNT`, `PASSWORD`, `FB_TOKEN`, `GG_TOKEN`, `USER_NAME`, `ADDRESS`, `AVATAR`, `USER_TYPE`, `CREATED_DAY`, `STATUS`, `ABILITY`, `GENDER`, `BIRTHDAY`, `DESCRIPTION`, `EMAIL`)"
	        		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	        
	        //Get last ID then +1
	        User usr = new User();
	        usr = getLastUser();
	        String lastId = "U" + String.valueOf(Integer.parseInt(usr.getId().substring(1)) + 1);
	        
	        params.add(lastId);
	        params.add(user.getAccount());
	        params.add(user.getPassword());
	        params.add(user.getFbtoken());
	        params.add(user.getGgtoken());
	        params.add(user.getName());
	        params.add(user.getAddress());
	        params.add(user.getAvatar());
	        params.add(user.getUser_type().getId());
	        params.add(new Date());
	        params.add(user.getStatus());
	        params.add(user.getAbility());
	        params.add(user.getGender());
	        params.add(user.getBirthday());
	        params.add(user.getDescription());
	        params.add(user.getEmail());
	        
	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			Success = preparedStatement.executeUpdate();
			
			if(Success == 0)
				return null;
			return getUserbyId(lastId);
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
    
    public User updateUser(User user) throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
	    	StringBuilder sql = new StringBuilder();
	    	List<Object> params = new ArrayList<Object>();
	        sql.append("update user set `USER_ACCOUNT`=?, PASSWORD=?, `FB_TOKEN`=?, `GG_TOKEN`=?, "
	        		+ "`USER_NAME`=?, `ADDRESS`=?, `AVATAR`=?, `USER_TYPE`=?, `CREATED_DAY`=?, `STATUS`=? "
	        		+ "`ABILITY`=?, `GENDER`=?, `BIRTHDAY`=?, `DESCRIPTION`=?, `EMAIL`=?"
	        		+ "WHERE `USER_ID`=?");

	        params.add(user.getAccount());
	        params.add(user.getPassword());
	        params.add(user.getFbtoken());
	        params.add(user.getGgtoken());
	        params.add(user.getName());
	        params.add(user.getAddress());
	        params.add(user.getAvatar());
	        params.add(user.getUser_type().getId());
	        params.add(user.getCreated_day());
	        params.add(user.getStatus());
	        params.add(user.getAbility());
	        params.add(user.getGender());
	        params.add(user.getBirthday());
	        params.add(user.getDescription());
	        params.add(user.getEmail());
	        params.add(user.getId());
	        
	        //execute querry with sql and params
	        preparedStatement = connect.prepareStatement(sql.toString());
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			
			System.out.println(preparedStatement.toString());
			Success = preparedStatement.executeUpdate();
			if(Success == 0)
				return null;
			return getUserbyId(user.getId());
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
    
    public String deleteUser(String id, String password) throws Exception {
    	Connection connect = null;
		PreparedStatement preparedStatement = null;
		int Success = 0;
		try {
			// Setup the connection with the DB
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(mysql.getMysql());
			
	    	StringBuilder sql = new StringBuilder();
	    	List<Object> params = new ArrayList<Object>();
	        sql.append("delete from user where `USER_ID`=? and `PASSWORD`=?");
	        params.add(id);
	        params.add(password);
	        
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
