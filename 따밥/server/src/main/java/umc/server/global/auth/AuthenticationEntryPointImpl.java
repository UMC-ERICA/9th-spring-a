package umc.server.global.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralErrorCode;

import java.io.IOException;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(
                GeneralErrorCode._UNAUTHORIZED,
                null
        );

        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
