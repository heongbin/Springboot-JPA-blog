package com.blog.blogtest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.blog.blogtest.config.auth.PrincipalDetail;
import com.blog.blogtest.config.auth.PrincipalDetailService;


//스프링 시큐리티 커스터마이징.
@Configuration //ioc관리 ,빈등록
@EnableWebSecurity //시큐리티 필터 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한및 인증 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //ioc
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();//return 값을 빈에 등록해줌
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	
			.csrf().disable()//csrf 비허ㅏㄹ
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")//인증이 필요한곳은 이곳으로 인도
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/");
				//.failureUrl("/fail");
		}
}
