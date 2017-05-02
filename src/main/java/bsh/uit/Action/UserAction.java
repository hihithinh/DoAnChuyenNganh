package bsh.uit.Action;
 
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bsh.uit.core.dao.UserDAO;
import bsh.uit.core.entities.User;
import bsh.uit.core.mgr.UserMgr;

public class UserAction extends ActionSupport implements SessionAware{
 
   private static final long serialVersionUID = 7299264265184515893L;
   private String account;
   private String password;
   private User user;
   private UserMgr userMgr;
   private boolean loginState;
   private Map<String, Object> sessionMap;
   
   @Override
   public String execute() throws Exception {
           return SUCCESS;
   }
   
   public UserAction() throws ClassNotFoundException, SQLException {
	   user = new User();
	   userMgr = new UserMgr();
	   sessionMap = ActionContext.getContext().getSession();
   }
 
   public String Login() throws Exception {
	   ObjectMapper mapper = new ObjectMapper();
       
	   try {

		   user = userMgr.loginUser(account, password);
		   System.out.println("name:"+user.getName());
		   if(user.getName().equals(null) || user.getName() == null) {
			   loginState = false;
		   } else {
			   loginState = true;
			   user.setPassword(null);
			   sessionMap.put("username", mapper.writeValueAsString(user));
		   }
		   
		   return SUCCESS;
		   
	   } catch (Exception e) {
		   throw e;
	   }
   }
   
   public String viewUserInfo() throws Exception {
	   user = userMgr.getUserbyAccount(account);
	   return SUCCESS;
   }
   
   public String createNewUser() throws Exception {
	   user = userMgr.addUser(user);
	   return SUCCESS;
   }
   
   public String updateUserInfo() throws Exception {
	   user = userMgr.updateUser(user);
	   return SUCCESS;
   }
   
   public String Logout() {
       // remove userName from the session
       if (sessionMap.containsKey("username")) {
           sessionMap.remove("username");
       }
       return SUCCESS;
   }

	public User getUser() {
		return user;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		System.out.println(account);
		return account;
	}

	public String getPassword() {
		return password;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean getLoginState() {
		return loginState;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;	
	}
}