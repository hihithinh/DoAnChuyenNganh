package bsh.uit.core.mgr;

import bsh.uit.core.dao.RatingDAO;
import bsh.uit.core.entities.Rating;

public class RatingMgr {

	RatingDAO ratingDao;
	
	public RatingMgr() {
		ratingDao = new RatingDAO();
	}
	
	public Rating getNotifybyId(String id) throws Exception {
		return ratingDao.getRatingbyId(id);
	}
	
	public String addRating(Rating rating) throws Exception {
		return ratingDao.addRating(rating);
	}
	
	public String updateRating(Rating rating) throws Exception {
		return ratingDao.addRating(rating);
	}
	
	public String deleteRating(String id) throws Exception {
		return ratingDao.deleteRating(id);
	}
}
