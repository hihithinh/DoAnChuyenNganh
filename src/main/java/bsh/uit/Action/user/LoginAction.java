package bsh.uit.Action.user;
 
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

import bsh.uit.core.entities.User;
import bsh.uit.core.mgr.UserMgr;

public class LoginAction extends ActionSupport {
 
   private static final long serialVersionUID = 7299264265184515893L;
   private String account;
   private String password;
   private User user;
   private UserMgr userMgr;
   private boolean loginState;
   

   @Override
   public String execute() throws Exception {
           return SUCCESS;
   }
   
   public LoginAction() throws ClassNotFoundException, SQLException {
	   user = new User();
	   userMgr = new UserMgr();
   }
 
   public String Login() throws Exception {
	   try {
		  // user = new User();
		   //userDao.readDataBase();
		   user = userMgr.loginUser(account, password);
		   System.out.println("name:"+user.getName());
		   if(user.getName().equals(null) || user.getName() == null) {
			   loginState = false;
		   } else {
			   loginState = true;
		   }
		   return SUCCESS;
		   
	   } catch (Exception e) {
		   throw e;
	   }
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
}