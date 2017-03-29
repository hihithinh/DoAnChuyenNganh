package bsh.uit.core.sql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class MySQL implements Serializable{

	private static final long serialVersionUID = 1L;
	private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public ResultSet doSQLQuery(String sql, List<Object> params) throws Exception {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayerconnectordb?"
	                                            + "user=Admin&password=aBcD1234");
			
			preparedStatement = connect.prepareStatement(sql);
			for(int i = 0; i < params.size(); i++){
				preparedStatement.setObject(i+1, params.get(i));
			}
			resultSet = preparedStatement.executeQuery();
			return resultSet;
    	} catch(Exception e) {
    		throw e;
    	} 
    }
}
