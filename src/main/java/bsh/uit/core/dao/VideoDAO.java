package bsh.uit.core.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bsh.uit.core.entities.Video;
import bsh.uit.core.sql.MySQL;

public class VideoDAO {
	private MySQL mysql;
	private UserDAO userDao;
	private TypeDAO typeDao;
	
	public VideoDAO() {
		mysql = new MySQL();
		userDao = new UserDAO();
		typeDao = new TypeDAO();
	}
	
	public Video SQLtoVideo(ResultSet resultSet) throws Exception {
		Video video = new Video();
		while (resultSet.next()){
			video.setCreated_day(null);
			video.setDescription(resultSet.getString("description"));
			video.setId(resultSet.getString("notify_id"));
			video.setInstrument(typeDao.getTypebyId(resultSet.getString("instrument")));
			video.setLink(resultSet.getString("link"));
			video.setStatus(resultSet.getInt("status"));
			video.setUser(userDao.getUserbyId(resultSet.getString("user_id")));
		}
		return video;
	}
	
	public Video getVideobyId(String id) throws Exception {
		try {
			List<Object> params = new ArrayList<Object>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select * from video where video_id=?");
	        params.add(id);
			
			return SQLtoVideo(mysql.doSQLQuery(sql.toString(), params));
		} catch (Exception e) {
            throw e;
		}
	}
}
