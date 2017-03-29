package bsh.uit.core.entities;

import java.io.Serializable;
import java.util.Date;
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	//ID
	private String id;
	
	//Ten dang nhap
	private String account;
	
	//Mat khau
	private String password;
	
	//Facebook token
	private String fbtoken;
	
	//Google token
	private String ggtoken;
	
	//Ten hien thi
	private String name;
	
	//Dia chi
	private String address;
	
	//URL anh dai dien
	private String avatar;
	
	//Loai cua user
	private Type user_type;
	
	//Ngay tao
	private Date created_day;
	
	//Trang thai tai khoan
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFbtoken() {
		return fbtoken;
	}

	public void setFbtoken(String fbtoken) {
		this.fbtoken = fbtoken;
	}

	public String getGgtoken() {
		return ggtoken;
	}

	public void setGgtoken(String ggtoken) {
		this.ggtoken = ggtoken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Type getUser_type() {
		return user_type;
	}

	public void setUser_type(Type user_type) {
		this.user_type = user_type;
	}

	public Date getCreated_day() {
		return created_day;
	}

	public void setCreated_day(Date created_day) {
		this.created_day = created_day;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
