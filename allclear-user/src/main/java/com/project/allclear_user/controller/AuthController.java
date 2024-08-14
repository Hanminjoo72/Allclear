package com.project.allclear_user.controller;

import com.project.allclear_user.domain.dto.TokenDto;
import com.project.allclear_user.jwt.JwtGenerator;
import com.project.allclear_user.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
@Controller
@AllArgsConstructor
public class AuthController {

    final private AuthService authService;

    @GetMapping("/token")
    public ResponseEntity<TokenDto> generateAccessToken(HttpServletRequest request){
        String refreshToken = request.getHeader("Authorization");

        if(StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer ")) {
            return ResponseEntity.ok(authService.generateAccessToken(refreshToken.substring(7)));
        }
        throw new RuntimeException("빈 토큰값입니다.");
    }

}
