package com.projectA1.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.projectA1.config.auth.PrincipalUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity // security 적용하려면 사용
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean // method보다 훨씬 독립적, 별도의 객체로 뽑아서 진행
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	////////////////////////////////////////////
	// 삭제가능 ///

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static String convertObjectToJson(Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return (request, response, authentication) -> {
			PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
			HttpSession session = request.getSession();
			session.setAttribute("user", principalUser); // PrincipalUser 객체를 세션에 저장
			session.setMaxInactiveInterval(1800); // 세션 만료 시간을 30분으로 설정 (초 단위)
			System.out.println("세션에 사용자이름 >>" + principalUser.getUsername() + " 님이 저장됨");
			response.setStatus(HttpServletResponse.SC_OK);

			// Content-Type을 설정하여 UTF-8 문자 인코딩을 사용
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(convertObjectToJson(principalUser.getUser()));
		};
	}

	@Bean
	public SimpleUrlAuthenticationFailureHandler failureHandler() {
		return new SimpleUrlAuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, SecurityException {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("Login Failed");
			}
		};
	}

	// 삭제예정///
	///////////////////////////////////////////

	@Bean // SecurityFilterChain - 로그인했을 때 어떻게 처리하겠다는 것.
	public SecurityFilterChain fileChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(request -> request
				.requestMatchers("/m_visit/**", "/m_review/**", "/m_reservation/**", "/m_centerManage/**", "/m_user/**",
						"/", "/img/**", "/src/main/**", "/main", "/updatePassword", "/join/**", "/login/**",
						"/centerManage/**", "/diary/**", "/test/**", "/login/**", "/user/join", "/owner/join",
						"/fragments/*")
				.permitAll().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers("/img/**").permitAll().requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/owner/**").hasRole("OWNER").anyRequest().authenticated())

				// 안드로이드 통신 시큐리티 적용때문에 사용하는 부분
				.formLogin(login -> login.loginProcessingUrl("/loginPro") // 로그인 처리 URL 설정
						.usernameParameter("email").passwordParameter("password") // 요청에서 사용되는 파라미터 설정 (필요한 경우)
						.successHandler(successHandler()).failureHandler(failureHandler()).permitAll()) // 모든 사용자에게 접근 허용
																										

				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/") // 로그아웃 후 리다이렉트할 경로 설정
						.invalidateHttpSession(true) // 세션 무효화
						.deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
						.permitAll());

		return http.build();

	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
