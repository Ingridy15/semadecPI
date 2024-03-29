package com.semadec.semadec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws
	Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET,"/").permitAll()
		.antMatchers(HttpMethod.GET,"/cadastrarU").permitAll()
		.antMatchers(HttpMethod.POST,"/cadastrarU").permitAll()
		.antMatchers(HttpMethod.GET,"/cadastrarE").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST,"/cadastrarE").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) 
	throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		}
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/materialize/**","/style/**");
	}
}
