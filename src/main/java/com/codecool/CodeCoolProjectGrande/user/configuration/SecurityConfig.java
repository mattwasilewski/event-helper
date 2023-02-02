package com.codecool.CodeCoolProjectGrande.user.configuration;


import com.codecool.CodeCoolProjectGrande.user.jwt.AuthEntryPointJwt;
import com.codecool.CodeCoolProjectGrande.user.jwt.AuthTokenFilter;
import com.codecool.CodeCoolProjectGrande.user.jwt.JwtUtils;
import com.codecool.CodeCoolProjectGrande.user.repository.UserDetailsServiceImpl;
import com.codecool.CodeCoolProjectGrande.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;


@Configuration
public class SecurityConfig {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    private static final int ENCODING_STRENGTH = 10;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfig(AuthEntryPointJwt unauthorizedHandler, UserDetailsServiceImpl userDetailsService) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization")); // added
                config.setMaxAge(3600L);
                return config;
            }
        })
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/google").authenticated()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("http://localhost:3000/", true)
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                        userDetailsService.processOauthUser(oAuth2User);
                        System.out.println("co to jest? : " + oAuth2User.getAttributes());
                        System.out.println("jakies get name xd: " + oAuth2User.getName() );
                        System.out.println("chyba email: " + oAuth2User.getAttributes().get("email"));
                        response.sendRedirect("http://localhost:3000/");
                    }
                });

        return http.build();
    }



    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
