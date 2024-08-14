package com.project.allclear_user.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            log.info("exception filter");
            doFilter(request,response,filterChain);
            log.info("jwt success");
        } catch (NullPointerException e) {
            final Map<String, Object> body = new HashMap<>();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 예외에 맞는 HTTP 상태 코드 설정
            response.setContentType("application/json");
            body.put("timestamp", LocalDateTime.now());
            body.put("error", "Bad Request");// 예외에 맞는 메시지 설정
            body.put("path", request.getRequestURI());

            mapper.writeValue(response.getOutputStream(), body);
            logger.info("jwt exception nullpointer");
            //throw new ExceptionHandler(Error.);
        }
        catch (ExpiredJwtException e) {
            final Map<String, Object> body = new HashMap<>();
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            response.setStatus(419);
            response.setContentType("application/json");
            body.put("timestamp", LocalDateTime.now());
            body.put("error", "Unauthorized");
            body.put("path", request.getRequestURI());

            mapper.writeValue(response.getOutputStream(), body);
        }
    }
}