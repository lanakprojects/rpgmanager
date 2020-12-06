package hh.rpgmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.rpgmanager.web.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailServiceImpl userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .authorizeRequests()
        		.antMatchers("/signup", "/css/**").permitAll()
        		.and()
	        .authorizeRequests()
	        	.anyRequest().authenticated()
	        	.and()
	      .formLogin()
	          .loginPage("/login")
	          .defaultSuccessUrl("/charlist", true)
	          .permitAll()
	          .and()
	      .logout()
	          .permitAll();
	 }
	 
	

}
