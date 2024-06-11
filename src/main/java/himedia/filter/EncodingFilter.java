package himedia.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");		// MIME TYPE -> text 파일 인데 문서 포맷은 html 이라는 뜻 -> 예를 들어 image/jpeg는 이미지인데 jpeg 포맷임 이라는 뜻
		resp.setCharacterEncoding("UTF-8");
		
		// 연결된 필터 요청, 응답 객체 전달
		chain.doFilter(req, resp);
		
	}

}
