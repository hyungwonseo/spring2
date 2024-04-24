package dw.gameshop.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        // 권한이 없는 사용자가 보호된 리소스에 접근했을 때 호출됨

        // 응답으로 전송할 JSON 데이터 생성
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", HttpStatus.FORBIDDEN.value());
        responseData.put("error", "Forbidden");
        responseData.put("message", "Access denied");

        // JSON 데이터를 문자열로 변환
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responseData);

        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // 응답 내용 출력
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
