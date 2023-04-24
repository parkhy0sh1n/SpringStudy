package com.gdu.app07;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// JUnit 프레임워크의 테스트 실행 방법을 확장할 때 사용하는 애니터이션이다.
// JUnit 4 : Java의 단위테스트를 수행해주는 대표적인 테스팅 프레임워크이다.
@RunWith(SpringJUnit4ClassRunner.class)

// 자동으로 만들어 줄 애플리케이션 컨텍스트의 설정 파일 위치를 지정해주는 애너테이션이다.
// 테스트에서 사용할 Bean이 @Conponent로 생성되었기 떄문에 component-scan이 작성된 servlet-context.xml의 경로
/*
	@Component : 개발자가 직접 작성한 Class 를 Bean 으로 만들어주는 애너테이션이다.
			     Singleton Class Bean을 생성하는 애너테이션이다. 
			     @Scope 애너테이션을 통해 Singleton 아닌 방식으로도 생성이 가능하다.
			     이 어노테이션은 선언적 애너테이션이다.
				 즉, package scan 안에 이 애너테이션은 "이 클래스를 정의했으니 Bean으로 등록하라"는 뜻이 된다.
*/
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

// 테스트 순서를 메소드명의 알파벳 순으로 수행.
/*
	@FixMethodOrder	     	     : 테스트 메소드의 실행 순서 기준을 결정해주는 애너테이션.
	MethodSorters.NAME_ASCENDING : 메소드 명을 오름차순으로 정렬한 순서대로 실행된다.
*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardControllerTest {
	
	/*
		Mock 테스트
		1. 가상 MVC 테스트이다.
		2. Controller를 테스트할 수 있는 통합 테스트이다.
		3. method + mapping을 이용해서 테스트를 진행한다.
	*/
	
	/*
		MockMvc : Mock 테스트를 수행하는 Spring Framework 객체이다.
	 			  WebApplicationContext 인터페이스에 의해 생성된다.
	*/
	/*
		ApplicationContext(Root Context)       : ContextLoaderListener에 의해서 만들어진다.
		WebApplicationContext(Servlet Context) : DispatcherServlet에 의해서 만들어진다.
	*/
	private MockMvc mockMvc;
	
	// @WebApplication이 있어야 자동 주입(@Autowired)이 가능하다,.
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	// LOGGER : 시스템 운영에 대한 기록인 log를 남겨주는 인터페이스이다.
	// BoardControllerTest 클래스(.class)를 로거공장(LoggerFactory)를 통해 Logger로 등록(getLogger)한다.
    private static final Logger LOGGER = LoggerFactory.getLogger(BoardControllerTest.class);
    
    // 모든 @Test 수행 이전에 실행된다.
    @Before
    public void setUp() throws Exception {
    	// webApplicationContext(Servlet Context)에 MockMvcBuilders를 통해 mockMvc객체를 build한다.
    	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
	@Test
	public void a1삽입테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/add.do")                  // @PostMapping("/board/add.do")
					.param("title", "테스트제목")       // 파라미터
					.param("content", "테스트내용")     // 파라미터
					.param("writer", "테스트작성자"))   // 파라미터
						.andReturn()                    // 삽입결과
						.getFlashMap()                  // FlashAttrubute에 저장된 결과 확인 
							.toString());
	}
    
	@Test
	public void a2수정테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/modify.do")               // @PostMapping("/board/modify.do")
					.param("title", "테스트제목2")      // 파라미터
					.param("content", "테스트내용2")    // 파라미터
					.param("boardNo", "1"))             // 파라미터
						.andReturn()                    // 수정결과
						.getFlashMap()                  // FlashAttrubute에 저장된 결과 확인 
							.toString());
	}
	
	@Test
	public void a3상세조회테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/detail.do")      // @GetMapping("/board/detail.do")
					.param("boardNo", "1"))   // 파라미터
						.andReturn()          // 상세조회결과
						.getModelAndView()    // Model에 저장된 조회 결과를 가져오기 위해서 ModelAndView를 먼저 가져옴
						.getModelMap()        // ModelAndView에서 Model을 가져옴
							.toString());
	}
	
	@Test
	public void a4목록테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/list.do"))   // @GetMapping("/board/list.do")
					.andReturn()          // 목록조회결과
					.getModelAndView()    // Model에 저장된 조회 결과를 가져오기 위해서 ModelAndView를 먼저 가져옴
					.getModelMap()        // ModelAndView에서 Model을 가져옴
						.toString());
	}
	
	@Test
	public void a5삭제테스트() throws Exception {
		LOGGER.debug(mockMvc.perform(MockMvcRequestBuilders
				.post("/board/remove.do")               // @PostMapping("/board/remove.do")
					.param("boardNo", "1"))             // 파라미터
						.andReturn()                    // 수정결과
						.getFlashMap()                  // FlashAttrubute에 저장된 결과 확인 
							.toString());
	}
    
}
