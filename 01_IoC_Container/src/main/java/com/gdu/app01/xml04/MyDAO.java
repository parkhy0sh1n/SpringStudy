package com.gdu.app01.xml04;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MyDAO {
	
	// field
	private Connection con;
	
	// singleton pattern - app-context.xml에서 <bean> 태그를 만들 때 사용된다.
	
	// method
	public Connection getConnection() {
		
		// Spring Container에 만들어 둔 myConn Bean 가져오기
		/*
			AbstractApplicationContext  : 컨테이너 종료(close)와 같은 기능을 제공해 주는 객체이다.
			enericXmlApplicationContext : AbstractApplicationContext 객체를 상속 받아 만드는 클래스. 
										  xml 파일에서 스프링 빈 설정 정보를 읽어오는 역할을 수행한다.
		*/
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml04/app-context.xml");
		con = ctx.getBean("myConn", MyConnection.class).getConnection();
		ctx.close();
		return con;
	}
	
	public void close() {
		
		// con.close() 메소드를 사용하면 오류가 발생하기 때문에 예외처리 한다.
		try {
			if(con != null) {
				con.close(); 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	public void list() {
		
		con = getConnection();
		
	}

}
