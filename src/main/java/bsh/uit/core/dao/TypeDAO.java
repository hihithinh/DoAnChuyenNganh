package bsh.uit.core.dao;

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
		try {
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from type where type_id=?");
	        params.add(id);
			
			return SQLtoType(mysql.doSQLQuery(sql.toString(), params));
		} catch (Exception e) {
            throw e;
		}
	}
}
