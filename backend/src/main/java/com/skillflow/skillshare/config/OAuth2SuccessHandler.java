package com.skillflow.skillshare.config;

import com.skillflow.skillshare.model.User;
import com.skillflow.skillshare.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final String redirectUrl;

    public OAuth2SuccessHandler(UserService userService, JwtUtil jwtUtil, 
                               @Value("${frontend.redirect-url}") String redirectUrl) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.redirectUrl = redirectUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String username = oAuth2User.getAttribute("name");
        String oauthId = oAuth2User.getAttribute("sub");
        String oauthProvider = "google";

        User user = userService.createOrUpdateOAuthUser(email, username, oauthId, oauthProvider);
        String jwt = jwtUtil.generateToken(user.getEmail(), user.getUsername(), user.getRoles());

        String targetUrl = redirectUrl + "?token=" + jwt;
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}