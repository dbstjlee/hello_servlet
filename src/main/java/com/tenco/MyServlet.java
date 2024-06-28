package com.tenco;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// http://localhost:8080/hello/my-servelt
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyServlet() {
		super();
		System.out.println("호출1");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("호출2");
		// DB 접근 기술 - 10초
	}

	// 클라이언트가 매번 요청을 하는데 매번 호출이 될까?
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("호출3");
		System.out.println("서비스 메서드 호출 확인");
		response.setContentType("text/html; charset=UTF-8");
		// 동작 흐름 방식 이해하기
		// doGet, doPost는 동작을 안 한 이유: 요청 받으면 service가 받고
		// 부모 클래스 메서드 호출
		super.service(request, response); // 알아서 분배해줌.
		// doGet 메서드를 알아서 호출함.
		// doGet, doPost 중 무엇을 먼저 호출해야 할지 모를 때 super.service() 사용하기.
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("호출4 - 1");
		System.out.println("doGet 메서드 호출");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("호출4 - 2");
		System.out.println("doPost 메서드 호출");
	}

	@Override
	public void destroy() {
		System.out.println("destroy() 메서드 호출");
		super.destroy();
	}
	// web.xml : <load-on-startup>1</load-on-startup> 추가
	// 호출 1, 2 가 먼저 출력됨.
	// servlet 맵핑 전에 입력하면 HelloServlet보다 먼저 호출됨.

}
