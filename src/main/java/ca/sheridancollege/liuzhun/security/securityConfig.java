package ca.sheridancollege.liuzhun.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private DonorDetailsServiceImpl donorDetailsServiceImple;
	
	
	@Autowired
	private StudentDetailsServiceImpl studentDetailsServiceImple;
	
	
	@Autowired
	private AdminDetailsServiceImpl adminDetailsServiceImple;
	
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
	
				http.csrf().disable();
				http.headers().frameOptions().disable();
				
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/register").permitAll()
			.antMatchers("/secure/**").hasAnyRole("DONOR", "STUDENT", "ADMIN")
			.antMatchers("/demo/checkout/api/paypal/order/create/").permitAll()
			.antMatchers("/demo/checkout/api/paypal/order/capture/").permitAll()
			.antMatchers("/", "/js/*", "/css/", "/images/", "/*").permitAll()
			
			// not to be implemented in the production
			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin()
				.loginPage("/login").permitAll()
			.and().logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll()
			.and()
				.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
				
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//			.passwordEncoder(NoOpPasswordEncoder.getInstance())
//				.withUser("frank@frank.com").password("1234").roles("USER")
//				.and()
//				.withUser("guest@guest.com").password("abcd").roles("GUEST");
//	}
//	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(donorDetailsServiceImple)
		.passwordEncoder(passwordEncoder());
		
		auth.userDetailsService(studentDetailsServiceImple)
		.passwordEncoder(passwordEncoder());
		
		auth.userDetailsService(adminDetailsServiceImple)
		.passwordEncoder(passwordEncoder());
		
	
	}
	
	


}