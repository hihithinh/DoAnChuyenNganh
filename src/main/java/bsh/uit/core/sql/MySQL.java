package bsh.uit.core.sql;

import java.io.Serializable;


public class MySQL implements Serializable{

	private static final long serialVersionUID = 1L;
	private String dbUrl = "jdbc:mysql://localhost:3306/";
	private String dbName = "musicplayerconnectordb";
	private String username = "Admin";
	private String password = "aBcD1234";
	
	private String mysql = dbUrl+dbName+"?"
            + "user="+username
            +"&password="+password
            +"&verifyServerCertificate=false&useSSL=true";

	public String getMysql() {
		return mysql;
	}
}
