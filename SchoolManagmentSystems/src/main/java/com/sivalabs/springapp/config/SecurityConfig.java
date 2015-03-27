/**
 * 
 */
package com.sivalabs.springapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Siva
 *
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) 
//@ImportResource("classpath:applicationContext-security.xml")
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private DataSource dataSource;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
		/*
        registry
        .inMemoryAuthentication()
        .withUser("siva")  // #1
          .password("siva")
          .roles("USER")
          .and()
        .withUser("admin") // #2
          .password("admin")
          .roles("ADMIN","USER");
        */
        
       // registry.jdbcAuthentication().dataSource(dataSource);
//		registry.userDetailsService(customUserDetailsService);
		registry.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("select email, password, true from users where email = ?")
		.authoritiesByUsernameQuery("select u.email, r.role_name from users u, roles r where u.email = ? and u.id = r.user_id");
    }


	  @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**"); // #3
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	    .csrf().disable()
	    .authorizeRequests()
	        .antMatchers("/login","/login/form**","/register","/logout").permitAll() // #4
	        .antMatchers("/admin","/admin/**").hasRole("ADMIN") // #6
	        .anyRequest().authenticated() // 7
	        .and()
	    .formLogin()  // #8
	        .loginPage("/login/form") // #9
	        .loginProcessingUrl("/login")
	        .failureUrl("/login/form?error")
	        .permitAll(); // #5
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder(){
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
	  }
}
