package com.cemalturkcan.ecommerce.library.security;

import com.cemalturkcan.ecommerce.library.security.jwt.api.JwtService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private static final String AUTHORIZATION_KEY = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = getJwt(request);
        token = Objects.equals(token, "null") ? null : token;

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final String userId = jwtService.extractUserName(token);

        if (StringUtils.isNotEmpty(userId) && SecurityContextHolder.getContext().getAuthentication() == null) {
            authenticateUser(request, token, userId);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateUser(HttpServletRequest request, String token, String userId) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userId);
        if (jwtService.isTokenValid(token, userDetails)) {
            setAuthenticationContext(request, userDetails);
        }
    }

    private void setAuthenticationContext(HttpServletRequest request, UserDetails userDetails) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
    }


    private String getJwt(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_KEY);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            return authorizationHeader.substring(7);
        }

        if (request.getCookies() == null || request.getCookies().length == 0) {
            return null;
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(AUTHORIZATION_KEY))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }
}