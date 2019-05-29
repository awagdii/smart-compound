package com.ntgclarity.smartcompound.portal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	private CustomAccessDescionManager customAccessDescionManager;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder amb) {
		amb.authenticationProvider(customAuthenticationProvider);
	}

	@Configuration
	public static class MyClass1 extends WebSecurityConfigurerAdapter {

		@Autowired
		private CustomAccessDescionManager customAccesDescionManager;

		@Autowired
		private CustomAccessDeniedHandler customAccessDeniedHandler;
		
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();   
			/**START HEBA'S WORK**/
			//Access CSS, JS and image files
			http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**").permitAll();
			/**END HEBA'S WORK**/
	/*		http.authorizeRequests().antMatchers("/login.xhtml").permitAll()  
					.and().formLogin().loginPage("/login.xhtml").permitAll()
					.and().logout().permitAll().logoutSuccessUrl("/login.xhtml")
					.and().authorizeRequests().antMatchers("/admin/**.xhtml").access("isFullyAuthenticated()")
					.accessDecisionManager(customAccesDescionManager)
					.anyRequest().fullyAuthenticated().and().formLogin()
					.loginPage("/login.xhtml").failureUrl("/login.xhtml?error")
					.usernameParameter("email").permitAll().and().logout()
					.logoutUrl("/logout").deleteCookies("remember-me")
					.logoutSuccessUrl("/").permitAll().and().rememberMe(); 
	*/
			/**START HEBA'S WORK**/
//			http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
			/**END HEBA'S WORK**/
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/javax.faces.resource/**");
			web.ignoring().antMatchers("/*.xhtml"); 
			web.ignoring().antMatchers("/ws/**");   
			web.ignoring().antMatchers("/imageResolver");  


		}
	}

}
