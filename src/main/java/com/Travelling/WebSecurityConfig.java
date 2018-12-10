package com.Travelling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/**")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/css/**","/fonts/**","/images/**","/js/**","/scss/**")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/","/home","/signup","/database")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	@Bean
	public UserDetailsService userDetailService(){
		UserDetails user = User.withDefaultPasswordEncoder().username("hhaarryy").password("123456").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user);
	}

}
