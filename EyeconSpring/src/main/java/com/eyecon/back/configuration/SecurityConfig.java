package com.eyecon.back.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecurityConfig {

	
	  @Bean
	   public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
	      
	      http
	      .csrf(crsf -> crsf.disable())
	      .authorizeHttpRequests((authorizeRequest) -> authorizeRequest.
	            requestMatchers(new AntPathRequestMatcher("/auth/**"), new AntPathRequestMatcher("/verify/**"),new AntPathRequestMatcher("/join.do") )
	            .permitAll()
	            .anyRequest().authenticated())
	      .sessionManagement(
	            (sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	      .authenticationProvider(authenticationProvider)
//	      .addFilterBefore(jwtautuFilter, UsernamePasswordAuthenticationFilter.class)
	      
//	        .logout(logoutConfig -> { logoutConfig .logoutUrl("/auth/logout")
//	        .addLogoutHandler(logoutService) .logoutSuccessHandler((request, response,
//	        authentication) -> SecurityContextHolder.clearContext()); })
//	       
	      ;

	      return http.build();
	   }
	
}
