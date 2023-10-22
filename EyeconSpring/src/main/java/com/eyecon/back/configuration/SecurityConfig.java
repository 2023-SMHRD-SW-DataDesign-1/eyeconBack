package com.eyecon.back.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;


@Configuration
@EnableWebSecurity

public class SecurityConfig {

	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthenticationFilter jwtautuFilter;
	private final LogoutHandler logoutService;
	
	/*
	 * @Bean MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector)
	 * { return new MvcRequestMatcher.Builder(introspector); }
	 */
	 
	public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtautuFilter, LogoutHandler logoutService){
		this.authenticationProvider = authenticationProvider;
		this.jwtautuFilter = jwtautuFilter;
		this.logoutService = logoutService;
		
		
	}


	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
		
		http
		.cors(Customizer.withDefaults())
		.csrf(crsf -> crsf.disable())
		.authorizeHttpRequests((authorizeRequest) -> authorizeRequest.
				requestMatchers(new AntPathRequestMatcher("/auth/**"), new AntPathRequestMatcher("/verify/**"),new AntPathRequestMatcher("/join.do"),new AntPathRequestMatcher("/login.do"))
				.permitAll()
				.anyRequest().authenticated())
		.sessionManagement(
				(sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtautuFilter, UsernamePasswordAuthenticationFilter.class)
		
		  		.logout(logoutConfig -> { logoutConfig .logoutUrl("/auth/logout")
		  		.addLogoutHandler(logoutService)
		  		.logoutSuccessHandler((request, response, authentication) -> 
		  			SecurityContextHolder.clearContext()); 
		})
		;

		return http.build();
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

    }
	

	
    
}
