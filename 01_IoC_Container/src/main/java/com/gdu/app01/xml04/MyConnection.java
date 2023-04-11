package com.gdu.app01.xml04;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	
	// field
	private String driver;
	private String url;
	private String user;
	private String password;
	
	// default constructor
	
	// method(getter,setter, getConnection)
	public Connection getConnection() {
		// 자바와 DB를 연결하기 위해서 Connection 객체를 생성한다.
		Connection con = null;
		try {
			// Class.forName()을 이용해서 드라이버 로드한다.
			Class.forName(driver);
			// 선언한 con 객체에 생성된 Connection 객체 대입한다.
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle 접속 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
