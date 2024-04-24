package dw.gameshop.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException, JsonProcessingException {
        // 인증되지 않은 사용자가 보호된 리소스에 접근했을 때 호출됨

        // 응답으로 전송할 JSON 데이터 생성
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", HttpStatus.UNAUTHORIZED.value());
        responseData.put("error", "Unauthorized");
        responseData.put("message", "Authentication required");

        // JSON 데이터를 문자열로 변환
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(responseData);

        // 응답 헤더 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 응답 내용 출력
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
