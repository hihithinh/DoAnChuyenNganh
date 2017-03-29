package bsh.uit.core.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bsh.uit.core.entities.Rating;
import bsh.uit.core.sql.MySQL;

public class RatingDAO {
	private MySQL mysql;
	private ProjectDAO projectDao;
	private UserDAO userDao;
	
	public RatingDAO() {
		mysql = new MySQL();
		projectDao = new ProjectDAO();
		userDao = new UserDAO();
	}
	
	public Rating SQLtoRating(ResultSet resultSet) throws Exception {
		Rating rating = new Rating();
		while (resultSet.next()){
			rating.setCreated_day(null);
			rating.setDescription(resultSet.getString("description"));
			rating.setId(resultSet.getString("notify_id"));
			rating.setParent(getNotifybyId(resultSet.getString("parent_id")));
			rating.setPoint(resultSet.getInt("rating_point"));
			rating.setProject(projectDao.getProjectbyId(resultSet.getString("project_id")));
			rating.setType(resultSet.getInt("rating_type"));
			rating.setUser(userDao.getUserbyId(resultSet.getString("user_id")));
		}
		return rating;
	}
	
	public Rating getNotifybyId(String id) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from rating where rating_id=?");
	        params.add(id);
			
			return SQLtoRating(mysql.doSQLQuery(sql.toString(), params));
		} catch (Exception e) {
            throw e;
		}
	}
}
