package com.test.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityInMemoryConfiguration {

	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails ramesh= User.builder()
				.username("Ramesh")
				.password(passwordEncode().encode("abc123"))
				.roles("USER")
				.build();
		UserDetails mukesh= User.builder()
				.username("Mukesh")
				.password(passwordEncode().encode("xyz789"))
				.roles("ADMIN","USER")
				.build();

		return new InMemoryUserDetailsManager(ramesh,mukesh);

	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeHttpRequests((authorize) ->{
			authorize.antMatchers("/api/admin").hasRole("ADMIN");
			authorize.antMatchers(HttpMethod.POST,"/api/user").hasRole("ADMIN");
			authorize.antMatchers(HttpMethod.GET,"/api/user").hasRole("USER");
			authorize.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());
		return http.build();

	}
}
