package com.tenco;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HttpServlet 클래스를 상속받아서 내가 정의한 클래스가 선언이 된다. 이유는 HTTP 프로토콜을 통한 request,
 * response 처리를 할 수 있기 때문이다.
 * 
 * URL 맵핑에 대한 개념을 알자. 클라이언트가 특정 URL을 요청했을 때 해당 URL에 대응하는 서블릿을 실행하도록 설정하는 것을
 * 의미한다. URL 맵핑 - 2가지 방법을 알아보자
 */

// 요청이 올 때 메모리에 올라가는 형식
//@WebServlet("/hello-servlet2")

// 서버 시작 시 바로 메모리에 뜨게 하는 방법 
@WebServlet(urlPatterns = "/example", loadOnStartup = 1)
// HelloServlet과 MyServlet 둘 다 살아있는 상태임.
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// sevlet 클래스가 메모리에 올라가는 시점은?

	// 생성자
	public HelloServlet() {
		super();
		System.out.println("생성자가 호출됨");
	}

	// 해당 서블릿 클래스가 인스턴스화 될 때 단 한번 실행하는 메서드이다.
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 메서드가 호출됨");
	}

	// 메모리에서 내려가기 직전에 호출되는 메서드이다.
	public void destroy() {
		System.out.println("destroy 호출");
		// init() 메서드 다시 호출 안 하고 재활용함.
	}

	// WebServlet이 아닌 URL 맵핑으로 GET
	// GET 요청으로 들어올 때 동작됨.
	// 주소 설계 - http://localhost:8080/hello/hello-servlet
	// hello : context root(servlet 만들 시 설정한 것)
	// hello-servlet : web.xml 내의 url-pattern
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MIME TYPE - 인터넷 세상에서 데이터의 유형을 나타내는 표준 방식

		response.setContentType("application/pdf");
		//response.setContentType("text/html");
		// 문자 형식은 html이라고 먼저 설정. 그래야 웹서버가? 파싱 가능
		response.setCharacterEncoding("UTF-8");
		// 스트림을 어디에서 뽑아야 될까?
		response.getWriter().write("<html><body><h1>HELLO</h1></body></html>"); // http 통신 => printwriter 사용
		// 컴파일됨.
	}

	// POST 요청으로 들어올 때 동작됨.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 문제 -> POST 요청이 들어오는 것을 확인하고
		// 응용해서 데이터 또는 html 형식으로 응답처리 하시오!
		// request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>...</h1>");
		out.print("</body>");
		out.print("</html>");
	}

	// monitor tomcat  -> start
	// http://localhost:8080/ -> 이 웹이 welcom-file 에 정의되어 있음. 
	
	// http://localhost:8080/hello/
	// welcome-file-list() 에 정의되어 있어서 
	// http://localhost:8080/hello/index.html 이라고 안 쳐도 나옴. 
	
	// .war 파일 만들기
	// http://localhost/hello/index.html
	// dypackage > Export > war 검색 > war file > Browse... >  wars 파일 생성 
	// > hello_servlet.war이 자동으로 생성됨 
	// > Tomcat 파일 내의 webapps에 파일명을 hello_servlet.war ->  hello.war로 변경 
	// > monitor tomcat -> stop 했다가 다시 start
	
}
